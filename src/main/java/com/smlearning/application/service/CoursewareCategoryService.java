package com.smlearning.application.service;

import java.util.ArrayList;
import java.util.List;

import com.smlearning.domain.entity.CoursewareCategory;
import com.smlearning.domain.vo.CoursewareNode;
import com.smlearning.domain.vo.Tree;




/**
 * 课件分类接口
 * @author Administrator
 */
public interface CoursewareCategoryService {

	/**
	 * 创建课件分类
	 * @param name 名称
	 * @param parentId 上级分类编号
	 * @param sortFlag 排序号
	 * @param creator 创建人
	 * @return 课程分类对象
	 * @throws NoParentCourseCategoryException 无上级课程分类
	 */
	public CoursewareCategory createCoursewareCategory(String name, Long parentId, Integer sortFlag, Integer useType,Long creator) throws Exception;
	
	/**
	 * 修改课件分类
	 * @param courseCategoryId 课件分类编号
	 * @param name 名称
	 * @param sortFlag 排序号
	 * @return 课程件类编号
	 * @throws NoCourseCategoryException 无此课件分类
	 * @throws NonEnoughAccessException 无足够的权限
	 */
	public CoursewareCategory modifyCoursewareCategory(Long coursewareCategoryId, String name, Integer sortFlag) throws Exception;
	
	/**
	 * 删除课件分类
	 * @param courseCategoryId 课件分类编号
	 * @throws NoCourseCategoryException 无此课件分类
	 * @throws NonEmptyCourseCategoryException 此课件分类非空
	 * @throws NonEmptyCouseCategoryException 
	 */
	public void removeCoursewareCategory(Long coursewareCategoryId) throws Exception;
	
	/**
	 * 返回数据集
	 * @return
	 */
	public ArrayList<CoursewareNode> createCoursewareNodeList(Integer useType);
	
	/**
	 * 获得课件分类
	 * @param courseCategoryId 课件分类编号
	 */
	public CoursewareCategory getCoursewareCategoryByID(Long coursewareCategoryId) throws Exception;
	
	/**
	 * 公用树
	 * @return
	 */
	public ArrayList<Tree> createTree(Integer useType);
	
	/**
	 * 返回数据集
	 * @return
	 */
	public ArrayList<CoursewareNode> createCoursewareTree(Integer useType);
	
	
	public List<CoursewareCategory> getCoursewareCategoryList(int type);
	
}
