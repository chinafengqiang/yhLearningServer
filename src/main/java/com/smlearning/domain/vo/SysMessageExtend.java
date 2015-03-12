package com.smlearning.domain.vo;


public class SysMessageExtend implements java.io.Serializable {

	private static final long serialVersionUID = -5918123953970580274L;
	
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
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	private Long id;
	private String name;
	private String createdTime;
	private String content;
	private String creator;
	private Long class_id;
	private String organGrade;

	public String getOrganGrade() {
		return organGrade;
	}
	public void setOrganGrade(String organGrade) {
		this.organGrade = organGrade;
	}
	public Long getClass_id() {
		return class_id;
	}
	public void setClass_id(Long class_id) {
		this.class_id = class_id;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	
}
