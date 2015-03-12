package com.smlearning.domain.entity.enums;

import java.util.TreeMap;

/**
 * 用户状态
 * @author song
 *
 */
public enum UserStatusEnum {
	
	/**
	 * 启用
	 */
	OPENED,
	/**
	 * 停用
	 */
	CLOSED;
	
	/**
	 * 获取枚举类型名称
	 */
	public String toString() {
		
		if (this.equals(OPENED)) { return "启用"; }
		if (this.equals(CLOSED)) { return "停用"; }
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
	public static UserStatusEnum valueOf(Integer value) {
		
		return UserStatusEnum.values()[value];
	}
	
	/**
	 * 获取枚举类型的所有<值,名称>对
	 * @return
	 */
	public static TreeMap<Integer, String> toMap(){
		
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		for (int i=0; i< UserStatusEnum.values().length; i++ ) {
			map.put(UserStatusEnum.values()[i].ordinal(), UserStatusEnum.values()[i].toString());
		}
		return map;
	}		
}
