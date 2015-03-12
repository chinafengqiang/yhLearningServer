package com.smlearning.domain.vo;

public class ManagerVO implements java.io.Serializable {

	  private static final long serialVersionUID = 5978682153921920832L;
	  private Long id;
	  private String name;
	  private String password;
	  private String actualName;
	  private String department;
	  private String post;
	  private Long superManagerId=0L;
	  private String newPassword;
	  private Long classId = 0L;
	  public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public Long getId() {
		return id;
	}
	public Long getSuperManagerId() {
		return superManagerId;
	}
	public void setSuperManagerId(Long superManagerId) {
		this.superManagerId = superManagerId;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getActualName() {
		return actualName;
	}
	public void setActualName(String actualName) {
		this.actualName = actualName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public Integer getDegree() {
		return degree;
	}
	public void setDegree(Integer degree) {
		this.degree = degree;
	}
	public Integer getUseType() {
		return useType;
	}
	public void setUseType(Integer useType) {
		this.useType = useType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	private Integer degree = 1;
	  private Integer useType = 0;
	  private Integer status = 0;
}
