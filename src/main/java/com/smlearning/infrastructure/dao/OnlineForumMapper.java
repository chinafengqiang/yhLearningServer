package com.smlearning.infrastructure.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.smlearning.domain.entity.OnlineForum;
import com.smlearning.domain.entity.OnlineForumExample;
@Repository
public interface OnlineForumMapper {
	
	/**
	 * 分页查询
	 * @param example
	 * @return
	 */
	List<OnlineForum> selectByExamplePaging(OnlineForumExample example);
	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table online_forum
     *
     * @mbggenerated
     */
    int countByExample(OnlineForumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table online_forum
     *
     * @mbggenerated
     */
    int deleteByExample(OnlineForumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table online_forum
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table online_forum
     *
     * @mbggenerated
     */
    int insert(OnlineForum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table online_forum
     *
     * @mbggenerated
     */
    int insertSelective(OnlineForum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table online_forum
     *
     * @mbggenerated
     */
    List<OnlineForum> selectByExample(OnlineForumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table online_forum
     *
     * @mbggenerated
     */
    OnlineForum selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table online_forum
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") OnlineForum record, @Param("example") OnlineForumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table online_forum
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") OnlineForum record, @Param("example") OnlineForumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table online_forum
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(OnlineForum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table online_forum
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(OnlineForum record);
}