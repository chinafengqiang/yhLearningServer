package com.smlearning.infrastructure.utils;

import java.util.UUID;

public class SystemUtil {

	/**产生UUID
	 * @return
	 */
	public static String createUUID() {
		
		return UUID.randomUUID().toString(); 
	}
}
