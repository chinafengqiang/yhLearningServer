package com.smlearning.domain.entity.enums;

import java.util.TreeMap;

/**
 * 资源分类
 * @author
 */
public enum CourseUseTypeEnum {
	
	/**
	 * 课件
	 */
	Courseware,
	/**
	 * 电子书
	 */
	Ebooks;
	
	/**
	 * 获取枚举类型名称
	 */
	public String toString() {
		
		if (this.equals(Courseware)) { return "课件"; }
		if (this.equals(Ebooks)) { return "电子书"; }
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
	public static CourseUseTypeEnum valueOf(Integer value) {
		
		return CourseUseTypeEnum.values()[value];
	}
	
	/**
	 * 获取枚举类型的所有<值,名称>对
	 * @return
	 */
	public static TreeMap<Integer, String> toMap(){
		
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		for (int i=0; i< CourseUseTypeEnum.values().length; i++ ) {
			map.put(CourseUseTypeEnum.values()[i].ordinal(), CourseUseTypeEnum.values()[i].toString());
		}
		return map;
	}	
	
}
