package com.smlearning.infrastructure.utils;

import java.util.HashMap;
import java.util.Map;
 
/** 
 * 文件类型选项
 * @author 
 */
public enum FileTypeEnum {

	JPG,
	GIF,
	PNG,
	OTHER;
	/**
	 * 获取枚举类型名称
	 */
	public String toString() {
		
		if (this.equals(JPG)) { return "jpg格式图像文件"; }
		if (this.equals(GIF)) { return "gif格式图像文件"; }
		if (this.equals(PNG)) { return "png格式图像文件"; }
		if (this.equals(OTHER)) { return "其他文件"; }
		return "";
	}

	/**
	 * 获取枚举类型数�??
	 */
	public Integer toValue() {
	
		return this.ordinal();
	}
	
	/**
	 * 按数值获取对应的枚举类型
	 * @param value 数�??
	 * @return 枚举类型
	 */
	public static FileTypeEnum valueOf(Integer value) {
		
		return FileTypeEnum.values()[value];
	}
	
	/**
	 * 获取枚举类型的所�?<�?,名称>�?
	 * @return
	 */
	public static Map<Integer, String> toMap(){
		
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (int i=0; i< FileTypeEnum.values().length; i++ ) {
			map.put(FileTypeEnum.values()[i].ordinal(), FileTypeEnum.values()[i].toString());
		}
		return map;
	}		
}
