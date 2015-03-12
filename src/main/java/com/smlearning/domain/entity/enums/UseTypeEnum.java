package com.smlearning.domain.entity.enums;

import java.util.TreeMap;

/**
 * 教师类型
 * @author
 */
public enum UseTypeEnum {
	
	/**
	 * 主教
	 */
	MTEACHER,
	/**
	 * 助教
	 */
	HTEACHER;
	
	/**
	 * 获取枚举类型名称
	 */
	public String toString() {
		
		if (this.equals(MTEACHER)) { return "主教"; }
		if (this.equals(HTEACHER)) { return "助教"; }
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
	public static UseTypeEnum valueOf(Integer value) {
		
		return UseTypeEnum.values()[value];
	}
	
	/**
	 * 获取枚举类型的所有<值,名称>对
	 * @return
	 */
	public static TreeMap<Integer, String> toMap(){
		
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		for (int i=0; i< UseTypeEnum.values().length; i++ ) {
			map.put(UseTypeEnum.values()[i].ordinal(), UseTypeEnum.values()[i].toString());
		}
		return map;
	}	
	
}
