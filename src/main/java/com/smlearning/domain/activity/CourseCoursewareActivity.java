package com.smlearning.domain.activity;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smlearning.domain.entity.CourseCourseware;
import com.smlearning.domain.entity.CourseCoursewareExample;
import com.smlearning.infrastructure.dao.CourseCoursewareMapper;
import com.smlearning.infrastructure.dao.CourseMapper;
import com.smlearning.infrastructure.dao.CoursewareMapper;

/**
 * 课程引用课件领域活动 聚合
 * @author Administrator
 */
@Repository
public class CourseCoursewareActivity {

	static Logger logger = Logger.getLogger(CourseCoursewareActivity.class.getName());
	
	//课程引用课件基础层
	@Autowired
	private CourseCoursewareMapper courseCoursewareMapper;
	
	//课程基础层
	@Autowired
	private CoursewareMapper coursewareMapper;
	
	//课程基础层
	@Autowired
	private CourseMapper courseMapper;
	
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
		
		//判断是否存在课程
		if (this.courseMapper.selectByPrimaryKey(courseId) == null) {
			throw new Exception("无此课程！");
		}
		//判断是否存在课件
		if (this.coursewareMapper.selectByPrimaryKey(coursewareId) == null) {
			throw new Exception("无此课件！");
		}
		
		
		CourseCoursewareExample example = new CourseCoursewareExample();
		example.or().andCourseIdEqualTo(courseId);
		example.or().andCoursewareIdEqualTo(coursewareId);
		
		//获得管理员列表
		List<CourseCourseware> list = this.courseCoursewareMapper.selectByExample(example);
		if(list.size() > 0){
			throw new Exception("重复引用课件");
		}
		
		CourseCourseware courseCourseware = new CourseCourseware();
		
		courseCourseware.setCourseId(courseId);
		courseCourseware.setCoursewareId(coursewareId);
		courseCourseware.setCreator(creator);
		courseCourseware.setCreatedTime(new Date());
		
		this.courseCoursewareMapper.insert(courseCourseware);
		
		return courseCourseware;
	}
	
	/**
	 * 取消引用课件
	 * @param courseCoursewareId 课程课件编号
	 * @param userId 操作者
	 * @throws NoCourseCoursewareException 无此课程课件
	 * @throws NonEnoughAccessException 无足够的权限
	 */
	public void unUseCourseware(Long courseCoursewareId) throws Exception {
		
		CourseCourseware courseCourseware = this.courseCoursewareMapper.selectByPrimaryKey(courseCoursewareId);
		
		//判断是否存在此课程课件
		if (courseCourseware == null) {
			throw new Exception("无此课程课件");
		}
		
		this.courseCoursewareMapper.deleteByPrimaryKey(courseCoursewareId);
	}
	
}
