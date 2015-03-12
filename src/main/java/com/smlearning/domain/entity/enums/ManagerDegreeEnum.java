package com.smlearning.domain.entity.enums;

import java.util.TreeMap;

/**
 * 管理员等级
 * @author
 */
public enum ManagerDegreeEnum {
	
	/**
	 * 系统管理员
	 */
	SUPER_MANAGER,
	/**
	 * 管理员
	 */
	MANAGER;
	
	/**
	 * 获取枚举类型名称
	 */
	public String toString() {
		
		if (this.equals(SUPER_MANAGER)) { return "系统管理员"; }
		if (this.equals(MANAGER)) { return "管理员"; }
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
	public static ManagerDegreeEnum valueOf(Integer value) {
		
		return ManagerDegreeEnum.values()[value];
	}
	
	/**
	 * 获取枚举类型的所有<值,名称>对
	 * @return
	 */
	public static TreeMap<Integer, String> toMap(){
		
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		for (int i=0; i< ManagerDegreeEnum.values().length; i++ ) {
			map.put(ManagerDegreeEnum.values()[i].ordinal(), ManagerDegreeEnum.values()[i].toString());
		}
		return map;
	}	
	
}
