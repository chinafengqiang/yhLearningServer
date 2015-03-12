package com.smlearning.application.service;

import com.smlearning.domain.entity.CourseCourseware;


/**
 *  课程引用课件接口
 * @author Administrator
 */
public interface CourseCoursewareService {

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
	public CourseCourseware useCourseware(Long courseId, Long coursewareId, Long creator) throws Exception;
	
	/**
	 * 取消引用课件
	 * @param courseCoursewareId 课程课件编号
	 * @param userId 操作者
	 * @throws NoCourseCoursewareException 无此课程课件
	 * @throws NonEnoughAccessException 无足够的权限
	 */
	public void unUseCourseware(Long courseCoursewareId) throws Exception;
		
}
