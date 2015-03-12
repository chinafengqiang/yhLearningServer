package com.smlearning.domain.activity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smlearning.domain.entity.Courseware;
import com.smlearning.domain.entity.CoursewareExample;
import com.smlearning.domain.entity.userCourseNote;
import com.smlearning.domain.entity.userCourseNoteExample;
import com.smlearning.domain.entity.enums.CourseUseTypeEnum;
import com.smlearning.domain.entity.enums.CoursewareSendStatusEnum;
import com.smlearning.domain.entity.extend.CoursewareExtend;
import com.smlearning.domain.vo.CoursewareVO;
import com.smlearning.infrastructure.dao.CoursewareCategoryMapper;
import com.smlearning.infrastructure.dao.CoursewareMapper;
import com.smlearning.infrastructure.dao.userCourseNoteMapper;
import com.smlearning.infrastructure.utils.DataGrid;
import com.smlearning.infrastructure.utils.PageHelper;


/**
 * 课件领域活动 聚合
 * @author Administrator
 */
@Repository
public class CoursewareActivity {

	static Logger logger = Logger.getLogger(CoursewareActivity.class.getName());
	
	/**
	 * 上传电子书路径名称
	 */
	private final static String fileName = "/uploadFile/file/";
	
	/**
	 * 上传图片路径名称
	 */
	private final static String picName = "/uploadFile/pic/"; 
	
	//课程基础层
	@Autowired
	private CoursewareMapper coursewareMapper;
	
	//课程分类基础层
	@Autowired
	private CoursewareCategoryMapper coursewareCategoryMapper;
	
	//课程日记基础层
	@Autowired
	private userCourseNoteMapper userCourseNoteMapper;
	
	/**
	 * 返回课件列表数据信息
	 * @param coursewareVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryCoursewarePaning(CoursewareVO coursewareVO, PageHelper ph) {
		logger.info("queryCoursewarePaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<CoursewareExtend> sList = null;
		int sCount = 0;
		try{
			CoursewareExample mEx = new CoursewareExample();
			mEx.setUseType(CourseUseTypeEnum.Courseware.toValue());
			if(StringUtils.isNotBlank(coursewareVO.getName())){
				mEx.setName(coursewareVO.getName());
			}
			if(StringUtils.isNotBlank(coursewareVO.getCategoryName())){
				mEx.setCategoryName(coursewareVO.getCategoryName());
			}
			
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  coursewareMapper.selectByExamplePaging(mEx); 
			sCount = coursewareMapper.countByExample(mEx);
		}catch(Exception ex){
			logger.error("Error of queryCoursewarePaning",ex);
		}
		logger.info("queryCoursewarePaning result :" +  sList.size() );
		logger.info("queryCoursewarePaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	/**
	 * 返回电子书列表数据信息
	 * @param coursewareVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryBookPaning(CoursewareVO coursewareVO, PageHelper ph) {
		logger.info("queryBookPaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
		List<CoursewareExtend> sList = null;
		int sCount = 0;
		try{
			CoursewareExample mEx = new CoursewareExample();
			mEx.setUseType(CourseUseTypeEnum.Ebooks.toValue());
			mEx.setClassId(coursewareVO.getClassId());
			if(StringUtils.isNotBlank(coursewareVO.getName())){
				mEx.setName(coursewareVO.getName());
			}
			if(StringUtils.isNotBlank(coursewareVO.getCategoryName())){
				mEx.setCategoryName(coursewareVO.getCategoryName());
			}
			mEx.setOrder(ph.getOrder());
			mEx.setSort(ph.getSort());
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  coursewareMapper.selectByExamplePaging(mEx); 
			sCount = coursewareMapper.countByExamplePaging(mEx);
		}catch(Exception ex){
			logger.error("Error of queryBookPaning",ex);
		}
		logger.info("queryBookPaning result :" +  sList.size() );
		logger.info("queryBookPaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	
	/**
	 * 返回电子书列表数据信息
	 * @param coursewareVO
	 * @param ph
	 * @return
	 */
	public DataGrid queryBookPanings(CoursewareVO coursewareVO, PageHelper ph) {
		logger.info("queryBookPaning :" +  ph.getPage()+ "," + ph.getRows());
		
		DataGrid dg = new DataGrid();
 		List<CoursewareExtend> sList = null;
		int sCount = 0;
		try{
			CoursewareExample mEx = new CoursewareExample();
			mEx.setUseType(CourseUseTypeEnum.Ebooks.toValue());
			mEx.setClassId(coursewareVO.getClassId());
			if(StringUtils.isNotBlank(coursewareVO.getName())){
				mEx.setName(coursewareVO.getName());
			}
			
			if(null != coursewareVO.getCategoryId()){
				mEx.setCategoryId(coursewareVO.getCategoryId());
			}
			
			if(StringUtils.isNotBlank(coursewareVO.getCategoryName())){
				mEx.setCategoryName(coursewareVO.getCategoryName());
			}
			
//			if(null != coursewareVO.getClassId()){
//				
//				logger.info("queryBookPaning :coursewareVO.getClassId()coursewareVO.getClassId()coursewareVO.getClassId()" + coursewareVO.getClassId());
//				mEx.setClassId(coursewareVO.getClassId());
//			}
			
			mEx.setOrderByClause("id ");
			
			mEx.setOffset((ph.getPage() - 1 ) * ph.getRows()); // 偏移3
			mEx.setLimit(ph.getRows()); // 取55条   ， 即 4-58
			sList =  coursewareMapper.selectByExamplePagings(mEx); 
			sCount = coursewareMapper.countByExamplePagings(mEx);
		}catch(Exception ex){
			logger.error("Error of queryBookPaning",ex);
		}
		logger.info("queryBookPaning result :" +  sList.size() );
		logger.info("queryBookPaning sCount :" +  sCount );
		
		dg.setRows(sList);
        dg.setTotal(new Long(sCount));
		
		return dg;
	}
	
	/**
	 * 创建课件
	 * @param name 课件名称
	 * @param courseSubjectId 课程专题编号
	 * @param url 课件网址
	 * @return
	 * @throws NocourseSubjectException 无此课程专题
	 */
	public Courseware createCourseware(String name, Long coursewareCategoryId, String url, Long creator, String pic, Long classId,Long gradeId,Integer isPublic) throws Exception{
		
		//判断是否存在此课件分类
		if(this.coursewareCategoryMapper.selectByPrimaryKey(coursewareCategoryId) == null){
			throw new Exception("无此课件分类");
		}
		
		//创建对像
		Courseware courseware = new Courseware();
		
		courseware.setName(name);
		courseware.setCoursewareCategoryId(coursewareCategoryId);
		courseware.setUrl(fileName + url);
		courseware.setCreatedTime(new Date());
		courseware.setCreator(creator);
		if(pic != null && !"".equals(pic))
		  courseware.setPic(picName + pic);
		courseware.setClassId(classId);
		courseware.setStatus(0);
		courseware.setGradeId(gradeId);
		courseware.setIsPublic(isPublic);
		this.coursewareMapper.insert(courseware);
		
		
		return courseware;
	}
	
	/**
	 * 修改课件
	 * @param coursewareId 课件编号
	 * @param name 课件名称
	 * @param url 课件网址
	 * @return
	 * @throws NoCoursewareException 无此课件
	 */
	public Courseware modifyCourseware(Long coursewareId, Long coursewareCategoryId, String name, String url, String pic, Long classId,Long gradeId,Integer isPublic) throws Exception{
		
		logger.info("coursewareId=="+coursewareId);
		
		Courseware courseware = this.coursewareMapper.selectByPrimaryKey(coursewareId);
		
		//判断是否存在此课程专题
		if(courseware == null){
			throw new Exception("无此课件！");
		}
		
		courseware.setCoursewareCategoryId(coursewareCategoryId);
		courseware.setName(name);
		
		if(pic!=null&&!"".equals(pic)){
		  if(pic.contains("uploadFile")){
              courseware.setPic(pic);
          } else{
              courseware.setPic(picName + pic);
          }
		}
		
  		if(url.contains("uploadFile")){
            courseware.setUrl(url);
        } else{
            courseware.setUrl(fileName + url);
        }
		
		courseware.setStatus(0);
		courseware.setClassId(classId);
		courseware.setGradeId(gradeId);
		courseware.setIsPublic(isPublic);
		this.coursewareMapper.updateByPrimaryKeySelective(courseware);
		
		return courseware;
	}
	
	/**
	 * 删除课件
	 * @param coursewareId 课件编号
	 * @throws NoCoursewareException 无此课件
	 */
	public void removeCourseware(Long coursewareId) throws Exception{
		
		Courseware courseware = this.coursewareMapper.selectByPrimaryKey(coursewareId);
		
		//判断是否存在此课程专题
		if(courseware == null){
			throw new Exception("无此课件！");
		}
		
		this.coursewareMapper.deleteByPrimaryKey(coursewareId);
	}
	
	/**
	 * 获取课件信息
	 * @param coursewareId 课件编号
	 * @throws NoCoursewareException 无此课件
	 */
	public Courseware getCoursewareById(Long coursewareId) throws Exception{
		
		Courseware courseware = this.coursewareMapper.selectByPrimaryKey(coursewareId);
		
		//判断是否存在此课程专题
		if(courseware == null){
			throw new Exception("无此课件！");
		}
		
		return courseware;
	}
	
	/**
	 * 创建日记
	 * @return
	 * @throws NocourseSubjectException 无此课程专题
	 */
	public userCourseNote createNote(Long userId, Long coursewareId, String note) throws Exception{
		
		//创建对像
		userCourseNote courseNote = new userCourseNote();
		
		courseNote.setUserId(userId);
		courseNote.setCoursewareId(1l);
		courseNote.setNote(note);
		courseNote.setCreatedTime(new Date());
		
		this.userCourseNoteMapper.insert(courseNote);
		
		return courseNote;
	}
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<userCourseNote> queryNote(Long userId) {
		
		List<userCourseNote> sList = null;
		userCourseNoteExample ex = new userCourseNoteExample();
		ex.or().andUserIdEqualTo(userId);
		sList =  userCourseNoteMapper.selectByExampleWithBLOBs(ex); 
		
		return sList;
	}
	
	
	/**
	 * 返回数据信息
	 * @return
	 */
	public List<Courseware> queryBookByCatetory(Long catetoryId) {
		
		List<Courseware> sList = null;
		
		CoursewareExample mEx = new CoursewareExample();
		mEx.or().andCoursewareCategoryIdEqualTo(catetoryId);
		mEx.setUseType(CourseUseTypeEnum.Ebooks.toValue());
		
		sList =  coursewareMapper.selectByExample(mEx); 
		return sList;
	}
	
	/**
	 * 修改课件状态
	 * @param coursewareId 课件编号
	 * @return
	 * @throws NoCoursewareException 无此课件
	 */
	public Courseware modifyCoursewareStauts(Long coursewareId) throws Exception{
		
		logger.info("coursewareId=="+coursewareId);
		
		Courseware courseware = this.coursewareMapper.selectByPrimaryKey(coursewareId);
		
		//判断是否存在此课程专题
		if(courseware == null){
			throw new Exception("无此课件！");
		}
		
		courseware.setStatus(CoursewareSendStatusEnum.SENDED.toValue());
		
		this.coursewareMapper.updateByPrimaryKeySelective(courseware);
		
		return courseware;
	}
	
	
}
