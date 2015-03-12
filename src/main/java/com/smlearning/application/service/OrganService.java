package com.smlearning.application.service;

import com.smlearning.domain.entity.Organ;
import com.smlearning.domain.vo.OrganVO;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;


/**
 * 组织机构接口
 * @author Administrator
 */
public interface OrganService {

	/**
	 * 初始化系统
	 * @param organName 单位名称
	 * @param organGrade 单位级别
	 * @throws RepeatedManagerNameException
	 * @throws RepeatedOrganNameException 
	 */
	public void init(String organName, String organGrade) throws Exception;
	
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
	public Organ modifySelfOrgan(String name, String grade, String department, String linkman, String tel) throws Exception;
	
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
	public Organ modifyHigherOrgan(Long organId, String name, String grade, String department, String linkman, String tel, String serverIp, String serverPort) throws Exception;
	
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
	public Organ createLowerOrgan(String name, String grade, String department, String linkman, String tel, String serverIp, String serverPort) throws Exception;
	
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
	public Organ modifyLowerOrgan(Long organId, String name, String grade, String department, String linkman, String tel, String serverIp, String serverPort) throws Exception;

	
	/**
	 * 删除下级单位
	 * @param organId 单位编号
	 * @throws NoOrganException 
	 * @throws InvalidAccessException
	 */
	public void removeLowerOrgan(Long organId) throws Exception;
	
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
	public Organ createHigherOrgan(String name, String grade, String department, String linkman, String tel, String serverIp, String serverPort) throws Exception;
	
	/**
	 * 删除上级单位
	 * @param organId 单位编号
	 * @throws NoOrganException 
	 * @throws InvalidAccessException
	 */
	public void removeHigherOrgan(Long organId) throws Exception;
	
	/**
	 * 返回上级组织机构列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryHigherOrganPaning(OrganVO organVO, PageHelper ph);
	
	/**
	 * 返回下级组织机构列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryLowerOrganPaning(OrganVO organVO, PageHelper ph);
	
	/**
	 * 获得单位
	 * @param sysMessageId 消息编号
	 */
	public Organ getOrganById(Long organId) throws Exception;
	
	
	/**
	 * 获得本单位
	 * @param organId 消息编号
	 */
	public Organ getOrganSlefById() throws Exception;
	
}
