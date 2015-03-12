package com.smlearning.domain.entity.enums;

import java.util.TreeMap;

/**
 * 管理员状态
 * @author
 */
public enum ManagerStatusEnum {
	
	/**
	 * 已启用
	 */
	OPENED,
	/**
	 * 已停用
	 */
	CLOSED;
	
	/**
	 * 获取枚举类型名称
	 */
	public String toString() {
		
		if (this.equals(OPENED)) { return "已启用"; }
		if (this.equals(CLOSED)) { return "已停用"; }
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
	public static ManagerStatusEnum valueOf(Integer value) {
		
		return ManagerStatusEnum.values()[value];
	}
	
	/**
	 * 获取枚举类型的所有<值,名称>对
	 * @return
	 */
	public static TreeMap<Integer, String> toMap(){
		
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		for (int i=0; i< ManagerStatusEnum.values().length; i++ ) {
			map.put(ManagerStatusEnum.values()[i].ordinal(), ManagerStatusEnum.values()[i].toString());
		}
		return map;
	}		
}
