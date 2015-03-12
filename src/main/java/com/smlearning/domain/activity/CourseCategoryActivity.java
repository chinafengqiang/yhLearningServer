package com.smlearning.domain.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smlearning.domain.entity.CourseCategory;
import com.smlearning.domain.entity.CourseCategoryExample;
import com.smlearning.domain.vo.CourseNode;
import com.smlearning.infrastructure.dao.CourseCategoryMapper;

/**
 * 课程分类领域活动 聚合
 * @author Administrator
 */
@Repository
public class CourseCategoryActivity {

	static Logger logger = Logger.getLogger(CourseCategoryActivity.class.getName());
	
	//课程分类基础层
	@Autowired
	private CourseCategoryMapper courseCategoryMapper;
	
	/**
	 * 树节点缓存
	 */
	private static ArrayList<CourseNode> courseNodeListCache = new ArrayList<CourseNode>();
	
	
	/**
	 * 返回数据集
	 * @return
	 */
	public ArrayList<CourseNode> createCourseNodeList() {
		
		if (courseNodeListCache.size() > 0) {
			return courseNodeListCache;
		}
		
		ArrayList<CourseNode> rt = new ArrayList<CourseNode>();
		
		List<CourseCategory> courseCategoryList = this.courseCategoryMapper.selectByExample(null);
		
		for (CourseCategory item : courseCategoryList) {
			CourseNode courseTreeNode = new CourseNode();
			courseTreeNode.setParentId(item.getParentId());
			courseTreeNode.setId(item.getId());
			courseTreeNode.setName(item.getName());
			rt.add(courseTreeNode);
		}
		
		setCourseCategoryTreeNodeListCache(rt);
		
		return rt;
	}
	
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
		
		CourseCategory parentCategory = this.courseCategoryMapper.selectByPrimaryKey(parentId);
		
		//判断是否存在上级课程分类
		if (parentId > 0 && parentCategory == null) {
			throw new Exception("无此课程上级分类");
		}
		
	
		CourseCategory courseCategory = new CourseCategory();
		
		courseCategory.setName(name);
		courseCategory.setCreator(creator);
		courseCategory.setCreatedTime(new Date());
		courseCategory.setParentId(parentId);
		courseCategory.setSortFlag(sortFlag);
		
		this.courseCategoryMapper.insert(courseCategory);
		
		clearCourseCategoryNodeListCache();
		
		return courseCategory;
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
	
		CourseCategory courseCategory = this.courseCategoryMapper.selectByPrimaryKey(courseCategoryId);
		
		//判断是否存在此分类
		if (courseCategory == null) {
			throw new Exception("无此课程分类");
		}
		
		courseCategory.setName(name);
		courseCategory.setSortFlag(sortFlag);

		this.courseCategoryMapper.updateByPrimaryKeySelective(courseCategory);
		
		clearCourseCategoryNodeListCache();

		return courseCategory;

	}
	
	/**
	 * 删除课程分类
	 * @param courseCategoryId 课程分类编号
	 * @throws NoCourseCategoryException 无此课程分类
	 * @throws NonEmptyCourseCategoryException 此课程分类非空
	 * @throws NonEmptyCouseCategoryException 
	 */
	public void removeCourseCategory(Long courseCategoryId) throws Exception {

		CourseCategory courseCategory = this.courseCategoryMapper.selectByPrimaryKey(courseCategoryId);
		
		//判断是否存在此分类
		if (courseCategory == null) {
			throw new Exception("无此课程分类");
		}
		
		CourseCategoryExample example = new CourseCategoryExample();
		example.or().andParentIdEqualTo(courseCategoryId);
		
		//获得管理员列表
		List<CourseCategory> list = this.courseCategoryMapper.selectByExample(example);
		if(list.size() > 0){
			throw new Exception("此课程分类包含子类");
		}
		this.courseCategoryMapper.deleteByPrimaryKey(courseCategoryId);
		
		clearCourseCategoryNodeListCache();
		
	}
	
	/**
	 * 清除缓存并添加数据集
	 * @param courseNodeList
	 */
	private void setCourseCategoryTreeNodeListCache(ArrayList<CourseNode> courseNodeList) {
		
		courseNodeListCache.clear();
		courseNodeListCache.addAll(courseNodeList);
	}
	
	/**
	 * 清除缓存
	 */
	private void clearCourseCategoryNodeListCache() {
		
		courseNodeListCache.clear();
	}
	
	
	
}
