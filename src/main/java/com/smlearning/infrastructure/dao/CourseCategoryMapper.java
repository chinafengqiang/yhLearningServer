package com.smlearning.infrastructure.dao;

import com.smlearning.domain.entity.CourseCategory;
import com.smlearning.domain.entity.CourseCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbggenerated
     */
    int countByExample(CourseCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbggenerated
     */
    int deleteByExample(CourseCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbggenerated
     */
    int insert(CourseCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbggenerated
     */
    int insertSelective(CourseCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbggenerated
     */
    List<CourseCategory> selectByExample(CourseCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbggenerated
     */
    CourseCategory selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CourseCategory record, @Param("example") CourseCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") CourseCategory record, @Param("example") CourseCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CourseCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_category
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CourseCategory record);
}