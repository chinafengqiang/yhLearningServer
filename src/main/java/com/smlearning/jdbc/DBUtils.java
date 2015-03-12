package com.smlearning.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;


public class DBUtils {
  @Resource
  private JdbcTemplate jdbcTemplate;
  
  public DataGrid Pagination(String sql,String orderBySql,Object[] args,int offset,int limit){
    if(sql==null||"".equals(sql)){
      throw new IllegalArgumentException("Pagination.sql is empty,please initial it first. ");
    }
    StringBuffer totalSQL = new StringBuffer(" SELECT count(*) FROM ( ");
    totalSQL.append(sql);
    totalSQL.append(" ) totalTable ");
    
    StringBuffer paginationSQL = new StringBuffer(sql);
    if(orderBySql != null){
      paginationSQL.append(" "+orderBySql);
    }
    paginationSQL.append(" limit "+offset+","+limit);
    
    int totals = 0;
    List<Map<String,Object>> items = null;
    if(args!=null&&args.length>0){
      totals = jdbcTemplate.queryForInt(totalSQL.toString(), args);
      items = jdbcTemplate.queryForList(paginationSQL.toString(), args);
    }else{
      totals = jdbcTemplate.queryForInt(totalSQL.toString());
      items = jdbcTemplate.queryForList(paginationSQL.toString());
    }
   
    DataGrid dataGrid = new DataGrid();
    dataGrid.setTotal((long)totals);
    dataGrid.setRows(items);
    return dataGrid;
  }
  
  public DataGrid Pagination(String sql,Object[] args,int offset,int limit){
    if(sql==null||"".equals(sql)){
      throw new IllegalArgumentException("Pagination.sql is empty,please initial it first. ");
    }
    StringBuffer totalSQL = new StringBuffer(" SELECT count(*) FROM ( ");
    totalSQL.append(sql);
    totalSQL.append(" ) totalTable ");
    
    StringBuffer paginationSQL = new StringBuffer(sql);
    paginationSQL.append(" limit "+offset+","+limit);
    
    int totals = 0;
    List<Map<String,Object>> items = null;
    if(args!=null&&args.length>0){
      totals = jdbcTemplate.queryForInt(totalSQL.toString(), args);
      items = jdbcTemplate.queryForList(paginationSQL.toString(), args);
    }else{
      totals = jdbcTemplate.queryForInt(totalSQL.toString());
      items = jdbcTemplate.queryForList(paginationSQL.toString());
    }

    DataGrid dataGrid = new DataGrid();
    dataGrid.setTotal((long)totals);
    dataGrid.setRows(items);
    return dataGrid;
  }
  
  public DataGrid Pagination(String sql,int offset,int limit){
    return this.Pagination(sql, null, offset, limit);
  }
  
  public DataGrid Pagination(String tableName,String searchers,PageHelper ph){
    StringBuffer sqlBuffer = new StringBuffer("select ");
    if(searchers != null && !"".equals(searchers)){
      sqlBuffer.append(searchers);
    }else{
      sqlBuffer.append("*");
    }
    sqlBuffer.append(" from "+tableName);
    int offset = (ph.getPage() - 1 ) * ph.getRows();
    int limit = ph.getRows();
    String orderBySql = "order by "+ph.getSort()+" "+ph.getOrder();
    return Pagination(sqlBuffer.toString(),orderBySql,null,offset, limit);
  }
  
  public DataGrid Pagination(String sql,PageHelper ph){
    int offset = (ph.getPage() - 1 ) * ph.getRows();
    int limit = ph.getRows();
    String orderBySql = "order by "+ph.getSort()+" "+ph.getOrder();
    return Pagination(sql,orderBySql,null,offset, limit);
  }
  
  public DataGrid Pagination(String sql,Object[] args,PageHelper ph){
    int offset = (ph.getPage() - 1 ) * ph.getRows();
    int limit = ph.getRows();
    String orderBySql = "order by "+ph.getSort()+" "+ph.getOrder();
    return Pagination(sql,orderBySql,args,offset, limit);
  }
  
  public List<Map<String,Object>> getList(String sql,Object[] args){
    return jdbcTemplate.queryForList(sql, args);
  }
  
  public void insert(String sql,Object args){
    jdbcTemplate.update(sql, args);
  }
  
  public void insert(String tableName,HashMap<String,String> argsMap){
    Object[] args = new Object[argsMap.size()];
    jdbcTemplate.update(getInsertSql(tableName,argsMap, args), args);
  }
  
  /**
   * 
   * 增加并且获取主键
   * @param sql sql语句
   * @param params 参数
   * @return 主键
   */
  public  long insertAndGetKey(final String sql) {
      KeyHolder keyHolder = new GeneratedKeyHolder();
      jdbcTemplate.update(new PreparedStatementCreator() {
          public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
              
              PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
             
              return ps;
          }
      }, keyHolder);
      
      Long generatedId = keyHolder.getKey().longValue(); 
      return generatedId;
  }
  
  private String getInsertSql(String tableName,HashMap<String,String> argsMap,Object[] args){
    StringBuffer sqlBuffer = new StringBuffer(); 
    if(argsMap != null){
          Iterator<Entry<String,String>> iter = argsMap.entrySet().iterator();
          sqlBuffer.append("insert into "+tableName+" (");
          String pmtSql = "";
          int count = 0;
          int total = argsMap.size();
          while(iter.hasNext()){
            Entry<String,String> entry = iter.next();
            String key = entry.getKey();
            String value = entry.getValue();
            sqlBuffer.append(key);
            pmtSql += "?";
            if(count != (total-1)){
              sqlBuffer.append(",");
              pmtSql += ",";
            }

            args[count] = value;
            count++;
          }
          sqlBuffer.append(") values ("+pmtSql+")");
     }
    return sqlBuffer.toString();
  }
  
  
  public Map<String,Object> getObject(String sql){
    return jdbcTemplate.queryForMap(sql);
  }
  
  public Map<String,Object> getObject(String sql,Object[] args){
    return jdbcTemplate.queryForMap(sql,args);
  }
  
  public void update(String tableName,HashMap<String,String> argsMap,String updateKey){
    Object[] args = new Object[argsMap.size()];
    jdbcTemplate.update(getUpdateSql(tableName,updateKey,argsMap, args), args);
  }
  
  private String getUpdateSql(String tableName,String updateKey,HashMap<String,String> argsMap,Object[] args){
    StringBuffer sqlBuffer = new StringBuffer(); 
    if(argsMap != null){
          int total = argsMap.size();
          
          String updateKeyValue = argsMap.get(updateKey);
          argsMap.remove(updateKey);
          
          Iterator<Entry<String,String>> iter = argsMap.entrySet().iterator();
          sqlBuffer.append("update "+tableName+" set ");
          int count = 0;
         
          args[total-1] = updateKeyValue;
          
          while(iter.hasNext()){
            Entry<String,String> entry = iter.next();
            String key = entry.getKey();
            String value = entry.getValue();
            sqlBuffer.append(key+" = ?");
            if(count != (total-2)){
              sqlBuffer.append(",");
            }
            
            args[count] = value;
            count++;
          }
          sqlBuffer.append(" where "+updateKey+" = ?");
     }
    return sqlBuffer.toString();
  }
  
  public void delete(String sql,Object[] args){
    jdbcTemplate.update(sql, args);
  }
  
  public void delete(String tableName,String key,Object value){
    jdbcTemplate.update("delete from "+tableName+" where "+key+" = ?", new Object[]{value});
  }
  
  public void batchUpdate(String sql,List<Object[]> batchArgs){
    jdbcTemplate.batchUpdate(sql, batchArgs);
  }
  
}
