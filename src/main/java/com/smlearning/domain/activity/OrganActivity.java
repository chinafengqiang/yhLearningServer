package com.smlearning.domain.activity;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smlearning.domain.entity.Organ;
import com.smlearning.domain.entity.OrganExample;
import com.smlearning.domain.entity.enums.OrganDegreeEnum;
import com.smlearning.domain.vo.OrganVO;
import com.smlearning.infrastructure.dao.OrganMapper;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 * 组织机构领域活动 聚合
 * @author Administrator
 */
@Repository
public class OrganActivity {

	static Logger logger = Logger.getLogger(OrganActivity.class.getName());
	
	//组织机构基础层
	@Autowired
	private OrganMapper organMapper;
	
	//管理员活动层
	@Autowired
	private ManagerActivity managerActivity;
	
	/**
	 * 返回上级组织机构列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryHigherOrganPaning(OrganVO organVO, PageHelper ph) {
		logger.info("queryHigherOrganPaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<Organ> sList = null;
		int sCount = 0;
		try{
			OrganExample mEx = new OrganExample();
			OrganExample.Criteria criteria = mEx.createCriteria();
			criteria.andDegreeEqualTo(OrganDegreeEnum.HIGHER.toValue());
			
			if(StringUtils.isNotBlank(organVO.getName())){
				
				String nameLike = "%" + organVO.getName() + "%";
				criteria.andNameLike(nameLike);
			}
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  organMapper.selectByExamplePaging(mEx); 
			sCount = organMapper.countByExample(mEx);
		}catch(Exception ex){
			logger.error("Error of queryHigherOrganPaning",ex);
		}
		logger.info("queryHigherOrganPaning result :" +  sList.size() );
		logger.info("queryHigherOrganPaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	
	/**
	 * 返回下级组织机构列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryLowerOrganPaning(OrganVO organVO, PageHelper ph) {
		logger.info("queryLowerOrganPaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<Organ> sList = null;
		int sCount = 0;
		try{
			OrganExample mEx = new OrganExample();
			OrganExample.Criteria criteria = mEx.createCriteria();
			criteria.andDegreeEqualTo(OrganDegreeEnum.LOWER.toValue());
			if(StringUtils.isNotBlank(organVO.getName())){
				
				String nameLike = "%" + organVO.getName() + "%";
				criteria.andNameLike(nameLike);
			}
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  organMapper.selectByExamplePaging(mEx); 
			sCount = organMapper.countByExample(mEx);
		}catch(Exception ex){
			logger.error("Error of queryLowerOrganPaning",ex);
		}
		logger.info("queryLowerOrganPaning result :" +  sList.size() );
		logger.info("queryLowerOrganPaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	/**
	 * 创建本单位
	 * @param name 单位名称
	 * @param grade 单位级别
	 * @throws RepeatedOrganNameException 已存在本单位!
	 */
	private void createSelfOrgan(String name, String grade) throws Exception {
	
		
		OrganExample example = new OrganExample();
		example.or().andDegreeEqualTo(OrganDegreeEnum.SELF.toValue());
		
		//判断是否已经存在本单位
		if(this.organMapper.selectByExample(example).size() > 0){
			throw new Exception("已存在本单位!");
		}
				
		//创建本单位记录
		Organ organ = new Organ();
		organ.setName(name);
		organ.setGrade(grade);
		organ.setDegree(OrganDegreeEnum.SELF.toValue());
		organ.setServerPort("80");
		
		this.organMapper.insert(organ);
		
	}
	
	/**
	 * 创建上级单位
	 * @throws RepeatedOrganNameException
	 */
	private void createHigherOrgan() throws Exception {
		
		OrganExample example = new OrganExample();
		example.or().andDegreeEqualTo(OrganDegreeEnum.HIGHER.toValue());
		
		//判断是否已经存在本单位
		if(this.organMapper.selectByExample(example).size() > 0){
			throw new Exception("已存在上级单位!");
		}
		
		//创建上级单位记录
		Organ organ = new Organ();
		organ.setName("上级单位");
		organ.setGrade("上级");
		organ.setDegree(OrganDegreeEnum.HIGHER.toValue());
		organ.setServerPort("80");
		
		this.organMapper.insert(organ);
	}
	
	/**
	 * 初始化系统
	 * @param organName 单位名称
	 * @param organGrade 单位级别
	 * @throws RepeatedManagerNameException
	 * @throws RepeatedOrganNameException 
	 */
	public void init(String organName, String organGrade) throws Exception {
	
		OrganExample example = new OrganExample();
		example.or().andDegreeEqualTo(OrganDegreeEnum.SELF.toValue());
		
		//判断是否已经初始化了
		if(this.organMapper.selectByExample(example) != null){
			return;
		}
		
		//创建本单位
		createSelfOrgan(organName, organGrade);

		//创建上级单位
		createHigherOrgan();

		//创建系统管理员
		this.managerActivity.createSuperManager();
	}
	
	/**
	 * 修改本单位
	 * @param name 单位名称
	 * @param grade 单位级别
	 * @param department 部门
	 * @param linkman 联系人
	 * @param tel 电话
	 * @return
	 * @throws NoOrganException 无此单位
	 */
	public Organ modifySelfOrgan(String name, String grade, String department, String linkman, String tel) throws Exception {
		
		Organ organ = null;
		
		OrganExample example = new OrganExample();
		example.or().andDegreeEqualTo(OrganDegreeEnum.SELF.toValue());
		
		List<Organ> list = this.organMapper.selectByExample(example);
		
		if(list.size() > 0){
			organ = list.get(0);
		}
		
		//判断是否存在本单位记录
		if (organ == null) {
			throw new Exception("无本单位记录!");
		}
		
		//修改本单位信息
		organ.setName(name);
		organ.setGrade(grade);
		organ.setLinkman(linkman);
		organ.setTel(tel);
		
		this.organMapper.updateByPrimaryKey(organ);
		
		return organ;
	}
	
	/**
	 * 修改上级单位
	 * @param name 单位编号
	 * @param name 单位名称
	 * @param grade 单位级别
	 * @param department 部门
	 * @param linkman 联系人
	 * @param tel 电话
	 * @param serverIp 服务器IP
	 * @param serverPort 服务器端口
	 * @return
	 * @throws NoOrganException 无此单位
	 * @throws RepeatedOrganNameException 
	 */
	public Organ modifyHigherOrgan(Long organId, String name, String grade, String department, String linkman, String tel, String serverIp, String serverPort) throws Exception {
		
		//获取单位对像
		Organ organ = this.organMapper.selectByPrimaryKey(organId);
		
		if (organ == null) {
			throw new Exception("无此上级单位!");
		}
		
		//判断是否单位名重名
		if (!organ.getName().equals(name)) {
			
			//查询用户名是否存在
			OrganExample exampleName = new OrganExample();
			exampleName.or().andNameEqualTo(name);
				
			if (this.organMapper.selectByExample(exampleName).size() > 0) {
				throw new Exception("单位名称重复!");
			}
		}
		
		//修改上级单位信息
		organ.setName(name);
		organ.setGrade(grade);
		organ.setLinkman(linkman);
		organ.setTel(tel);
		organ.setServerIp(serverIp);
		organ.setServerPort(serverPort);
		
		this.organMapper.updateByPrimaryKey(organ);
		
		return organ;
	}
	

	/**
	 * 创建下级单位
	 * @param name 单位名称
	 * @param grade 单位级别
	 * @param department 部门
	 * @param linkman 联系人
	 * @param tel 电话
	 * @param serverIp 服务器ip
	 * @param serverPort 服务器端口
	 * @return
	 * @throws RepeatedOrganNameException
	 */
	public Organ createLowerOrgan(String name, String grade, String department, String linkman, String tel, String serverIp, String serverPort) throws Exception {
		
		
		//查询用户名是否存在
		OrganExample exampleName = new OrganExample();
		OrganExample.Criteria criteria = exampleName.createCriteria();
		
		criteria.andDegreeEqualTo(OrganDegreeEnum.LOWER.toValue());
		criteria.andNameEqualTo(name);
	//	exampleName.or().andNameEqualTo(name);
		//exampleName.or().andDegreeEqualTo(OrganDegreeEnum.HIGHER.toValue());
		//判断是否存在上级单位记录
		if (this.organMapper.selectByExample(exampleName).size() > 0) {
			throw new Exception("下级单位名称重复!");
		}
		
		Organ organ = new Organ();
		
		//新增下级单位信息
		organ.setName(name);
		organ.setGrade(grade);
		organ.setDegree(OrganDegreeEnum.LOWER.toValue());
		organ.setLinkman(linkman);
		organ.setTel(tel);
		organ.setServerIp(serverIp);
		organ.setServerPort(serverPort);
		
		this.organMapper.insert(organ);
		
		return organ;
	}
	
	/**
	 * 修改下级单位
	 * @param organId 单位编号
	 * @param name 单位名称
	 * @param grade 单位级别
	 * @param department 部门
	 * @param linkman 联系人
	 * @param tel 电话
	 * @param serverIp 服务器ip
	 * @param serverPort 服务器端口
	 * @return
	 * @throws NoOrganException
	 * @throws RepeatedOrganNameException
	 */
	public Organ modifyLowerOrgan(Long organId, String name, String grade, String department, String linkman, String tel, String serverIp, String serverPort) throws Exception {
		
		//获取单位对像
		Organ organ = this.organMapper.selectByPrimaryKey(organId);
		
		if (organ == null) {
			throw new Exception("无此下级单位!");
		}
		
		//判断是否单位名重名
		if (!organ.getName().equals(name)) {
			
			//查询用户名是否存在
			OrganExample exampleName = new OrganExample();
			exampleName.or().andNameEqualTo(name);
				
			if (this.organMapper.selectByExample(exampleName).size() > 0) {
				throw new Exception("单位名称重复!");
			}
			
		}
		
		organ.setName(name);
		organ.setGrade(grade);
		organ.setLinkman(linkman);
		organ.setTel(tel);
		organ.setServerIp(serverIp);
		organ.setServerPort(serverPort);
		
		this.organMapper.updateByPrimaryKey(organ);
		
		return organ;
	}
	
	/**
	 * 删除下级单位
	 * @param organId 单位编号
	 * @throws NoOrganException 
	 * @throws InvalidAccessException
	 */
	public void removeLowerOrgan(Long organId) throws Exception {
		
		//获取单位对像
		Organ organ = this.organMapper.selectByPrimaryKey(organId);
		
		if (organ == null) {
			throw new Exception("无此下级单位!");
		}
		
		if (!organ.getDegree().equals(OrganDegreeEnum.LOWER.toValue())) {
			throw new Exception("只能删除下级单位!");
		}
		
		this.organMapper.deleteByPrimaryKey(organId);
	}
	
	/**
	 * 测试连接
	 * @param organId 单位编号
	 * @throws NoOrganException
	 * @throws ConnectOrganErrorException 连接出错
	 */
	public void tryConnectOrgan(Long organId) throws Exception {
		
//		Organ organ = this.systemModelFactoryFacade.getOrganModelFactory().findById(organId);
//		
//		if (organ == null) {
//			throw new NoOrganException("无此单位!");
//		}
//		
//		try {
//			String url = "http://" + organ.getServerIp() + ":" + organ.getServerPort() + SysConfig.urlTryConnect;
//			if (!HtmlClientUtil.post(url).setParameter("a", "1").html().trim().equals(SysConfig.sendDataSuccessFlag)) {
//				throw new ConnectOrganErrorException(); 
//			}
//		} catch (Exception e) {
//			throw new ConnectOrganErrorException();
//		}
	}
	
	/**
	 * 创建上级单位
	 * @param name 单位名称
	 * @param grade 单位级别
	 * @param department 部门
	 * @param linkman联系人
	 * @param tel电话
	 * @param serverIp服务器IP
	 * @param serverPort服务器端口
	 * @return
	 * @throws RepeatedOrganNameException
	 */
	public Organ createHigherOrgan(String name, String grade, String department, String linkman, String tel, String serverIp, String serverPort) throws Exception{
		
		
		//查询用户名是否存在
		OrganExample exampleName = new OrganExample();
		OrganExample.Criteria criteria = exampleName.createCriteria();
		
		criteria.andDegreeEqualTo(OrganDegreeEnum.HIGHER.toValue());
		criteria.andNameEqualTo(name);
	//	exampleName.or().andNameEqualTo(name);
		//exampleName.or().andDegreeEqualTo(OrganDegreeEnum.HIGHER.toValue());
		//判断是否存在上级单位记录
		if (this.organMapper.selectByExample(exampleName).size() > 0) {
			throw new Exception("上级单位名称重复!");
		}
		
		//新增上级单位信息
		Organ organ = new Organ();
		organ.setName(name);
		organ.setGrade(grade);
		organ.setDegree(OrganDegreeEnum.HIGHER.toValue());
		organ.setLinkman(linkman);
		organ.setTel(tel);
		organ.setServerIp(serverIp);
		organ.setServerPort(serverPort);
		
		this.organMapper.insert(organ);
		
		return organ;
	}
	
	/**
	 * 删除上级单位
	 * @param organId 单位编号
	 * @throws NoOrganException 
	 * @throws InvalidAccessException
	 */
	public void removeHigherOrgan(Long organId) throws Exception {
		
		//获取单位对像
		Organ organ = this.organMapper.selectByPrimaryKey(organId);
		
		if (organ == null) {
			throw new Exception("无此上级单位!");
		}
		
		if (!organ.getDegree().equals(OrganDegreeEnum.HIGHER.toValue())) {
			throw new Exception("只能删除上级单位!");
		}
		
		this.organMapper.deleteByPrimaryKey(organId);
	}
	
	
	/**
	 * 获得单位
	 * @param sysMessageId 消息编号
	 */
	public Organ getOrganById(Long organId) throws Exception{
		
		//获取单位对像
		Organ organ = this.organMapper.selectByPrimaryKey(organId);
		
		if (organ == null) {
			throw new Exception("无此单位!");
		}
		
		return organ;
	}
	
	/**
	 * 获得本单位
	 * @param organId 消息编号
	 */
	public Organ getOrganSlefById() throws Exception{
		
		Organ organ = null;
		
		OrganExample example = new OrganExample();
		example.or().andDegreeEqualTo(OrganDegreeEnum.SELF.toValue());
		
		List<Organ> list = this.organMapper.selectByExample(example);
		
		if(list.size() > 0){
			organ = list.get(0);
		}
		
		return organ;
	}
	
}
