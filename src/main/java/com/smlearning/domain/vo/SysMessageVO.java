package com.smlearning.domain.vo;

import java.util.Date;

public class SysMessageVO implements java.io.Serializable {

	private static final long serialVersionUID = -2498081426368969804L;
	private Long id;
	private String name;
	private Integer status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private Date createdTime;
	private Long creator;
	private String organName;
	private String organGrade;
	private Integer organDegree;
	private String content;
	private Date sentTime;
	private Long classId;
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	private Integer canSendAll;
	
	public Integer getCanSendAll() {
		return canSendAll;
	}
	public void setCanSendAll(Integer canSendAll) {
		this.canSendAll = canSendAll;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getOrganGrade() {
		return organGrade;
	}
	public void setOrganGrade(String organGrade) {
		this.organGrade = organGrade;
	}
	public Integer getOrganDegree() {
		return organDegree;
	}
	public void setOrganDegree(Integer organDegree) {
		this.organDegree = organDegree;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSentTime() {
		return sentTime;
	}
	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}
	
}
