package com.smlearning.domain.entity.enums;

import java.util.TreeMap;

/**
 * 管理员等级
 * @author
 */
public enum OrganDegreeEnum {
	
	/**
	 * 上级单位
	 */
	HIGHER,
	/**
	 * 本单位
	 */
	SELF,	
	/**
	 * 下级单位
	 */
	LOWER;
	
	/**
	 * 获取枚举类型名称
	 */
	public String toString() {
		
		if (this.equals(HIGHER)) { return "上级单位"; }
		if (this.equals(SELF)) { return "本单位"; }
		if (this.equals(LOWER)) { return "下级单位"; }
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
	public static OrganDegreeEnum valueOf(Integer value) {
		
		return OrganDegreeEnum.values()[value];
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
