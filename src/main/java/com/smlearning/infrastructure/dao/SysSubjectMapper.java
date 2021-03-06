package com.smlearning.infrastructure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smlearning.domain.entity.SysSubject;
import com.smlearning.domain.entity.SysSubjectExample;

public interface SysSubjectMapper {
	
	/**
	 * 分页查询
	 * @param example
	 * @return
	 */
	List<SysSubject> selectByExamplePaging(SysSubjectExample example);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_subject
     *
     * @mbggenerated
     */
    int countByExample(SysSubjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_subject
     *
     * @mbggenerated
     */
    int deleteByExample(SysSubjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_subject
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_subject
     *
     * @mbggenerated
     */
    int insert(SysSubject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_subject
     *
     * @mbggenerated
     */
    int insertSelective(SysSubject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_subject
     *
     * @mbggenerated
     */
    List<SysSubject> selectByExample(SysSubjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_subject
     *
     * @mbggenerated
     */
    SysSubject selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_subject
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysSubject record, @Param("example") SysSubjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_subject
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysSubject record, @Param("example") SysSubjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_subject
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysSubject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_subject
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysSubject record);
}