package com.smlearning.domain.activity;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smlearning.domain.entity.CourseSchedule;
import com.smlearning.domain.entity.CourseScheduleExample;
import com.smlearning.domain.entity.Lesson;
import com.smlearning.domain.entity.LessonExample;
import com.smlearning.domain.entity.extend.LessonExtend;
import com.smlearning.domain.vo.CourseScheduleVO;
import com.smlearning.infrastructure.dao.CourseScheduleMapper;
import com.smlearning.infrastructure.dao.LessonMapper;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.DateUtil;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 * 课程表进度领域活动 聚合
 * @author Administrator
 */
@Repository
public class CourseScheduleActivity {

	static Logger logger = Logger.getLogger(CourseScheduleActivity.class.getName());
	
	//课程表基础层
	@Autowired
	private CourseScheduleMapper courseScheduleMapper;
	
	//课程表基础层
	@Autowired
	private LessonMapper lessonMapper;
	
	/**
	 * 返回学员列表数据信息
	 * @param sysMessageVO
	 * @param ph
	 * @return
	 */
	public DataGrid querySchedulePaning(CourseScheduleVO scheduleVO, PageHelper ph) {
		logger.info("querySchedulePaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<CourseSchedule> sList = null;
		int sCount = 0;
		try{
			CourseScheduleExample mEx = new CourseScheduleExample();
			if(StringUtils.isNotBlank(scheduleVO.getSuperGrade())){
				
				String nameLike = scheduleVO.getSuperGrade();
				mEx.or().andSuperGradeEqualTo(nameLike);
			}
			
			if(StringUtils.isNotBlank(scheduleVO.getSuperClass())){
				
				String actualNameLike = scheduleVO.getSuperClass();
				mEx.or().andSuperClassEqualTo(actualNameLike);
			}
			
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  courseScheduleMapper.selectByExamplePaging(mEx); 
			sCount = courseScheduleMapper.countByExample(mEx);
		}catch(Exception ex){
			logger.error("Error of querySchedulePaning",ex);
		}
		logger.info("querySchedulePaning result :" +  sList.size() );
		logger.info("querySchedulePaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	
	/**
	 * 返回列表数据信息
	 * @param lessonExtend
	 * @param ph
	 * @return
	 */
	public DataGrid queryLessonPaning(LessonExtend lessonExtend, PageHelper ph) {
		logger.info("queryLessonPaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<LessonExtend> sList = null;
		int sCount = 0;
		try{
			LessonExample mEx = new LessonExample();
			
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  lessonMapper.selectByExamplePaging(mEx); 
			sCount = lessonMapper.selectByExamplePagingCount(mEx);
		}catch(Exception ex){
			logger.error("Error of queryLessonPaning",ex);
		}
		logger.info("queryLessonPaning result :" +  sList.size() );
		logger.info("queryLessonPaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<LessonExtend> queryLesson(LessonExtend lessonExtend) {
		
		List<LessonExtend> sList = null;
		
		LessonExample ex = new LessonExample();
		ex.setClassId(lessonExtend.getClassId());
		ex.setYear(DateUtil.getCurYear());
		
		if(DateUtil.getCurMonth() < 9) {
			ex.setTerm(1);
		} else {
			ex.setTerm(2);
		}
		
		sList =  lessonMapper.selectByExampleAll(ex); 
		
		return sList;
	}
	

	/**
	 * 创建课程进度表
	 * @param classId
	 * @param subjectId
	 * @param day
	 * @param thetime
	 * @param term
	 * @return
	 * @throws Exception
	 */
	public Lesson createLesson(Long classId, Long subjectId, Integer day, Integer thetime,Integer term) throws Exception{
		
		//创建对像
		Lesson lesson = new Lesson();
		
		lesson.setClassId(classId);
		lesson.setSubjectId(subjectId);
		lesson.setDay(day);
		lesson.setThetime(thetime);
		lesson.setTerm(term);
		lesson.setYear(DateUtil.getCurYear());
		
		this.lessonMapper.insert(lesson);
		
		return lesson;
	}
	
	
	/**
	 * 修改课程进度表
	 * @param classId
	 * @param subjectId
	 * @param day
	 * @param thetime
	 * @param term
	 * @return
	 * @throws Exception
	 */
	public Lesson modifyLesson(Long lessonId, Long classId, Long subjectId, Integer day, Integer thetime,Integer term) throws Exception{
		
		//创建对像
		Lesson lesson = this.lessonMapper.selectByPrimaryKey(lessonId);
		
		//判断是否存在此课程专题
		if(lesson == null){
			throw new Exception("无此课程表！");
		}
		
		lesson.setClassId(classId);
		lesson.setSubjectId(subjectId);
		lesson.setDay(day);
		lesson.setThetime(thetime);
		lesson.setTerm(term);
		
		this.lessonMapper.updateByPrimaryKey(lesson);
		
		return lesson;
	}
	
	
	/**
	 * 删除课程进度表
	 */
	public void removeLesson(Long lessonId) throws Exception{
		
		//创建对像
		Lesson lesson = this.lessonMapper.selectByPrimaryKey(lessonId);
		
		//判断是否存在此课程专题
		if(lesson == null){
			throw new Exception("无此课程表！");
		}
		
		this.lessonMapper.deleteByPrimaryKey(lessonId);
	}
	
	
	/**
	 * 获得课程进度表
	 * @param coursewareId 课件编号
	 * @throws NoCoursewareException 无此课件
	 */
	public Lesson getLessonById(Long lessonId) throws Exception{
		
		//创建对像
		Lesson lesson = this.lessonMapper.selectByPrimaryKey(lessonId);
		
		//判断是否存在此课程专题
		if(lesson == null){
			throw new Exception("无此课程表！");
		}
		
		return lesson;
	}

	/**
	 * 创建课程进度表
	 * @param weekName 星期名称
	 * @param levelOne 
	 * @param levelTwo
	 * @param levelThree
	 * @param levelFour
	 * @param levelFive
	 * @param levelSix
	 * @param levelSeven
	 * @param levelEight
	 * @param superGrade
	 * @param superClass
	 * @return
	 * @throws Exception
	 */
	public CourseSchedule createCourseSchedule(String weekName, String levelOne, String levelTwo, 
			String levelThree, String levelFour, String levelFive, String levelSix,
			String levelSeven, String levelEight, String superGrade, String superClass) throws Exception{
		
		//创建对像
		CourseSchedule courseSchedule = new CourseSchedule();
		
		courseSchedule.setWeekName(weekName);
		courseSchedule.setLevelOne(levelOne);
		courseSchedule.setLevelTwo(levelTwo);
		courseSchedule.setLevelThree(levelThree);
		courseSchedule.setLevelFour(levelFour);
		courseSchedule.setLevelFive(levelFive);
		courseSchedule.setLevelSix(levelSix);
		courseSchedule.setLevelSeven(levelSeven);
		courseSchedule.setLevelEight(levelEight);
		courseSchedule.setSuperGrade(superGrade);
		courseSchedule.setSuperClass(superClass);
		
		this.courseScheduleMapper.insert(courseSchedule);
		
		return courseSchedule;
	}	
	
	
	
	/**
	 * 修改课程进度表
	 * @param weekName 星期名称
	 * @param levelOne 
	 * @param levelTwo
	 * @param levelThree
	 * @param levelFour
	 * @param levelFive
	 * @param levelSix
	 * @param levelSeven
	 * @param levelEight
	 * @param superGrade
	 * @param superClass
	 * @return
	 * @throws Exception
	 */
	public CourseSchedule modifyCourseSchedule(Long id, String weekName, String levelOne, String levelTwo, 
			String levelThree, String levelFour, String levelFive, String levelSix,
			String levelSeven, String levelEight, String superGrade, String superClass) throws Exception{
		
		CourseSchedule courseSchedule = this.courseScheduleMapper.selectByPrimaryKey(id);
		
		//判断是否存在此课程专题
		if(courseSchedule == null){
			throw new Exception("无此课程表！");
		}
		
		courseSchedule.setWeekName(weekName);
		courseSchedule.setLevelOne(levelOne);
		courseSchedule.setLevelTwo(levelTwo);
		courseSchedule.setLevelThree(levelThree);
		courseSchedule.setLevelFour(levelFour);
		courseSchedule.setLevelFive(levelFive);
		courseSchedule.setLevelSix(levelSix);
		courseSchedule.setLevelSeven(levelSeven);
		courseSchedule.setLevelEight(levelEight);
		courseSchedule.setSuperGrade(superGrade);
		courseSchedule.setSuperClass(superClass);
		
		this.courseScheduleMapper.updateByPrimaryKey(courseSchedule);
		
		return courseSchedule;
	}	
	
	/**
	 * 删除课程进度表
	 * @param coursewareId 课件编号
	 * @throws NoCoursewareException 无此课件
	 */
	public void removeCourseSchedule(Long courseScheduleId) throws Exception{
		
		CourseSchedule courseSchedule = this.courseScheduleMapper.selectByPrimaryKey(courseScheduleId);
		
		//判断是否存在此课程专题
		if(courseSchedule == null){
			throw new Exception("无此课程表！");
		}
		
		this.courseScheduleMapper.deleteByPrimaryKey(courseScheduleId);
	}
	
	
	/**
	 * 获得课程进度表
	 * @param coursewareId 课件编号
	 * @throws NoCoursewareException 无此课件
	 */
	public CourseSchedule getCourseScheduleById(Long courseScheduleId) throws Exception{
		
		CourseSchedule courseSchedule = this.courseScheduleMapper.selectByPrimaryKey(courseScheduleId);
		
		//判断是否存在此课程专题
		if(courseSchedule == null){
			throw new Exception("无此课程表！");
		}
		
		return courseSchedule;
	}
	
}
