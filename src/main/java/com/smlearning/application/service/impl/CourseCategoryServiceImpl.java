package com.smlearning.application.service.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smlearning.application.service.CourseCategoryService;
import com.smlearning.domain.activity.CourseCategoryActivity;
import com.smlearning.domain.entity.CourseCategory;
import com.smlearning.domain.vo.CourseNode;

/**
 * 课程分类业务方法
 * @author Administrator
 */
@Service
public class CourseCategoryServiceImpl implements CourseCategoryService{

	static Logger logger = Logger.getLogger(CourseCategoryServiceImpl.class.getName());

	@Autowired
	private CourseCategoryActivity courseCategoryActivity;

	/**
	 * 创建课程分类
	 * @param name 名称
	 * @param parentId 上级分类编号
	 * @param sortFlag 排序号
	 * @param creator 创建人
	 * @return 课程分类对象
	 * @throws NoParentCourseCategoryException 无上级课程分类
	 */
	public CourseCategory createCourseCategory(String name, Long parentId, Integer sortFlag, Long creator) throws Exception {
		return courseCategoryActivity.createCourseCategory(name, parentId, sortFlag, creator);
	}
	
	/**
	 * 修改课程分类
	 * @param courseCategoryId 课程分类编号
	 * @param name 名称
	 * @param sortFlag 排序号
	 * @return 课程分类编号
	 * @throws NoCourseCategoryException 无此课程分类
	 * @throws NonEnoughAccessException 无足够的权限
	 */
	public CourseCategory modifyCourseCategory(Long courseCategoryId, String name, Integer sortFlag) throws Exception{
		return courseCategoryActivity.modifyCourseCategory(courseCategoryId, name, sortFlag);
	}
	
	/**
	 * 删除课程分类
	 * @param courseCategoryId 课程分类编号
	 * @throws NoCourseCategoryException 无此课程分类
	 * @throws NonEmptyCourseCategoryException 此课程分类非空
	 * @throws NonEmptyCouseCategoryException 
	 */
	public void removeCourseCategory(Long courseCategoryId) throws Exception {
		courseCategoryActivity.removeCourseCategory(courseCategoryId);
	}
	
	/**
	 * 返回数据集
	 * @return
	 */
	public ArrayList<CourseNode> createCourseNodeList() {
		return courseCategoryActivity.createCourseNodeList();
	}
	
}
