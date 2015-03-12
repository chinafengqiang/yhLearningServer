package com.smlearning.application.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.iactive.db.DataGridModel;
import cn.com.iactive.db.PagerModel;

import com.smlearning.domain.entity.Courseware;
import com.smlearning.domain.entity.userCourseNote;
import com.smlearning.domain.vo.CoursewareVO;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;





/**
 * 课件接口
 * @author Administrator
 */
public interface CoursewareService {

	/**
	 * 返回课件列表数据信息
	 * @param coursewareVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryCoursewarePaning(CoursewareVO coursewareVO, PageHelper ph);
	
	/**
	 * 返回电子书列表数据信息
	 * @param coursewareVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryBookPaning(CoursewareVO coursewareVO, PageHelper ph);
	
	/**
	 * 创建课件
	 * @param name 课件名称
	 * @param courseSubjectId 课程专题编号
	 * @param url 课件网址
	 * @return
	 * @throws NocourseSubjectException 无此课程专题
	 */
	public Courseware createCourseware(String name, Long coursewareCategoryId, String url, Long creator, String pic, Long classId,Long gradeId,Integer isPublic) throws Exception;
	
	/**
	 * 修改课件
	 * @param coursewareId 课件编号
	 * @param name 课件名称
	 * @param url 课件网址
	 * @return
	 * @throws NoCoursewareException 无此课件
	 */
	public Courseware modifyCourseware(Long coursewareId, Long coursewareCategoryId,String name, String url, String pic, Long classId,Long gradeId,Integer isPublic) throws Exception;
	
	/**
	 * 删除课件
	 * @param coursewareId 课件编号
	 * @throws NoCoursewareException 无此课件
	 */
	public void removeCourseware(Long coursewareId) throws Exception;
	
	/**
	 * 获取课件信息
	 * @param coursewareId 课件编号
	 * @throws NoCoursewareException 无此课件
	 */
	public Courseware getCoursewareById(Long coursewareId) throws Exception;
	
	/**
	 * 创建日记
	 * @return
	 * @throws NocourseSubjectException 无此课程专题
	 */
	public userCourseNote createNote(Long userId, Long coursewareId, String note) throws Exception;
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<userCourseNote> queryNote(Long userId);
	
	
	/**
	 * 返回电子书列表数据信息
	 * @param coursewareVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryBookPanings(CoursewareVO coursewareVO, PageHelper ph) ;
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<Courseware> queryBookByCatetory(Long catetoryId);
	
	
	/**
	 * 修改课件状态
	 * @param coursewareId 课件编号
	 * @return
	 * @throws NoCoursewareException 无此课件
	 */
	public Courseware modifyCoursewareStauts(Long coursewareId) throws Exception;
	
	public void deleteCourseware(long[] ids);
	
	
	public HashMap<String, Object> getCoursewareList(DataGridModel dm,HashMap<String,String> params);
	
	public List<Courseware> getCoursewareListByIds(String ids);
	
	public void modifyCoursewareStauts(String ids);
	
	public List<HashMap<String,Object>> getResMPath(int type);
	
	public int getBooks(long classId,long category,List<HashMap<String, Object>> resList);
	
	public int getBooks(long classId,long category,PagerModel<HashMap<String,Object>> pm,int offset,int limit);
	
	public int getBooksCategory(long classId, List<HashMap<String, Object>> resList);
}
