package com.smlearning.application.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smlearning.application.service.CourseCoursewareService;
import com.smlearning.domain.activity.CourseCoursewareActivity;
import com.smlearning.domain.entity.CourseCourseware;

/**
 * 课程引用课件分类业务方法
 * @author Administrator
 */
@Service
public class CourseCoursewareServiceImpl implements CourseCoursewareService{

	static Logger logger = Logger.getLogger(CourseCoursewareServiceImpl.class.getName());
	
	@Autowired
	private CourseCoursewareActivity courseCoursewareActivity;

	/**
	 * 课程引用课件
	 * @param courseId 课程编号
	 * @param coursewareId 课件编号
	 * @param organId 单位编号
	 * @param creator 创建者
	 * @return 课程课件编号
	 * @throws NoCourseException 无此课程
	 * @throws NoCoursewareException 无此课件
	 * @throws RepeatCourseCoursewareException 重复引用课件
	 */
	public CourseCourseware useCourseware(Long courseId, Long coursewareId, Long creator) throws Exception {
		return courseCoursewareActivity.useCourseware(courseId, coursewareId, creator);
	}
	
	/**
	 * 取消引用课件
	 * @param courseCoursewareId 课程课件编号
	 * @param userId 操作者
	 * @throws NoCourseCoursewareException 无此课程课件
	 * @throws NonEnoughAccessException 无足够的权限
	 */
	public void unUseCourseware(Long courseCoursewareId) throws Exception {
		courseCoursewareActivity.unUseCourseware(courseCoursewareId);
	}
	
}
