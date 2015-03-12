package com.smlearning.application.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import cn.com.iactive.db.DataGridModel;

import com.smlearning.domain.entity.SysClass;
import com.smlearning.domain.entity.SysKey;
import com.smlearning.domain.entity.SysSubject;
import com.smlearning.domain.entity.enums.SysKeyEnum;
import com.smlearning.domain.vo.SysClassVO;
import com.smlearning.domain.vo.SysKeyVO;
import com.smlearning.domain.vo.SysSubjectVO;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;



/**
 * 数据字典接口
 * @author Administrator
 */
public interface SysKeyService {

	/**
	 * 修改系统术语
	 * @param sysKeyEnum 系统术语类型
	 * @param keyValue 系统术语值
	 * @throws NoSysKeyException 无此术语
	 * @throws ModifyFixedSysKeyException 不可修改系统内部的术语
	 */
	public void modifySysKey(Long id, String keyValue) throws Exception;
		
	/**
	 * 获取系统术语值
	 * @param sysKeyEnum 系统术语类型
	 * @return 系统术语值
	 * @throws NoSysKeyException 无此术语
	 */
	public LinkedHashMap<String, String> getSysKeyList(SysKeyEnum sysKeyEnum) throws Exception;
	
	/**
	 * 返回数据字典列表数据信息
	 * @param SysKeyVO
	 * @param ph
	 * @return
	 */
	public DataGrid querySysKeyPaning(SysKeyVO sysKeyVO, PageHelper ph);
	
	/**
	 * 获得系统术语
	 * @param sysMessageId 消息编号
	 */
	public SysKey getSysKeyById(SysKeyEnum sysKeyEnum) throws Exception;
	
	/**
	 * 返回班级
	 * @param sysClassVO
	 * @param ph
	 * @return
	 */
	public DataGrid querySysClassPaning(SysClassVO sysClassVO, PageHelper ph);
	
	/**
	 * 返回科目
	 * @param SysSubjectVO
	 * @param ph
	 * @return
	 */
	public DataGrid querySysSubjectPaning(SysSubjectVO sysSubjectVO, PageHelper ph);
	
	/**
	 * 创建学习班
	 * @param name
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public SysClass createSysClass(String name, Long creator) throws Exception;
	
	/**
	 * 修改学习班
	 * @param name
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public SysClass modifySysClass(Long sysClassId, String name) throws Exception;
	
	/**
	 * 删除学习班
	 */
	public void removeSysClass(Long sysClassId) throws Exception;
	
	/**
	 * 创建科目
	 * @param name
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public SysSubject createSysSubject(String name, Long creator) throws Exception;
	
	/**
	 * 修改科目
	 * @param name
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public SysSubject modifySysSubject(Long sysSubjectId, String name) throws Exception;
	
	/**
	 * 删除科目
	 */
	public void removeSysSubject(Long sysSubjectId) throws Exception;
	
	/**
	 * 获得班级
	 * @return
	 * @throws Exception
	 */
	public SysClass getSysClass(Long sysClassId) throws Exception;
	
	/**
	 * 获得科目
	 * @return
	 * @throws Exception
	 */
	public SysSubject getSysSubject(Long sysSubjectId) throws Exception;
	
	/**
	 * 获得班级
	 * @return
	 */
	public List<SysClass> queryAllByClass();
	
	/**
	 * 获得科目
	 * @return
	 */
	public List<SysSubject> queryAllBySubject();
	
	/**
	 * 创建系统术语
	 * @param sysKeyEnum 系统术语类型
	 * @param keyValue 系统术语值
	 * @throws NoSysKeyException 无此术语
	 * @throws ModifyFixedSysKeyException 不可修改系统内部的术语
	 */
	public SysKey createSysKey(String keyValue) throws Exception;
	
	public HashMap<String,Object> getGradeClass(DataGridModel dm,long gradeId);

	 public boolean saveClass(HashMap<String, String> sysclass);
}
