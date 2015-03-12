package com.smlearning.application.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.iactive.db.DataGridModel;
import cn.com.iactive.db.IACDB;

import com.smlearning.application.service.SysKeyService;
import com.smlearning.domain.activity.SysKeyActivity;
import com.smlearning.domain.entity.SysClass;
import com.smlearning.domain.entity.SysKey;
import com.smlearning.domain.entity.SysSubject;
import com.smlearning.domain.entity.enums.SysKeyEnum;
import com.smlearning.domain.vo.SysClassVO;
import com.smlearning.domain.vo.SysKeyVO;
import com.smlearning.domain.vo.SysSubjectVO;
import com.smlearning.infrastructure.utils.DBTableInfo;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.DateUtil;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 * 数据字典业务方法
 * @author Administrator
 */
@Service
public class SysKeyServiceImpl implements SysKeyService{

	static Logger logger = Logger.getLogger(SysKeyServiceImpl.class.getName());
	
	@Autowired
	private SysKeyActivity sysKeyActivity;
	
	@Autowired
	private IACDB<HashMap<String,Object>> iacDB;
	
	/**
	 * 修改系统术语
	 * @param sysKeyEnum 系统术语类型
	 * @param keyValue 系统术语值
	 * @throws NoSysKeyException 无此术语
	 * @throws ModifyFixedSysKeyException 不可修改系统内部的术语
	 */
	public void modifySysKey(Long id, String keyValue) throws Exception{
		sysKeyActivity.modifySysKey(id, keyValue);
	}
	
	/**
	 * 获取系统术语值
	 * @param sysKeyEnum 系统术语类型
	 * @return 系统术语值
	 * @throws NoSysKeyException 无此术语
	 */
	public LinkedHashMap<String, String> getSysKeyList(SysKeyEnum sysKeyEnum) throws Exception {
		return sysKeyActivity.getSysKeyList(sysKeyEnum);
	}
	
	/**
	 * 返回数据字典列表数据信息
	 * @param SysKeyVO
	 * @param ph
	 * @return
	 */
	public DataGrid querySysKeyPaning(SysKeyVO sysKeyVO, PageHelper ph) {
		return sysKeyActivity.querySysKeyPaning(sysKeyVO, ph);
	}
	
	/**
	 * 获得系统术语
	 * @param sysMessageId 消息编号
	 */
	public SysKey getSysKeyById(SysKeyEnum sysKeyEnum) throws Exception{
		return sysKeyActivity.getSysKeyById(sysKeyEnum);
	}
	
	/**
	 * 返回班级
	 * @param sysClassVO
	 * @param ph
	 * @return
	 */
	public DataGrid querySysClassPaning(SysClassVO sysClassVO, PageHelper ph) {
		return sysKeyActivity.querySysClassPaning(sysClassVO, ph);
	}
	
	/**
	 * 返回科目
	 * @param SysSubjectVO
	 * @param ph
	 * @return
	 */
	public DataGrid querySysSubjectPaning(SysSubjectVO sysSubjectVO, PageHelper ph) {
		return sysKeyActivity.querySysSubjectPaning(sysSubjectVO, ph);
	}
	
	/**
	 * 创建学习班
	 * @param name
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public SysClass createSysClass(String name, Long creator) throws Exception {
		return sysKeyActivity.createSysClass(name, creator);
	}
	
	/**
	 * 修改学习班
	 * @param name
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public SysClass modifySysClass(Long sysClassId, String name) throws Exception {
		return sysKeyActivity.modifySysClass(sysClassId, name);
	}
	
	/**
	 * 删除学习班
	 */
	public void removeSysClass(Long sysClassId) throws Exception {
		sysKeyActivity.removeSysClass(sysClassId);
	}
	
	/**
	 * 创建科目
	 * @param name
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public SysSubject createSysSubject(String name, Long creator) throws Exception {
		return sysKeyActivity.createSysSubject(name, creator);
	}
	
	/**
	 * 修改科目
	 * @param name
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public SysSubject modifySysSubject(Long sysSubjectId, String name) throws Exception {
		return sysKeyActivity.modifySysSubject(sysSubjectId, name);
	}
	
	/**
	 * 删除科目
	 */
	public void removeSysSubject(Long sysSubjectId) throws Exception {
		sysKeyActivity.removeSysSubject(sysSubjectId);
	}
	
	/**
	 * 获得班级
	 * @return
	 * @throws Exception
	 */
	public SysClass getSysClass(Long sysClassId) throws Exception {
		return sysKeyActivity.getSysClass(sysClassId);
	}
	
	/**
	 * 获得科目
	 * @return
	 * @throws Exception
	 */
	public SysSubject getSysSubject(Long sysSubjectId) throws Exception {
		return sysKeyActivity.getSysSubject(sysSubjectId);
	}
	
	/**
	 * 获得班级
	 * @return
	 */
	public List<SysClass> queryAllByClass() {
		return sysKeyActivity.queryAllByClass();
	}
	
	/**
	 * 获得科目
	 * @return
	 */
	public List<SysSubject> queryAllBySubject() {
		return sysKeyActivity.queryAllBySubject();
	}
	
	
	/**
	 * 创建系统术语
	 * @param sysKeyEnum 系统术语类型
	 * @param keyValue 系统术语值
	 * @throws NoSysKeyException 无此术语
	 * @throws ModifyFixedSysKeyException 不可修改系统内部的术语
	 */
	public SysKey createSysKey(String keyValue) throws Exception{
		return sysKeyActivity.createSysKey(keyValue);
	}

  @Override
  public HashMap<String, Object> getGradeClass(DataGridModel dm,long gradeId) {
    HashMap<String,Object> params = new HashMap<String, Object>();
    if(gradeId > 0)
      params.put("gradeId",gradeId);
    return iacDB.getDataGrid("getGradeClass", dm,params);
  }

  
  @Override
  public boolean saveClass(HashMap<String, String> sysclass) {
    try {
      sysclass.put("created_time",DateUtil.formateDateToString(new Date()));
      iacDB.insertDynamic(DBTableInfo.SYS_CLASS_TABLE_NAME, sysclass);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
	
	
}
