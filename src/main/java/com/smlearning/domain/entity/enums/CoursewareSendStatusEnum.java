package com.smlearning.domain.entity.enums;

import java.util.TreeMap;

/**
 * 课程状态
 * @author xu
 */
public enum CoursewareSendStatusEnum {

	/**
	 * 待发送 0
	 */
	WAIT_SENDS, 
	/**
	 * 已发送 1
	 */
	SENDED;
	
	/**
	 * 获取枚举类型名称
	 */
	public String toString() {
		
		if (this.equals(WAIT_SENDS)) { return "待发送"; }
		if (this.equals(SENDED)) { return "已发送"; }
		return "";
	}

	/**
	 * 获取枚举类型数值
	 */
	public Integer toValue() {
	
		return this.ordinal();
	}
	
	/**
	 * 按数值获取对应的枚举类型
	 * @param value 数值
	 * @return 枚举类型
	 */
	public static CoursewareSendStatusEnum valueOf(Integer value) {
		
		return CoursewareSendStatusEnum.values()[value];
	}
	
	/**
	 * 获取枚举类型的所有<值,名称>对
	 * @return
	 */
	public static TreeMap<Integer, String> toMap(){
		
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		for (int i=0; i< CoursewareSendStatusEnum.values().length; i++ ) {
			map.put(CoursewareSendStatusEnum.values()[i].ordinal(), CoursewareSendStatusEnum.values()[i].toString());
		}
		return map;
	}		
}
