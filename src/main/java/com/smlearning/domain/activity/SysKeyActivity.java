package com.smlearning.domain.activity;


import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smlearning.domain.entity.SysClass;
import com.smlearning.domain.entity.SysClassExample;
import com.smlearning.domain.entity.SysKey;
import com.smlearning.domain.entity.SysKeyExample;
import com.smlearning.domain.entity.SysSubject;
import com.smlearning.domain.entity.SysSubjectExample;
import com.smlearning.domain.entity.enums.SysKeyEnum;
import com.smlearning.domain.vo.SysClassVO;
import com.smlearning.domain.vo.SysKeyVO;
import com.smlearning.domain.vo.SysSubjectVO;
import com.smlearning.infrastructure.dao.SysClassMapper;
import com.smlearning.infrastructure.dao.SysKeyMapper;
import com.smlearning.infrastructure.dao.SysSubjectMapper;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.DataTypeConvertor;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 * 数据字典领域活动 聚合
 * @author Administrator
 */
@Repository
public class SysKeyActivity {

	static Logger logger = Logger.getLogger(SysKeyActivity.class.getName());
	
	//数据字典基础层
	@Autowired
	private SysKeyMapper sysKeyMapper;
	
	//班级基础层
	@Autowired
	private SysClassMapper sysClassMapper;
	
	//课程科目基础层
	@Autowired
	private SysSubjectMapper sysSubjectMapper;
	
	/**
	 * 返回班级
	 * @param sysClassVO
	 * @param ph
	 * @return
	 */
	public DataGrid querySysClassPaning(SysClassVO sysClassVO, PageHelper ph) {
		logger.info("querySysClassPaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<SysClass> sList = null;
		int sCount = 0;
		try{
			SysClassExample mEx = new SysClassExample();
			
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  sysClassMapper.selectByExamplePaging(mEx); 
			sCount = sysClassMapper.countByExample(mEx);
		}catch(Exception ex){
			logger.error("Error of querySysClassPaning",ex);
		}
		logger.info("querySysClassPaning result :" +  sList.size() );
		logger.info("querySysClassPaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	/**
	 * 返回科目
	 * @param SysSubjectVO
	 * @param ph
	 * @return
	 */
	public DataGrid querySysSubjectPaning(SysSubjectVO sysSubjectVO, PageHelper ph) {
		logger.info("querySysSubjectPaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<SysSubject> sList = null;
		int sCount = 0;
		try{
			SysSubjectExample mEx = new SysSubjectExample();
			
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  sysSubjectMapper.selectByExamplePaging(mEx); 
			sCount = sysSubjectMapper.countByExample(mEx);
		}catch(Exception ex){
			logger.error("Error of querySysSubjectPaning",ex);
		}
		logger.info("querySysSubjectPaning result :" +  sList.size() );
		logger.info("querySysSubjectPaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	/**
	 * 获得班级
	 * @return
	 */
	public List<SysClass> queryAllByClass() {
		
		List<SysClass> list = sysClassMapper.selectByExample(null);
	
		return list;
	}
	
	
	/**
	 * 获得科目
	 * @return
	 */
	public List<SysSubject> queryAllBySubject() {
		
		List<SysSubject> list = sysSubjectMapper.selectByExample(null);
	
		return list;
	}
	
	
	/**
	 * 创建学习班
	 * @param name
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public SysClass createSysClass(String name, Long creator) throws Exception {
		
		SysClass sysClass = new SysClass();
		
		sysClass.setName(name);
		sysClass.setCreator(creator);
		sysClass.setCreatedTime(new Date());
		
		this.sysClassMapper.insert(sysClass);
		
		return sysClass;
	}
	
	/**
	 * 修改学习班
	 * @param name
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public SysClass modifySysClass(Long sysClassId, String name) throws Exception {
		
		SysClass sysClass = this.sysClassMapper.selectByPrimaryKey(sysClassId);
		
		//判断是否存在此分类
		if (sysClass == null) {
			throw new Exception("无此学习班");
		}
		
		sysClass.setName(name);
		sysClass.setCreatedTime(new Date());
		
		this.sysClassMapper.updateByPrimaryKeySelective(sysClass);
		
		return sysClass;
	}
	
	/**
	 * 获得班级
	 * @return
	 * @throws Exception
	 */
	public SysClass getSysClass(Long sysClassId) throws Exception {
		
		SysClass sysClass = this.sysClassMapper.selectByPrimaryKey(sysClassId);
		
		//判断是否存在此分类
		if (sysClass == null) {
			throw new Exception("无此学习班");
		}
		
		return sysClass;
	}
	
	/**
	 * 删除学习班
	 */
	public void removeSysClass(Long sysClassId) throws Exception {

		SysClass sysClass = this.sysClassMapper.selectByPrimaryKey(sysClassId);
		
		//判断是否存在此分类
		if (sysClass == null) {
			throw new Exception("无此学习班");
		}
		
		this.sysClassMapper.deleteByPrimaryKey(sysClassId);
		
	}
	
	/**
	 * 创建科目
	 * @param name
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public SysSubject createSysSubject(String name, Long creator) throws Exception {
		
		SysSubject sysSubject = new SysSubject();
		
		sysSubject.setName(name);
		sysSubject.setCreator(creator);
		sysSubject.setCreatedTime(new Date());
		
		this.sysSubjectMapper.insert(sysSubject);
		
		return sysSubject;
	}
	
	/**
	 * 修改科目
	 * @param name
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public SysSubject modifySysSubject(Long sysSubjectId, String name) throws Exception {
		
		SysSubject sysSubject = this.sysSubjectMapper.selectByPrimaryKey(sysSubjectId);
		
		//判断是否存在此分类
		if (sysSubject == null) {
			throw new Exception("无此学习班");
		}
		
		sysSubject.setName(name);
		sysSubject.setCreatedTime(new Date());
		
		this.sysSubjectMapper.updateByPrimaryKeySelective(sysSubject);
		
		return sysSubject;
	}
	
	/**
	 * 获得科目
	 * @param name
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public SysSubject getSysSubject(Long sysSubjectId) throws Exception {
		
		SysSubject sysSubject = this.sysSubjectMapper.selectByPrimaryKey(sysSubjectId);
		
		//判断是否存在此分类
		if (sysSubject == null) {
			throw new Exception("无此科目");
		}
		
		return sysSubject;
	}
	
	
	/**
	 * 删除科目
	 */
	public void removeSysSubject(Long sysSubjectId) throws Exception {

		SysSubject sysSubject = this.sysSubjectMapper.selectByPrimaryKey(sysSubjectId);
		
		//判断是否存在此分类
		if (sysSubject == null) {
			throw new Exception("无此学习班");
		}
		
		this.sysSubjectMapper.deleteByPrimaryKey(sysSubjectId);
		
	}
	
	/**
	 * 返回数据字典列表数据信息
	 * @param SysKeyVO
	 * @param ph
	 * @return
	 */
	public DataGrid querySysKeyPaning(SysKeyVO sysKeyVO, PageHelper ph) {
		logger.info("querySysKeyPaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<SysKey> sList = null;
		int sCount = 0;
		try{
			SysKeyExample mEx = new SysKeyExample();
			
			mEx.or().andCanModifyEqualTo(DataTypeConvertor.booleanToInteger(true));
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			mEx.setOrderByClause("ID ASC");
			sList =  sysKeyMapper.selectByExamplePaging(mEx); 
			sCount = sysKeyMapper.countByExample(mEx);
		}catch(Exception ex){
			logger.error("Error of querySysKeyPaning",ex);
		}
		logger.info("querySysKeyPaning result :" +  sList.size() );
		logger.info("querySysKeyPaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	/**
	 * 修改系统术语
	 * @param sysKeyEnum 系统术语类型
	 * @param keyValue 系统术语值
	 * @throws NoSysKeyException 无此术语
	 * @throws ModifyFixedSysKeyException 不可修改系统内部的术语
	 */
	public void modifySysKey(Long id, String keyValue) throws Exception{
		
		SysKey sysKey = this.sysKeyMapper.selectByPrimaryKey(id);
		
		//判断是否存在此系统术语
		if (sysKey == null) {
			throw new Exception("无此系统术语！");
		}
		
		//判断是否修改不可更改的系统术语
		if (sysKey.getCanModify().equals(DataTypeConvertor.booleanToInteger(false))) {
			throw new Exception("不可修改系统内部的术语！");
		}
		
		//更新术语值
		sysKey.setKeyValue(keyValue);
		
		logger.info("keyValue :" +  keyValue);
		
		this.sysKeyMapper.updateByPrimaryKey(sysKey);
	}
	
	/**
	 * 创建系统术语
	 * @param sysKeyEnum 系统术语类型
	 * @param keyValue 系统术语值
	 * @throws NoSysKeyException 无此术语
	 * @throws ModifyFixedSysKeyException 不可修改系统内部的术语
	 */
	public SysKey createSysKey(String keyValue) throws Exception{
		
		SysKey sysKey = new SysKey();
		
		sysKey.setId(Long.parseLong(SysKeyEnum.SERVER_IP.toValue().toString()));
		
		sysKey.setKeyName("serverIP");
		//更新术语值
		sysKey.setKeyValue(keyValue);
		sysKey.setCanModify(DataTypeConvertor.booleanToInteger(false));
		
		logger.info("keyValue :" +  keyValue);
		
		this.sysKeyMapper.insert(sysKey);
		
		return sysKey;
		
	}
	
	/**
	 * 获取系统术语值
	 * @param sysKeyEnum 系统术语类型
	 * @return 系统术语值
	 * @throws NoSysKeyException 无此术语
	 */
	public LinkedHashMap<String, String> getSysKeyList(SysKeyEnum sysKeyEnum) throws Exception {
		
		SysKey sysKey = this.sysKeyMapper.selectByPrimaryKey(Long.parseLong(sysKeyEnum.toValue().toString()));
		
		//判断是否存在此系统术语
		if (sysKey == null) {
			throw new Exception("无此系统术语！");
		}
		
		String[] list = sysKey.getKeyValue().split(";");
		LinkedHashMap<String, String> rt = new LinkedHashMap<String, String>();
		
		for (String item : list ) {
			rt.put(item, item);
		}
		
		return rt;
	}
	
	
	/**
	 * 获得系统术语
	 * @param sysMessageId 消息编号
	 */
	public SysKey getSysKeyById(SysKeyEnum sysKeyEnum) throws Exception{
		
		SysKey sysKey = this.sysKeyMapper.selectByPrimaryKey(Long.parseLong(sysKeyEnum.toValue().toString()));
		
		//判断是否存在此系统术语
		if (sysKey == null) {
			throw new Exception("无此系统术语！");
		}
		
		return sysKey;
	}
}
