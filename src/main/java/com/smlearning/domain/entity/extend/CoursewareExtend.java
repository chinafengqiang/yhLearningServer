package com.smlearning.domain.entity.extend;

import java.util.Date;

import com.smlearning.domain.entity.enums.CoursewareSendStatusEnum;
import com.smlearning.infrastructure.utils.DateUtil;



public class CoursewareExtend implements java.io.Serializable {
	
	private static final long serialVersionUID = 2758820714104235202L;

	 private Long id;
	 private String name;
	 private String url;
	 private Date createdTime;
	 private Long creator;
	 private String categoryName;
	 private String pic;
	 private Long gradeId;
    @SuppressWarnings("unused")
    private String statusName;
    private Integer status;
    private String strCreateTime; 
    private String gradeName;
    
    
 	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatusName() {
		if(status!= null){
			return CoursewareSendStatusEnum.valueOf(this.status).toString();
		}
		return "";
 	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
		this.strCreateTime = DateUtil.dateToString(createdTime, false);
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	public Long getGradeId() {
		return gradeId;
	}
	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}
	public String getStrCreateTime() {
		return strCreateTime;
	}
	public void setStrCreateTime(String strCreateTime) {
		this.strCreateTime = strCreateTime;
	}
	 
}
