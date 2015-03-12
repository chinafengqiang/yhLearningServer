package com.smlearning.application.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smlearning.application.service.OrganService;
import com.smlearning.domain.activity.OrganActivity;
import com.smlearning.domain.entity.Organ;
import com.smlearning.domain.vo.OrganVO;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 * 组织机构业务方法
 * @author Administrator
 */
@Service
public class OrganServiceImpl implements OrganService{

	static Logger logger = Logger.getLogger(OrganServiceImpl.class.getName());
	
	@Autowired
	private OrganActivity organActivity;
	
	/**
	 * 初始化系统
	 * @param organName 单位名称
	 * @param organGrade 单位级别
	 * @throws RepeatedManagerNameException
	 * @throws RepeatedOrganNameException 
	 */
	public void init(String organName, String organGrade) throws Exception {
		organActivity.init(organName, organGrade);
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
		return organActivity.modifySelfOrgan(name, grade, department, linkman, tel);
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
		return organActivity.modifyHigherOrgan(organId, name, grade, department, linkman, tel, serverIp, serverPort);
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
		return  organActivity.createLowerOrgan(name, grade, department, linkman, tel, serverIp, serverPort);
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
		return organActivity.modifyLowerOrgan(organId, name, grade, department, linkman, tel, serverIp, serverPort);
	}
	
	/**
	 * 删除下级单位
	 * @param organId 单位编号
	 * @throws NoOrganException 
	 * @throws InvalidAccessException
	 */
	public void removeLowerOrgan(Long organId) throws Exception {
		organActivity.removeLowerOrgan(organId);
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
		return organActivity.createHigherOrgan(name, grade, department, linkman, tel, serverIp, serverPort);
	}
	
	/**
	 * 删除上级单位
	 * @param organId 单位编号
	 * @throws NoOrganException 
	 * @throws InvalidAccessException
	 */
	public void removeHigherOrgan(Long organId) throws Exception {
		organActivity.removeHigherOrgan(organId);
	}
	
	/**
	 * 返回上级组织机构列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryHigherOrganPaning(OrganVO organVO, PageHelper ph) {
		return organActivity.queryHigherOrganPaning(organVO, ph);
	}
	
	/**
	 * 返回下级组织机构列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryLowerOrganPaning(OrganVO organVO, PageHelper ph) {
		return organActivity.queryLowerOrganPaning(organVO, ph);
	}
	
	/**
	 * 获得单位
	 * @param sysMessageId 消息编号
	 */
	public Organ getOrganById(Long organId) throws Exception{
		return organActivity.getOrganById(organId);
	}
	
	/**
	 * 获得本单位
	 * @param organId 消息编号
	 */
	public Organ getOrganSlefById() throws Exception{
		return organActivity.getOrganSlefById();
	}

}
