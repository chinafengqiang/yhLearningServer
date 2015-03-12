package com.smlearning.domain.vo;

public class SysKeyVO implements java.io.Serializable {

	private static final long serialVersionUID = -5731013880270696058L;
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	private String keyName;
	 private String keyValue;
}
