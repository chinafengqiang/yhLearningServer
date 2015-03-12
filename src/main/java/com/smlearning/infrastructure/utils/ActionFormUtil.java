package com.smlearning.infrastructure.utils;

import org.apache.commons.lang.StringUtils;

public class ActionFormUtil {

	/**
	 * 校验输入数据是否为空
	 * @param fieldValue 字段�?
	 * @param message 校验失败的提示信�?(若消息为�?,则不提示)
	 * @throws Exception 异常(含提示消�?)
	 */
	public static void verifyInputByNull(Object fieldValue, String message) throws Exception {
	
		if (fieldValue == null) {
			throw new Exception(message);
		}
        if (StringUtils.isBlank(fieldValue.toString())){
       		throw new Exception(message);
        } 
	}
	
	/**
	 * @param value
	 * @return
	 */
	public static Boolean getCheckBox(String value) {
		
		if (value != null){
			return value.equalsIgnoreCase("true");
		} else {
			return false;
		}
	}
	
	public static Integer getCheckBoxByInteger(String value) {
		
		return DataTypeConvertor.booleanToInteger(getCheckBox(value));
	}
	
	/**
	 * 判段下拉列表框是否�?�中
	 * @param value 
	 * @return 返回long
	 */
	public static Long getSelectByNoSelectFromZero(Long value) {
		
		if (value != null) {
			if (value == 0l) {
				return null;
			} else {
				return value;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * 判段下拉列表框是否�?�中
	 * @param value
	 * @return 返回Integer
	 */
	public static Integer getSelectByNoSelectFromZero(Integer value) {
		
		if (value != null) {
			if (value == 0) {
				return null;
			} else {
				return value;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * 判段checkbox是否选中
	 * @param value
	 * @return
	 */
	public static Integer getCheckBoxZero(Integer value) {
		
		if (value != null) {
			if (value == 0) {
				return null;
			} else {
				return value;
			}
		} else {
			return null;
		}
	}
}
