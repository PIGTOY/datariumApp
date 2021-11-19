package com.hanadocu.util;

public class StringUtil {

	/** 
	 * 빈 값 체크
	 * @param string
	 * @return boolean
	 */
	public static boolean checkEmptyString(String str) {
		
		boolean result = false;
		
		if(str == null || "null".equals(str) || "".equals(str)) {
			return true;
		}
		
		return result;
	}
}
