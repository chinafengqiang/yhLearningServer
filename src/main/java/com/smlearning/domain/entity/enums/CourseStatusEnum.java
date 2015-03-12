package com.smlearning.domain.entity.enums;

import java.util.TreeMap;

/**
 * 课程状态
 * @author xu
 */
public enum CourseStatusEnum {

	/**
	 * 启用 0
	 */
	OPENED, 
	/**
	 * 停用 1
	 */
	CLOSED,
	/**
	 * 已发送 2
	 */
	SENDED;
	
	/**
	 * 获取枚举类型名称
	 */
	public String toString() {
		
		if (this.equals(OPENED)) { return "启用"; }
		if (this.equals(CLOSED)) { return "停用"; }
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
	public static CourseStatusEnum valueOf(Integer value) {
		
		return CourseStatusEnum.values()[value];
	}
	
	/**
	 * 获取枚举类型的所有<值,名称>对
	 * @return
	 */
	public static TreeMap<Integer, String> toMap(){
		
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		for (int i=0; i< CourseStatusEnum.values().length; i++ ) {
			map.put(CourseStatusEnum.values()[i].ordinal(), CourseStatusEnum.values()[i].toString());
		}
		return map;
	}		
}
