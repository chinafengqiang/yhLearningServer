package com.smlearning.domain.activity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smlearning.domain.entity.Course;
import com.smlearning.domain.entity.CourseExample;
import com.smlearning.domain.entity.CoursePlan;
import com.smlearning.domain.entity.CoursePlanExample;
import com.smlearning.domain.entity.CourseWithBLOBs;
import com.smlearning.domain.entity.classBook;
import com.smlearning.domain.entity.classBookExample;
import com.smlearning.domain.entity.courseTable;
import com.smlearning.domain.entity.courseTableExample;
import com.smlearning.domain.entity.enums.CourseStatusEnum;
import com.smlearning.domain.entity.extend.CourseExtend;
import com.smlearning.domain.vo.CoursePlanVO;
import com.smlearning.domain.vo.CourseVO;
import com.smlearning.infrastructure.dao.CourseCategoryMapper;
import com.smlearning.infrastructure.dao.CourseMapper;
import com.smlearning.infrastructure.dao.CoursePlanMapper;
import com.smlearning.infrastructure.dao.CoursewareCategoryMapper;
import com.smlearning.infrastructure.dao.classBookMapper;
import com.smlearning.infrastructure.dao.courseTableMapper;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 * 课程领域活动 聚合
 * @author Administrator
 */
@Repository
public class CourseActivity {

	static Logger logger = Logger.getLogger(CourseActivity.class.getName());
	
	//课程基础层
	@Autowired
	private CourseMapper courseMapper;
	
	//课程分类基础层
	@Autowired
	private CourseCategoryMapper courseCategoryMapper;
	
	//课程分类基础层
	@Autowired
	private CoursewareCategoryMapper coursewareCategoryMapper;
	
	@Autowired
	private CoursePlanMapper coursePlanMapper;
	
	
	@Autowired
	private courseTableMapper courseTableMapper;
	
	@Autowired
	private classBookMapper classBookMapper;
	
	/**
	 * 上传视频路径名称
	 */
	private final static String fileName = "/uploadFile/file/";
	
	/**
	 * 上传图片路径名称
	 */
	private final static String picName = "/uploadFile/pic/"; 
	
	/**
	 * 返回课程列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryCoursePaning(CourseVO courseVO, PageHelper ph) {
		logger.info("queryCoursePaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<Course> sList = null;
		int sCount = 0;
		try{
			CourseExample mEx = new CourseExample();
			if(StringUtils.isNotBlank(courseVO.getName())){
				
				String nameLike = "%" + courseVO.getName() + "%";
				mEx.or().andNameLike(nameLike);
			}
			
			if(StringUtils.isNotBlank(courseVO.getLectuer())){
				
				String lectuerLike = "%" + courseVO.getLectuer() + "%";
				mEx.or().andLectuerLike(lectuerLike);
			}
			
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  courseMapper.selectByExamplePaging(mEx); 
			sCount = courseMapper.countByExample(mEx);
		}catch(Exception ex){
			logger.error("Error of queryCoursePaning",ex);
		}
		logger.info("queryCoursePaning result :" +  sList.size() );
		logger.info("queryCoursePaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	/**
	 * 创建课程
	 * @param name 课程名称
	 * @param courseCategoryId 课程分类编号
	 * @param lectuer主讲人
	 * @param hour学时
	 * @param description课程简介
	 * @param teacherDescription 老师简介
	 * @param canUpload  是否上传
	 * @param courseDescription
	 * @param creator 创建人
	 * @return
	 * @throws Exception
	 */
	public Course createCourse(String name, Long coursewareCategoryId, String lectuer, Integer hour, String description, 
			String teacherDescription, String pic, String url, Long creator,Long gradeId,Integer isPublic) throws Exception {
		
		logger.info("createCourse coursewareCategoryId :" +  coursewareCategoryId);
		
		//判断是否存在此课程分类
		if(this.coursewareCategoryMapper.selectByPrimaryKey(coursewareCategoryId) == null){
			throw new Exception("无此课程分类");
		}
		
		//创建课程对像
		CourseWithBLOBs course = new CourseWithBLOBs();
		course.setName(name);
		course.setCoursewareCategoryId(coursewareCategoryId);
		course.setLectuer(lectuer);
		course.setHour(hour);
		course.setDescription(description);
		course.setTeacherDescription(teacherDescription);
		if(pic!=null&&!"".equals(pic))
		  course.setPic(picName + pic);
		else
		  course.setPic("");
		course.setCreator(creator);
		course.setCreatedTime(new Date());
		course.setStatus(CourseStatusEnum.OPENED.toValue());
		course.setUrl(fileName + url);
		course.setGradeId(gradeId);
		course.setIsPublic(isPublic);
		this.courseMapper.insert(course);
		
		return course;
	}
	
	/**
	 * 修改课程
	 * @param courseId 课程编号
	 * @param name 课程名称
	 * @param lectuer 主讲人
	 * @param hour 学时
	 * @param courseTypeEnum 使用类型
	 * @param description 课程简介
	 * @param teacherDescription 老师简介
	 * @param canUpload 是否上传
	 * @return
	 * @throws NoCourseException 无此课程
	 */
	public Course modifyCourse(Long courseId, Long coursewareCategoryId, String name, String lectuer, Integer hour, String description, String teacherDescription,
			String pic, String url, Long creator,Long gradeId,Integer isPublic) throws Exception {
		
		CourseWithBLOBs course = this.courseMapper.selectByPrimaryKey(courseId);
		
		//判断是否存在此课程
		if (course == null) {
			throw new Exception("无此课程！");
		}
		
		//判断是否存在此课程分类
		if(this.coursewareCategoryMapper.selectByPrimaryKey(coursewareCategoryId) == null){
			throw new Exception("无此课程分类");
		}
		
		course.setCoursewareCategoryId(coursewareCategoryId);
		course.setName(name);
		course.setLectuer(lectuer);
		course.setHour(hour);
		course.setDescription(description);
		course.setTeacherDescription(teacherDescription);
		if(pic!=null&&!"".equals(pic)){
		  if(pic.contains("uploadFile")){
              course.setPic(pic);
          } else {
              course.setPic(picName + pic);
          }
         
		}
		
	   if(url.contains("uploadFile")){
           course.setUrl(url);
       } else{
           course.setUrl(fileName + url);
       }
		
		course.setCreator(creator);
		course.setGradeId(gradeId);
        course.setIsPublic(isPublic);
		this.courseMapper.updateByPrimaryKeySelective(course);
		
		return course;
	}
	
	
	/**
	 * 删除课程
	 * @param courseId 课程编号
	 * @param userId 操作者
	 * @throws NoCourseException 无此课程
	 * @throws CourseAlreadyUseException 此课程已经被选用
	 */
	public void removeCourse(Long courseId) throws Exception {
		
		CourseWithBLOBs course = this.courseMapper.selectByPrimaryKey(courseId);
		
		//判断是否存在此课程
		if (course == null) {
			throw new Exception("无此课程！");
		}
		
		//删除课程时加上防删处理!
//		if (this.studyModelFactoryFacade.getUserCourseModelFactory().findCountByCourseId(courseId) > 0) {
//			throw new CourseAlreadyUseException();
//		}
		
		// TODO : 系统优化改为储存过程
		//this.studyDAOFacade.getCourseDAO().deleteBySPCouse(courseId);
		
		this.courseMapper.deleteByPrimaryKey(courseId);
		
	}
	
	/**
	 * 修改课程状态
	 * @param courseId 课程编号
	 * @param statusEnum 状态
	 * @param userId 操作者
	 * @throws NoCourseException 无此课程
	 * @throws NonEnoughAccessException 无足够的权限
	 */
	public void modifyCourseStatus(Long courseId, CourseStatusEnum statusEnum) throws Exception {
		
		CourseWithBLOBs course = this.courseMapper.selectByPrimaryKey(courseId);
		
		//判断是否存在此课程
		if (course == null) {
			throw new Exception("无此课程！");
		}
		
		course.setStatus(statusEnum.toValue());
		
		this.courseMapper.updateByPrimaryKeySelective(course);
	}
	
	/**
	 * 获得课程对象信息
	 * @param courseId 课程编号
	 */
	public Course getCourse(Long courseId) throws Exception {
		
		Course course = this.courseMapper.selectByPrimaryKey(courseId);
		
		//判断是否存在此课程
		if (course == null) {
			throw new Exception("无此课程！");
		}
		
		return course;
	}
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<CourseExtend> queryCourse(Long classId) {
		
		List<CourseExtend> sList = null;
		sList =  courseMapper.selectByExampleAll(classId); 
		
		return sList;
	}
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public CourseExtend queryCourseById(Long courseId) {
		
		CourseExtend sList = null;
		sList =  courseMapper.selectByExampleId(courseId);
		
		return sList;
	}
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<CoursePlan> queryCoursePlan() {
		
		List<CoursePlan> sList = null;
		sList =  coursePlanMapper.selectByExample(null);
		
		return sList;
	}
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<courseTable> queryCourseTable() {
		
		List<courseTable> sList = null;
		sList =  courseTableMapper.selectByExample(null);
		
		return sList;
	}
	
	/**
	 * 返回课程列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryCourseTablePaning(PageHelper ph) {
		logger.info("queryCourseTablePaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<courseTable> sList = null;
		int sCount = 0;
		try{
			courseTableExample mEx = new courseTableExample();
			
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  courseTableMapper.selectByExamplePaging(mEx); 
			sCount = courseTableMapper.countByExample(mEx);
		}catch(Exception ex){
			logger.error("Error of queryCoursePlanPaning",ex);
		}
		logger.info("queryCourseTablePaning result :" +  sList.size() );
		logger.info("queryCourseTablePaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	/**
	 * 创建
	 * @param name 课件名称
	 * @param courseSubjectId 课程专题编号
	 * @param url 课件网址
	 * @return
	 * @throws NocourseSubjectException 无此课程专题
	 */
	public courseTable createCourseTable(String name,  String imageUrl) throws Exception{
		
		//创建对像
		courseTable courseTable = new courseTable();
		
		courseTable.setName(name);
		courseTable.setImageUrl(fileName  + imageUrl);
		
		this.courseTableMapper.insert(courseTable);
		
		return courseTable;
	}
	
	/**
	 * 修改课件
	 * @param coursewareId 课件编号
	 * @param name 课件名称
	 * @param url 课件网址
	 * @return
	 * @throws NoCoursewareException 无此课件
	 */
	public courseTable modifyCourseTable(Long courseTableId, String name,  String imageUrl) throws Exception{
		
		
		courseTable courseTable = this.courseTableMapper.selectByPrimaryKey(courseTableId);
		
		//判断是否存在此课程专题
		if(courseTable == null){
			throw new Exception("无此教学计划！");
		}
		
		
		courseTable.setName(name);
		
		if(imageUrl.contains("uploadFile")){
			courseTable.setImageUrl(imageUrl);
		} else {
			courseTable.setImageUrl(fileName  +imageUrl);
		}
		this.courseTableMapper.updateByPrimaryKeySelective(courseTable);
		
		return courseTable;
	}
	
	/**
	 * 删除课件
	 * @param coursewareId 课件编号
	 * @throws NoCoursewareException 无此课件
	 */
	public void removeCourseTable(Long courseTableId) throws Exception{
		
		courseTable courseTable = this.courseTableMapper.selectByPrimaryKey(courseTableId);
		
		//判断是否存在此课程专题
		if(courseTable == null){
			throw new Exception("无此教学计划！");
		}
		
		this.courseTableMapper.deleteByPrimaryKey(courseTableId);
	}
	
	/**
	 * 获得课程对象信息
	 * @param courseId 课程编号
	 */
	public courseTable getCourseTable(Long courseTableId) throws Exception {
		
		courseTable courseTable = this.courseTableMapper.selectByPrimaryKey(courseTableId);
		
		//判断是否存在此课程专题
		if(courseTable == null){
			throw new Exception("无此教学计划！");
		}
		
		return courseTable;
	}
	
	
	/**
	 * 返回课程列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryCoursePlanPaning(CoursePlanVO coursePlanVO, PageHelper ph) {
		logger.info("queryCoursePlanPaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<CoursePlan> sList = null;
		int sCount = 0;
		try{
			CoursePlanExample mEx = new CoursePlanExample();
			
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			
			String name = coursePlanVO.getName();
			long gradeId = coursePlanVO.getGradeId();
			if(StringUtils.isNotBlank(name)){
			  mEx.createCriteria().andNameLike(name);
			}

			sList =  coursePlanMapper.selectByExamplePaging(mEx); 
			sCount = coursePlanMapper.countByExample(mEx);
		}catch(Exception ex){
			logger.error("Error of queryCoursePlanPaning",ex);
		}
		logger.info("queryCoursePlanPaning result :" +  sList.size() );
		logger.info("queryCoursePlanPaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	/**
	 * 创建
	 * @param name 课件名称
	 * @param courseSubjectId 课程专题编号
	 * @param url 课件网址
	 * @return
	 * @throws NocourseSubjectException 无此课程专题
	 */
	public CoursePlan createCoursePlan(String name,  String imageUrl,long gradeId) throws Exception{
		
		//创建对像
		CoursePlan coursePlan = new CoursePlan();
		
		coursePlan.setName(name);
		coursePlan.setImageUrl(fileName  + imageUrl);
		coursePlan.setGradeId(gradeId);
		this.coursePlanMapper.insert(coursePlan);
		
		return coursePlan;
	}
	
	/**
	 * 修改课件
	 * @param coursewareId 课件编号
	 * @param name 课件名称
	 * @param url 课件网址
	 * @return
	 * @throws NoCoursewareException 无此课件
	 */
	public CoursePlan modifyCoursePlan(Long coursePlanId, String name,  String imageUrl,long gradeId) throws Exception{
		
		
		CoursePlan coursePlan = this.coursePlanMapper.selectByPrimaryKey(coursePlanId);
		
		//判断是否存在此课程专题
		if(coursePlan == null){
			throw new Exception("无此教学计划！");
		}
		
		
		coursePlan.setName(name);
		coursePlan.setGradeId(gradeId);
		if(imageUrl.contains("uploadFile")){
			coursePlan.setImageUrl(imageUrl);
		} else {
			coursePlan.setImageUrl(fileName  +imageUrl);
		}
		this.coursePlanMapper.updateByPrimaryKeySelective(coursePlan);
		
		return coursePlan;
	}
	
	/**
	 * 删除课件
	 * @param coursewareId 课件编号
	 * @throws NoCoursewareException 无此课件
	 */
	public void removeCoursePlan(Long coursePlanId) throws Exception{
		
		CoursePlan coursePlan = this.coursePlanMapper.selectByPrimaryKey(coursePlanId);
		
		//判断是否存在此课程专题
		if(coursePlan == null){
			throw new Exception("无此教学计划！");
		}
		
		this.coursePlanMapper.deleteByPrimaryKey(coursePlanId);
	}
	
	/**
	 * 获得课程对象信息
	 * @param courseId 课程编号
	 */
	public CoursePlan getCoursePlan(Long coursePlanId) throws Exception {
		
		CoursePlan coursePlan = this.coursePlanMapper.selectByPrimaryKey(coursePlanId);
		
		//判断是否存在此课程专题
		if(coursePlan == null){
			throw new Exception("无此教学计划！");
		}
		
		return coursePlan;
	}
	
	
	/**
	 * 返回课程列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryClassBookPaning(PageHelper ph) {
		logger.info("queryClassBookPaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<classBook> sList = null;
		int sCount = 0;
		try{
			classBookExample mEx = new classBookExample();
			
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  classBookMapper.selectByExamplePaging(mEx); 
			sCount = classBookMapper.countByExample(mEx);
		}catch(Exception ex){
			logger.error("Error of queryClassBookPaning",ex);
		}
		logger.info("queryClassBookPaning result :" +  sList.size() );
		logger.info("queryClassBookPaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	/**
	 * 创建
	 * @param name 课件名称
	 * @param courseSubjectId 课程专题编号
	 * @param url 课件网址
	 * @return
	 * @throws NocourseSubjectException 无此课程专题
	 */
	public classBook createClassBook(String name,  String imageUrl) throws Exception{
		
		//创建对像
		classBook classBook = new classBook();
		
		classBook.setName(name);
		classBook.setImageUrl(fileName  + imageUrl);
		
		this.classBookMapper.insert(classBook);
		
		return classBook;
	}
	
	/**
	 * 修改课件
	 * @param coursewareId 课件编号
	 * @param name 课件名称
	 * @param url 课件网址
	 * @return
	 * @throws NoCoursewareException 无此课件
	 */
	public classBook modifyclassBook(Long classBookId, String name,  String imageUrl) throws Exception{
		
		
		classBook classBook = this.classBookMapper.selectByPrimaryKey(classBookId);
		
		//判断是否存在此课程专题
		if(classBook == null){
			throw new Exception("无此教学计划！");
		}
		
		
		classBook.setName(name);
		
		if(imageUrl.contains("uploadFile")){
			classBook.setImageUrl(imageUrl);
		} else {
			classBook.setImageUrl(fileName  +imageUrl);
		}
		this.classBookMapper.updateByPrimaryKeySelective(classBook);
		
		return classBook;
	}
	
	/**
	 * 删除课件
	 * @param coursewareId 课件编号
	 * @throws NoCoursewareException 无此课件
	 */
	public void removeClassBook(Long classBookId) throws Exception{
		
		classBook classBook = this.classBookMapper.selectByPrimaryKey(classBookId);
		
		//判断是否存在此课程专题
		if(classBook == null){
			throw new Exception("无此教学计划！");
		}
		
		this.classBookMapper.deleteByPrimaryKey(classBookId);
	}
	
	/**
	 * 获得课程对象信息
	 * @param courseId 课程编号
	 */
	public classBook getClassBook(Long classBookId) throws Exception {
		
		classBook classBook = this.classBookMapper.selectByPrimaryKey(classBookId);
		
		//判断是否存在此课程专题
		if(classBook == null){
			throw new Exception("无此教学计划！");
		}
		
		return classBook;
	}
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<classBook> queryClassBook() {
		
		List<classBook> sList = null;
		sList =  classBookMapper.selectByExample(null);
		
		return sList;
	}
	
}
