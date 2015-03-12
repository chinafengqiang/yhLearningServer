package com.smlearning.domain.vo;

import java.util.Date;

public class CourseVO implements java.io.Serializable {

	private static final long serialVersionUID = 8183728726629082353L;
	
	 private Long id;
	 private String name;
	 public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCoursewareCategoryId() {
		return coursewareCategoryId;
	}
	public void setCoursewareCategoryId(Long coursewareCategoryId) {
		this.coursewareCategoryId = coursewareCategoryId;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLectuer() {
		return lectuer;
	}
	public void setLectuer(String lectuer) {
		this.lectuer = lectuer;
	}
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	private Long coursewareCategoryId;
	 private String lectuer;
	 private Integer hour = 0;
	 private Long pid;
	 public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	private Integer status;
	 private String pic;
	 private Date createdTime;
	 private Long creator;
	 private String description;
	 private String teacherDescription = "";
	 public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTeacherDescription() {
		return teacherDescription;
	}
	public void setTeacherDescription(String teacherDescription) {
		this.teacherDescription = teacherDescription;
	}
	private String url;
	private Long gradeId = 0L;
	private Integer isPublic = 0;
  public Long getGradeId() {
    return gradeId;
  }
  public void setGradeId(Long gradeId) {
    this.gradeId = gradeId;
  }
  public Integer getIsPublic() {
    return isPublic;
  }
  public void setIsPublic(Integer isPublic) {
    this.isPublic = isPublic;
  }
	
	 
}
