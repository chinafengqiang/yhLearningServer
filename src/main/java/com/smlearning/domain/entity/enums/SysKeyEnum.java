package com.smlearning.domain.entity.enums;

import java.util.TreeMap;

/**
 * 课件服务器状态
 * @author
 */
public enum SysKeyEnum {
	
	/**
	 * 性别　0
	 */
	SEX,
	/**
	 * 部门　1
	 */
	DEPARTMENT,
	/**
	 * 职务　2
	 */
	POST,
	/**
	 * 考试分类　3
	 */
	EXAM_CATEGORY,
	/**
	 * 试卷分类　4
	 */
	TEST_PAPER_CATEGORY,
	/**
	 * 课程分类　5
	 */
	COURSE_CATEGORY,
	/**
	 * 题库集　6
	 */
	QUESTION_LIBRARY_SET,
	/**
	 * 课程库集　7
	 */
	COURSE_LIBRARY_SET,
	
	/**
	 * 民族 8
	 */
	NATION,
	
	/**
	 * 政治面貌 9
	 */
	POLITICAL_LANDSCAPE,
	
	/**
	 * 学历 10
	 */
	EXPERIENCE,
	
	/**
	 * 职务级别 11
	 */
	DUTY_RANK,
	
    /**
     * 证件类型 12
     */
    CERTIFICATE_TYPE,
	
	/**
	 * 服务地址13
	 */
	SERVER_IP;
	
	/**
	 * 获取枚举类型名称
	 */
	public String toString() {
		
		if (this.equals(SEX)){ return "性别";}
		if (this.equals(DEPARTMENT)){ return "部门";}
		if (this.equals(POST)){ return "职务";}
		if (this.equals(EXAM_CATEGORY)){ return "考试分类";}
		if (this.equals(TEST_PAPER_CATEGORY)){ return "试卷分类";}
		if (this.equals(COURSE_CATEGORY)){ return "课程分类";}
		if (this.equals(QUESTION_LIBRARY_SET)){ return "题库集";}
		if (this.equals(COURSE_LIBRARY_SET)){ return "课程库集";}
		if (this.equals(NATION)){ return "民族";}
		if (this.equals(POLITICAL_LANDSCAPE)){ return "政治面貌";}
		if (this.equals(EXPERIENCE)){ return "学历";}
		if (this.equals(DUTY_RANK)){ return "职务级别";}
		if (this.equals(CERTIFICATE_TYPE)){ return "证件类型";}
		if (this.equals(SERVER_IP)){ return "服务地址";}
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
	public static SysKeyEnum valueOf(Integer value) {
		
		return SysKeyEnum.values()[value];
	}
	
	/**
	 * 获取枚举类型的所有<值,名称>对
	 * @return
	 */
	public static TreeMap<Integer, String> toMap(){
		
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		for (int i=0; i< SysKeyEnum.values().length; i++ ) {
			map.put(SysKeyEnum.values()[i].ordinal(), SysKeyEnum.values()[i].toString());
		}
		return map;
	}		
}
