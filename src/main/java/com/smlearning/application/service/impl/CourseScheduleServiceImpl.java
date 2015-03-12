package com.smlearning.application.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smlearning.application.service.CourseScheduleService;
import com.smlearning.domain.activity.CourseScheduleActivity;
import com.smlearning.domain.entity.CourseSchedule;
import com.smlearning.domain.entity.Lesson;
import com.smlearning.domain.entity.extend.LessonExtend;
import com.smlearning.domain.vo.CourseScheduleVO;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;

/**
 * 课程表业务方法
 * @author Administrator
 */
@Service
public class CourseScheduleServiceImpl implements CourseScheduleService{

	static Logger logger = Logger.getLogger(CourseScheduleServiceImpl.class.getName());
	
	@Autowired
	private CourseScheduleActivity scheduleActivity;
	
	/**
	 * @param ph
	 * @return
	 */
	public DataGrid querySchedulePaning(CourseScheduleVO scheduleVO, PageHelper ph) {
		return scheduleActivity.querySchedulePaning(scheduleVO, ph);
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
		return scheduleActivity.createCourseSchedule(weekName, levelOne, levelTwo, levelThree, levelFour,
				levelFive, levelSix, levelSeven, levelEight, superGrade, superClass);
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
		return scheduleActivity.modifyCourseSchedule(id, weekName, levelOne, levelTwo, levelThree, levelFour,
				levelFive, levelSix, levelSeven, levelEight, superGrade, superClass);
	}
	
	/**
	 * 删除课程进度表
	 * @param coursewareId 课件编号
	 * @throws NoCoursewareException 无此课件
	 */
	public void removeCourseSchedule(Long courseScheduleId) throws Exception{
		scheduleActivity.removeCourseSchedule(courseScheduleId);
	}
	
	/**
	 * 获得课程进度表
	 * @param coursewareId 课件编号
	 * @throws NoCoursewareException 无此课件
	 */
	public CourseSchedule getCourseScheduleById(Long courseScheduleId) throws Exception{
		return scheduleActivity.getCourseScheduleById(courseScheduleId);
	}
	
	/**
	 * 返回列表数据信息
	 * @param lessonExtend
	 * @param ph
	 * @return
	 */
	public DataGrid queryLessonPaning(LessonExtend lessonExtend, PageHelper ph) {
		return scheduleActivity.queryLessonPaning(lessonExtend, ph);
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
		return scheduleActivity.createLesson(classId, subjectId, day, thetime, term);
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
		return scheduleActivity.modifyLesson(lessonId, classId, subjectId, day, thetime, term);
	}
	
	
	/**
	 * 删除课程进度表
	 */
	public void removeLesson(Long lessonId) throws Exception{
		scheduleActivity.removeLesson(lessonId);
	}
	
	/**
	 * 获得课程进度表
	 * @param coursewareId 课件编号
	 * @throws NoCoursewareException 无此课件
	 */
	public Lesson getLessonById(Long lessonId) throws Exception{
		return scheduleActivity.getLessonById(lessonId);
	}
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<LessonExtend> queryLesson(LessonExtend lessonExtend) {
		return scheduleActivity.queryLesson(lessonExtend);
	}
	
}
