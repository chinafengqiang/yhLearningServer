package com.smlearning.datasync;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.ptg.TblPtg;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;







import com.smlearning.domain.entity.UserInfo;
import com.smlearning.infrastructure.utils.AppConstants;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.DateUtil;
import com.smlearning.infrastructure.utils.PageHelper;
import com.smlearning.jdbc.DBUtils;
import com.smlearning.jdbc.TableInfo;

public class DataSync implements IDataSync{

  @Resource
  private DBUtils dbUtils;

  @Override
  public DataGrid listChannel(PageHelper ph) {
    DataGrid dg = dbUtils.Pagination("select * from "+TableInfo.CHANNEL_NAME,ph);
    return dg;
  }

  @Override
  public void createChannel(HashMap<String, String> channel) {
    dbUtils.insert(TableInfo.CHANNEL_NAME,channel);
  }

  @Override
  public Map<String, Object> getChannel(int id) {
    return dbUtils.getObject("select * from "+TableInfo.CHANNEL_NAME+" where ID = ?", new Object[]{id});
  }

  @Override
  public void updateChannel(HashMap<String, String> channel) {
    dbUtils.update(TableInfo.CHANNEL_NAME,channel,"ID");
  }

  @Override
  public void deleteChannel(int id) {
    dbUtils.delete(TableInfo.CHANNEL_NAME,"ID", id);
  }

  @Override
  public DataGrid listGroup(PageHelper ph) {
    DataGrid dg = dbUtils.Pagination(TableInfo.GROUP_NAME,"",ph);
    return dg;
  }

  @Override
  public Map<String, Object> getGroup(int id) {
    return dbUtils.getObject("select * from "+TableInfo.GROUP_NAME+" where ID = ?", new Object[]{id});
  }
  
  @Override
  public void createGroup(HashMap<String, String> group) {
    dbUtils.insert(TableInfo.GROUP_NAME,group);
  }
  
  @Override
  public void updateGroup(HashMap<String, String> group) {
    dbUtils.update(TableInfo.GROUP_NAME,group,"ID");
  }
  
  @Override
  public void deleteGroup(int id) {
    deleteUserRuleByGroup(id);//删除组内用户权限
    
    dbUtils.delete("delete from "+TableInfo.USER_NAME+" where GID = ?",new Object[]{id});//删除组内用户
    
    dbUtils.delete("delete from "+TableInfo.USER_GROUP_NAME+" where GID = ?",new Object[]{id});//删除组权限
    
    dbUtils.delete(TableInfo.GROUP_NAME,"ID", id);//删除组
  }

  @Override
  public List<Map<String, Object>> getGroupPermChannel(int gid) {
    List<Map<String,Object>> userGroupList = dbUtils.getList("select * from "+TableInfo.USER_GROUP_NAME+" where GID = ?",
        new Object[]{gid});
    List<Map<String,Object>> channelList = dbUtils.getList("select * from "+TableInfo.CHANNEL_NAME+" where TYPE = ?",
        new Object[]{AppConstants.CHANNEL_TYPE_FILEPROG});
    initGroupPerm(channelList,userGroupList);
    return channelList;
  }
  
  private void initGroupPerm(List<Map<String,Object>> channelList,List<Map<String,Object>> ruleList){
    if(channelList!=null&&channelList.size()>0){
        for(int i=0;i<channelList.size();i++){
            Map<String,Object> channel = channelList.get(i);
            if(channel!=null){
                long channelId = (Long)channel.get("ID");
                if(ruleList!=null&&ruleList.size()>0){
                    for(int j=0;j<ruleList.size();j++){
                        Map<String,Object> group = ruleList.get(j);
                        if(group!=null){
                            long obj = (Long)group.get("UID");
                            if(channelId == obj){
                                channel.put("PERMED",1);
                                continue;
                            }
                        }
                    }
                }
            }
        }
    }   
}

  @Override
  public int setGroupPerm(HashMap<String, String> group) {
    int channelId = Integer.parseInt(group.get("channelId"));
    int gid = Integer.parseInt(group.get("gid"));
    int tag = Integer.parseInt(group.get("tag"));
    String sid = group.get("sid");
    List<Map<String,Object>> ruledList = dbUtils.getList("select * from "+TableInfo.USER_GROUP_NAME+" where GID = ? and UID = ?",
        new Object[]{gid,channelId});
    
    try {
        if(channelId>0&&gid>0){
          if(tag == 1){
              if(ruledList!=null&&ruledList.size()>0){
                  
              }else{
                  doSetGroupPerm(sid, gid, channelId);
              }
          }
          if(tag == -1){
              if(ruledList!=null&&ruledList.size()>0){
                  for(int i=0;i<ruledList.size();i++){
                    Map<String,Object> rule = ruledList.get(i);
                    removeUserGroupAndRule(rule);
                  }
              }
          }
      }
      return 1;
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }
  

  private void doSetGroupPerm(String sid,int gid,int channelId){
    List<Map<String,Object>> userList = dbUtils.getList("select * from "+TableInfo.USER_NAME+" where GID = ?"
        , new Object[]{gid});
    if(userList!=null&&userList.size()>0){
      List<Object[]> ruleList = new ArrayList<Object[]>();
      Object[] rule = null;
      for(Map<String,Object> user : userList){
        rule = new Object[9];
        long uid = (Long)user.get("UID");
        long startTime = (Long)user.get("START_VALID_DATE");
        long endTime = (Long)user.get("END_VALID_DATE");
        rule[0] = uid;
        rule[1] = 1;
        rule[2] = channelId;
        rule[3] = 6;
        rule[4] = 7;
        rule[5] = 1;
        rule[6] = startTime;
        rule[7] = endTime;
        rule[8] = sid;
        ruleList.add(rule);
      }
      
      dbUtils.batchUpdate("insert into "+TableInfo.USER_RULE_NAME
          +" (SRC,SRC_TYPE,OBJECT,OBJECT_TYPE,MODE,OBJECT_RANGE_TYPE,START_VALID_DATE,END_VALID_DATE,OBJECT_SID) values (?,?,?,?,?,?,?,?,?)",ruleList);
    }
    
    HashMap<String,String> usergroup = new HashMap<String,String>();
    usergroup.put("GID",gid+"");
    usergroup.put("UID",channelId+"");
    dbUtils.insert(TableInfo.USER_GROUP_NAME, usergroup);
  }
  
  private void removeUserGroupAndRule(Map<String,Object> rule){
    long id = (Long)rule.get("ID");
    long gid = (Long)rule.get("GID");
    long channelId = (Long)rule.get("UID");
    dbUtils.delete(TableInfo.USER_GROUP_NAME,"ID",id);
    
    deleteUserRuleByGroup(gid, channelId);
  }

  @Override
  public void deleteUserRuleByGroup(long gid, long channelId) {
    String sql = "delete from "+TableInfo.USER_RULE_NAME+" where SRC in (select u.UID from "+TableInfo.USER_NAME+" u where u.GID = ?)";
           sql += " and SRC_TYPE = 1 and OBJECT = ?";
    dbUtils.delete(sql, new Object[]{gid,channelId});
    
  }
  
  
  @Override
  public void deleteUserRuleByGroup(long gid) {
    String sql = "delete from "+TableInfo.USER_RULE_NAME+" where SRC in (select u.UID from "+TableInfo.USER_NAME+" u where u.GID = ?)";
           sql += " and SRC_TYPE = 1";
    dbUtils.delete(sql, new Object[]{gid});
  }

  @Override
  public List<Map<String, Object>> getGroupSelect() {
    return dbUtils.getList("select ID as value,NAME as name from "+TableInfo.GROUP_NAME,null);
  }

  @Override
  public DataGrid listUser(PageHelper ph) {
    String sql = "select u.*,g.NAME as GNAME from "+TableInfo.USER_NAME+" u left join "+TableInfo.GROUP_NAME+" g on ";
            sql += "u.GID = g.ID where u.TYPE = ?";
    return dbUtils.Pagination(sql,new Object[]{4}, ph);
  }

  @Override
  public void createUser(HashMap<String, String> user) {
    long startTime = new Date().getTime();
    Date endDate = DateUtil.addYears(new Date(), 15);
    long endTime = endDate.getTime();

    long gid = Long.parseLong(user.get("GID"));
    
    String sql = "insert into "+TableInfo.USER_NAME+" (TYPE,IS_VALID,START_VALID_DATE,END_VALID_DATE,USERNAME,PASSWORD,GID)";
          sql += " values (4,1,"+startTime+","+endTime+",'"+user.get("USERNAME")+"','"+user.get("PASSWORD")+"',"+gid+")";
    long userId = dbUtils.insertAndGetKey(sql);
    
    setAddUserPerm(userId, gid, startTime, endTime);
  }
  
  
  private void setAddUserPerm(long userId,long gid,long startTime,long endTime){
    List<Map<String,Object>> userGroupList = dbUtils.getList("select * from "+TableInfo.USER_GROUP_NAME+" where GID = ?",
        new Object[]{gid});
    if(userId>0&&userGroupList != null&&userGroupList.size()>0){//存在组授权
       //把用户添加到权限表中
      HashMap<String,String> rule = null;
      for(Map<String,Object> userGroup : userGroupList){
        if(userGroup != null){
          long channelId = (Long)userGroup.get("UID");
          rule = new HashMap<String, String>();
          Map<String,Object> channel = getChannel((int)channelId);
          if(channel != null){
            rule.put("SRC",userId+"");
            rule.put("SRC_TYPE","1");
            rule.put("OBJECT",channelId+"");
            rule.put("OBJECT_TYPE","6");
            rule.put("MODE","7");
            rule.put("OBJECT_RANGE_TYPE","1");
            rule.put("START_VALID_DATE",startTime+"");
            rule.put("END_VALID_DATE",endTime+"");
            rule.put("OBJECT_SID",(String)channel.get("SID"));
            dbUtils.insert(TableInfo.USER_RULE_NAME, rule);
          }
        }
      }
    }
  }

  @Override
  public void updateUser(HashMap<String, String> user) {
    dbUtils.update(TableInfo.USER_NAME,user,"UID");
  }

  @Override
  public Map<String, Object> getUser(int id) {
    return dbUtils.getObject("select * from "+TableInfo.USER_NAME+" where UID = ?", new Object[]{id});
  }

  @Override
  public void deleteUser(int id) {
    dbUtils.delete("delete from "+TableInfo.USER_RULE_NAME+" where SRC = ? and SRC_TYPE = ?", new Object[]{id,1});
    
    dbUtils.delete(TableInfo.USER_NAME,"UID",id);
  }

  @Override
  public List<Map<String, Object>> listRulesBySrc(int src, int srcType, int objetType) {
    return dbUtils.getList("select * from "+TableInfo.USER_RULE_NAME+" where SRC = ? and SRC_TYPE = ? and OBJECT_TYPE = ?",
        new Object[]{src,srcType,objetType});
  }
  
  
  
  @Override
  public List<Map<String, Object>> listRulesBySrcAndObject(int src, int srcType, int objetType,
      int object) {
    return dbUtils.getList("select * from "+TableInfo.USER_RULE_NAME+" where SRC = ? and SRC_TYPE = ? and OBJECT_TYPE = ? and OBJECT = ?",
        new Object[]{src,srcType,objetType,object});
  }

  @Override
  public List<Map<String, Object>> getUserPermChannel(int uid) {

    List<Map<String, Object>> channelRuleList = listRulesBySrc(uid,1,6);
    List<Map<String,Object>> channelList = dbUtils.getList("select * from "+TableInfo.CHANNEL_NAME+" where TYPE = ?",
        new Object[]{AppConstants.CHANNEL_TYPE_FILEPROG});
    initUserPerm(channelList,channelRuleList);
    return channelList;
  }
  
  
  private void initUserPerm(List<Map<String, Object>> channelList,List<Map<String, Object>> channelRuleList){
    if(channelList!=null&&channelList.size()>0){
        for(int i=0;i<channelList.size();i++){
            Map<String, Object> channel = (Map<String, Object>)channelList.get(i);
            if(channel != null){
              long channelId = (Long)channel.get("ID");
              String sid = (String)channel.get("SID");
              if(channelRuleList!=null&&channelRuleList.size()>0){
                  for(int j=0;j<channelRuleList.size();j++){
                      Map<String, Object> rule = (Map<String, Object>)channelRuleList.get(j);
                      if(channel!=null&&rule!=null){
                          String ruleSid = (String)rule.get("OBJECT_SID");
                          BigInteger objectBI = (BigInteger)rule.get("OBJECT");
                          long object = 0;
                          if(objectBI != null){
                            object = objectBI.longValue();
                          }
                          if(channelId == object&&sid.equals(ruleSid)){
                              channel.put("PERMED",1);
                              continue;
                          }
                      }
                  }
              }
            }
        }
    }
}

  @Override
  public int setUserPerm(HashMap<String, String> user) {
    int channelId = Integer.parseInt(user.get("channelId"));
    int uid = Integer.parseInt(user.get("uid"));
    int tag = Integer.parseInt(user.get("tag"));
    String sid = user.get("sid");
    
    List<Map<String,Object>> ruledList = listRulesBySrcAndObject(uid,1,6,channelId);
    
    try {
        if(channelId>0&&uid>0){
          Map<String,Object> userMap = getUser(uid);
          if(tag == 1){
              if(ruledList!=null&&ruledList.size()>0){
                  
              }else{
                HashMap<String, String> rule = new HashMap<String, String>();
                rule.put("SRC",uid+"");
                rule.put("SRC_TYPE","1");
                rule.put("OBJECT",channelId+"");
                rule.put("OBJECT_TYPE","6");
                rule.put("MODE","7");
                rule.put("OBJECT_RANGE_TYPE","1");
                rule.put("START_VALID_DATE",(Long)userMap.get("START_VALID_DATE")+"");
                rule.put("END_VALID_DATE",(Long)userMap.get("END_VALID_DATE")+"");
                rule.put("OBJECT_SID",sid);
                dbUtils.insert(TableInfo.USER_RULE_NAME, rule);
              }
          }
          if(tag == -1){
              if(ruledList!=null&&ruledList.size()>0){
                  for(int i=0;i<ruledList.size();i++){
                    Map<String,Object> rule = ruledList.get(i);
                    dbUtils.delete(TableInfo.USER_RULE_NAME,"ID",rule.get("ID"));
                  }
              }
          }
      }
      return 1;
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }
  
  
  public DataGrid listFileprog(int channlId,String startTime,String endTime,PageHelper ph) {
    StringBuffer HQL = new StringBuffer();
    HQL.append("select f.*,c.NAME as CHANNEL_NAME from tbl_media_fileprog as f,tbl_device_channel as c where");
    HQL.append(" f.CHANNEL_ID = c.ID and c.TYPE= 2");
    HQL.append(" and f.END_VALID_DATE >= "+new Date().getTime());
    if(channlId > 0){
      HQL.append(" and f.CHANNEL_ID = "+channlId);
    }
    if(StringUtils.isNotBlank(startTime)){
      Long start=DateUtil.dateString2Long(startTime);
      HQL.append(" and f.START_VALID_DATE >= "+start);
    }
    if(StringUtils.isNotBlank(endTime)){
      Long end=DateUtil.dateString2Long(endTime);
      HQL.append(" and f.START_VALID_DATE <= "+end);
    }
    //HQL.append(" order by f.CREATIONDATE desc");
    DataGrid dg = dbUtils.Pagination(HQL.toString(), ph);
    if(dg!=null){
     List<HashMap<String,Object>> list =  (List<HashMap<String,Object>>)dg.getRows();
     if(list!=null && list.size()>0){
       for(HashMap<String,Object> fileporg:list){
           long start = (Long)fileporg.get("START_VALID_DATE");
           long end = (Long)fileporg.get("END_VALID_DATE");
           java.math.BigInteger sendendBL = (java.math.BigInteger)fileporg.get("CUR_SEND_END_TIME");
           long sendend = sendendBL.longValue();
           fileporg.put("START_VALID_DATE_STR",DateUtil.long2TimeString(start));
           fileporg.put("END_VALID_DATE_STR",DateUtil.long2TimeString(end));
           fileporg.put("CUR_SEND_END_TIME_STR",DateUtil.long2TimeString(sendend));
           java.math.BigInteger curSendSizeBL =  (java.math.BigInteger)fileporg.get("CUR_SEND_SIZE");
           long curSendSize = curSendSizeBL.longValue();
           java.math.BigInteger filesizeBL =  (java.math.BigInteger)fileporg.get("FILESIZE");
           long filesize = filesizeBL.longValue();
           float sch = 0;
           if(filesize>0)
               sch = ((float)curSendSize/(float)filesize);
           sch = this.getDecimalFormat(sch,"0.000");
           float newSch = sch*100;
           newSch = this.getDecimalFormat(newSch, "0.00");
           fileporg.put("SCH_DATA", sch);
           fileporg.put("SCH_DATA_STR", newSch+"%");
       }
     }
    }
    return dg;
  }
 
  private float getDecimalFormat(float data,String format){
    float formatData = (float)0.0;
    try {
        String strData = new DecimalFormat(format).format(data);
        Float FloData = new Float(strData);
        if(FloData!=null){
            formatData = FloData.floatValue();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return formatData;
}

  @Override
  public List<Map<String, Object>> listFileprogFile(int fileprogId) {
    String sql = "select m.* from tbl_media_fileprog_file as mf,tbl_media_file as m where mf.FILEPROG_ID=? and m.ID=mf.FILE_ID";
    return dbUtils.getList(sql, new Object[]{fileprogId});
  }

  @Override
  public List<Map<String, Object>> listFileprogByIds(String fileprogIds) {
    String sql = "select * from tbl_media_fileprog where ID in ( "+fileprogIds+" )";
    List<Map<String,Object>> list =  dbUtils.getList(sql, null);
    if(list!=null && list.size()>0){
      for(Map<String,Object> fileporg:list){
          long start = (Long)fileporg.get("START_VALID_DATE");
          long end = (Long)fileporg.get("END_VALID_DATE");
          java.math.BigInteger sendendBL = (java.math.BigInteger)fileporg.get("CUR_SEND_END_TIME");
          long sendend = sendendBL.longValue();
          fileporg.put("START_VALID_DATE_STR",DateUtil.long2TimeString(start));
          fileporg.put("END_VALID_DATE_STR",DateUtil.long2TimeString(end));
          fileporg.put("CUR_SEND_END_TIME_STR",DateUtil.long2TimeString(sendend));
          java.math.BigInteger curSendSizeBL =  (java.math.BigInteger)fileporg.get("CUR_SEND_SIZE");
          long curSendSize = curSendSizeBL.longValue();
          java.math.BigInteger filesizeBL =  (java.math.BigInteger)fileporg.get("FILESIZE");
          long filesize = filesizeBL.longValue();
          float sch = 0;
          if(filesize>0)
              sch = ((float)curSendSize/(float)filesize);
          sch = this.getDecimalFormat(sch,"0.000");
          float newSch = sch*100;
          newSch = this.getDecimalFormat(newSch, "0.00");
          fileporg.put("SCH_DATA", sch);
          fileporg.put("SCH_DATA_STR", newSch+"%");
      }
    }
    return list;
  }
  
  public DataGrid listFileproged(String startTime,String endTime,PageHelper ph) {
    StringBuffer HQL = new StringBuffer();
    HQL.append("select f.*,c.NAME as CHANNEL_NAME from tbl_media_fileprog as f,tbl_device_channel as c where");
    HQL.append(" f.CHANNEL_ID = c.ID and c.TYPE= 2");
    HQL.append(" and f.END_VALID_DATE <= "+new Date().getTime());

    if(StringUtils.isNotBlank(startTime)){
      Long start=DateUtil.dateString2Long(startTime);
      HQL.append(" and f.START_VALID_DATE >= "+start);
    }
    if(StringUtils.isNotBlank(endTime)){
      Long end=DateUtil.dateString2Long(endTime);
      HQL.append(" and f.START_VALID_DATE <= "+end);
    }
    DataGrid dg = dbUtils.Pagination(HQL.toString(), ph);
    if(dg!=null){
     List<HashMap<String,Object>> list =  (List<HashMap<String,Object>>)dg.getRows();
     if(list!=null && list.size()>0){
       for(HashMap<String,Object> fileporg:list){
           long start = (Long)fileporg.get("START_VALID_DATE");
           long end = (Long)fileporg.get("END_VALID_DATE");
           java.math.BigInteger sendendBL = (java.math.BigInteger)fileporg.get("CUR_SEND_END_TIME");
           long sendend = sendendBL.longValue();
           fileporg.put("START_VALID_DATE_STR",DateUtil.long2TimeString(start));
           fileporg.put("END_VALID_DATE_STR",DateUtil.long2TimeString(end));
           fileporg.put("CUR_SEND_END_TIME_STR",DateUtil.long2TimeString(sendend));
       }
     }
    }
    return dg;
  }

  @Override
  public void deleteFileprog(int[] ids) {
    String delIds = null;
    if(ids != null){
        for(long id : ids){
            delIds += ","+id;
        }
    }
    String sql = "delete from tbl_media_fileprog where ID in ("+delIds+")";
    dbUtils.delete(sql, null);
  }
  
  
}
