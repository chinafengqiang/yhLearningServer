package com.smlearning.domain.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smlearning.domain.entity.CoursewareCategory;
import com.smlearning.domain.entity.CoursewareCategoryExample;
import com.smlearning.domain.vo.CoursewareNode;
import com.smlearning.domain.vo.Tree;
import com.smlearning.infrastructure.dao.CoursewareCategoryMapper;

/**
 * 课件分类领域活动 聚合
 * @author Administrator
 */
@Repository
public class CoursewareCategoryActivity {

	static Logger logger = Logger.getLogger(CoursewareCategoryActivity.class.getName());
	
	//课程分类基础层
	@Autowired
	private CoursewareCategoryMapper coursewareCategoryMapper;
	
	/**
	 * 树节点缓存
	 */
//	private static ArrayList<CoursewareNode> coursewareNodeListCache = new ArrayList<CoursewareNode>();
	
	public List<CoursewareCategory> getCoursewareCategoryList(int type){
		CoursewareCategoryExample mEx = new CoursewareCategoryExample();
		mEx.or().andUseTypeEqualTo(type);
		List<CoursewareCategory> coursewareCategoryList = this.coursewareCategoryMapper.selectByExample(mEx);
		return coursewareCategoryList;
	}
	/**
	 * 返回数据集
	 * @return
	 */
	public ArrayList<CoursewareNode> createCoursewareTree(Integer useType) {
		
		ArrayList<CoursewareNode> rt = new ArrayList<CoursewareNode>();
		
		CoursewareCategoryExample mEx = new CoursewareCategoryExample();
		
		CoursewareCategoryExample.Criteria criteria = mEx.createCriteria();
		
		criteria.andUseTypeEqualTo(useType);
	//	cExample.or().andNameNotEqualTo("作业");
		
	//	mEx.or().andUseTypeEqualTo(useType);
		mEx.setOrderByClause("sort_flag ASC");
		
		List<CoursewareCategory> coursewareCategoryList = this.coursewareCategoryMapper.selectByExample(mEx);
		for (CoursewareCategory item : coursewareCategoryList) {
			CoursewareNode coursewareTreeNode = new CoursewareNode();
			coursewareTreeNode.setParentId(item.getParentId());
			coursewareTreeNode.setId(item.getId());
			coursewareTreeNode.setName(item.getName());
			rt.add(coursewareTreeNode);
		}
		
	//	setCoursewareCategoryTreeNodeListCache(rt);
		
		return rt;
	}
	
	/**
	 * 返回数据集
	 * @return
	 */
	public ArrayList<CoursewareNode> createCoursewareNodeList(Integer useType) {
		
//		if (coursewareNodeListCache.size() > 0) {
//			return coursewareNodeListCache;
//		}
		
		List<String> strList = new ArrayList<String>();
		
		ArrayList<CoursewareNode> rt = new ArrayList<CoursewareNode>();
		
		CoursewareCategoryExample mEx = new CoursewareCategoryExample();
		
		CoursewareCategoryExample.Criteria criteria = mEx.createCriteria();
		
		strList.add("作业");
		strList.add("本地");
		strList.add("补充");
		
		criteria.andNameNotIn(strList);
		criteria.andUseTypeEqualTo(useType);
	//	cExample.or().andNameNotEqualTo("作业");
		
	//	mEx.or().andUseTypeEqualTo(useType);
		mEx.setOrderByClause("sort_flag ASC");
		
		List<CoursewareCategory> coursewareCategoryList = this.coursewareCategoryMapper.selectByExample(mEx);
		for (CoursewareCategory item : coursewareCategoryList) {
			CoursewareNode coursewareTreeNode = new CoursewareNode();
			coursewareTreeNode.setParentId(item.getParentId());
			coursewareTreeNode.setId(item.getId());
			coursewareTreeNode.setName(item.getName());
			rt.add(coursewareTreeNode);
		}
		
	//	setCoursewareCategoryTreeNodeListCache(rt);
		
		return rt;
	}
	
	
	/**
	 * 公用树
	 * @return
	 */
	public ArrayList<Tree> createTree(Integer useType) {
		
		ArrayList<Tree> rt = new ArrayList<Tree>();
		CoursewareCategoryExample cExample = new CoursewareCategoryExample();
		
		cExample.or().andUseTypeEqualTo(useType);
		cExample.setOrderByClause("sort_flag ASC");
		
		List<CoursewareCategory> coursewareCategoryList = this.coursewareCategoryMapper.selectByExample(cExample);
		for (CoursewareCategory item : coursewareCategoryList) {
			Tree tree = new Tree();
			
			tree.setText(item.getName());
			tree.setIconCls("status_online");
			tree.setId(item.getId().toString());
			tree.setPid(String.valueOf(0));
			
//			coursewareTreeNode.setParentId(item.getParentId());
//			coursewareTreeNode.setId(item.getId());
//			coursewareTreeNode.setName(item.getName());
			rt.add(tree);
		}
		
	//	setCoursewareCategoryTreeNodeListCache(rt);
		
		return rt;
	}
	
	/**
	 * 创建课件分类
	 * @param name 名称
	 * @param parentId 上级分类编号
	 * @param sortFlag 排序号
	 * @param creator 创建人
	 * @return 课程分类对象
	 * @throws NoParentCourseCategoryException 无上级课程分类
	 */
	public CoursewareCategory createCoursewareCategory(String name, Long parentId, Integer sortFlag, Integer useType, Long creator) throws Exception {
		
		CoursewareCategory parentCategory = this.coursewareCategoryMapper.selectByPrimaryKey(parentId);
		
		//判断是否存在上级课程分类
		if (parentId > 0 && parentCategory == null) {
			throw new Exception("无此课件上级分类");
		}
		
	
		CoursewareCategory coursewareCategory = new CoursewareCategory();
		
		coursewareCategory.setName(name);
		coursewareCategory.setCreator(creator);
		coursewareCategory.setCreatedTime(new Date());
		coursewareCategory.setParentId(parentId);
		coursewareCategory.setSortFlag(sortFlag);
		coursewareCategory.setUseType(useType);
		
		this.coursewareCategoryMapper.insert(coursewareCategory);
		
	//	clearCoursewareCategoryNodeListCache();
		
		return coursewareCategory;
	}
	
	/**
	 * 修改课件分类
	 * @param courseCategoryId 课件分类编号
	 * @param name 名称
	 * @param sortFlag 排序号
	 * @return 课程件类编号
	 * @throws NoCourseCategoryException 无此课件分类
	 * @throws NonEnoughAccessException 无足够的权限
	 */
	public CoursewareCategory modifyCoursewareCategory(Long coursewareCategoryId, String name, Integer sortFlag) throws Exception{
	
		CoursewareCategory coursewareCategory = this.coursewareCategoryMapper.selectByPrimaryKey(coursewareCategoryId);
		
		//判断是否存在此分类
		if (coursewareCategory == null) {
			throw new Exception("无此课件分类");
		}
		
		coursewareCategory.setName(name);
		coursewareCategory.setSortFlag(sortFlag);

		this.coursewareCategoryMapper.updateByPrimaryKeySelective(coursewareCategory);
		
	//	clearCoursewareCategoryNodeListCache();

		return coursewareCategory;

	}
	
	/**
	 * 删除课件分类
	 * @param courseCategoryId 课件分类编号
	 * @throws NoCourseCategoryException 无此课件分类
	 * @throws NonEmptyCourseCategoryException 此课件分类非空
	 * @throws NonEmptyCouseCategoryException 
	 */
	public void removeCoursewareCategory(Long coursewareCategoryId) throws Exception {

		CoursewareCategory coursewareCategory = this.coursewareCategoryMapper.selectByPrimaryKey(coursewareCategoryId);
		
		//判断是否存在此分类
		if (coursewareCategory == null) {
			throw new Exception("无此课件分类");
		}
		
		CoursewareCategoryExample example = new CoursewareCategoryExample();
		example.or().andParentIdEqualTo(coursewareCategoryId);
		
		//获得管理员列表
		List<CoursewareCategory> list = this.coursewareCategoryMapper.selectByExample(example);
		if(list.size() > 0){
			throw new Exception("此课件分类包含子类");
		}
		
		this.coursewareCategoryMapper.deleteByPrimaryKey(coursewareCategoryId);
		
	//	clearCoursewareCategoryNodeListCache();
		
	}
	
//	/**
//	 * 清除缓存并添加数据集
//	 * @param courseNodeList
//	 */
//	private void setCoursewareCategoryTreeNodeListCache(ArrayList<CoursewareNode> coursewareNodeList) {
//		
//		coursewareNodeListCache.clear();
//		coursewareNodeListCache.addAll(coursewareNodeList);
//	}
//	
//	/**
//	 * 清除缓存
//	 */
//	private void clearCoursewareCategoryNodeListCache() {
//		
//		coursewareNodeListCache.clear();
//	}
	
	/**
	 * 获得课件分类
	 * @param courseCategoryId 课件分类编号
	 */
	public CoursewareCategory getCoursewareCategoryByID(Long coursewareCategoryId) throws Exception {

		CoursewareCategory coursewareCategory = this.coursewareCategoryMapper.selectByPrimaryKey(coursewareCategoryId);
		
		//判断是否存在此分类
		if (coursewareCategory == null) {
			throw new Exception("无此课件分类");
		}
		
		return coursewareCategory;
		
	}
	
	
	
}
