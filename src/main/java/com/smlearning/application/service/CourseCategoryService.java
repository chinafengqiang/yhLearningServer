package com.smlearning.application.service;

import java.util.ArrayList;

import com.smlearning.domain.entity.CourseCategory;
import com.smlearning.domain.vo.CourseNode;



/**
 * 课程分类接口
 * @author Administrator
 */
public interface CourseCategoryService {

	/**
	 * 创建课程分类
	 * @param name 名称
	 * @param parentId 上级分类编号
	 * @param sortFlag 排序号
	 * @param creator 创建人
	 * @return 课程分类对象
	 * @throws NoParentCourseCategoryException 无上级课程分类
	 */
	public CourseCategory createCourseCategory(String name, Long parentId, Integer sortFlag, Long creator) throws Exception;
	
	
	/**
	 * 修改课程分类
	 * @param courseCategoryId 课程分类编号
	 * @param name 名称
	 * @param sortFlag 排序号
	 * @return 课程分类编号
	 * @throws NoCourseCategoryException 无此课程分类
	 * @throws NonEnoughAccessException 无足够的权限
	 */
	public CourseCategory modifyCourseCategory(Long courseCategoryId, String name, Integer sortFlag) throws Exception;
	
	/**
	 * 删除课程分类
	 * @param courseCategoryId 课程分类编号
	 * @throws NoCourseCategoryException 无此课程分类
	 * @throws NonEmptyCourseCategoryException 此课程分类非空
	 * @throws NonEmptyCouseCategoryException 
	 */
	public void removeCourseCategory(Long courseCategoryId) throws Exception;
	
	/**
	 * 返回数据集
	 * @return
	 */
	public ArrayList<CourseNode> createCourseNodeList();
		
}
