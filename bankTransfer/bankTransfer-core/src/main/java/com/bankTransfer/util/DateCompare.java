package com.bankTransfer.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间比较类
 * @author Administrator
 *
 */
public class DateCompare {
	private static final String patten = "HH"; 
	private static final SimpleDateFormat sdf = new SimpleDateFormat(patten);
	/**
	 * 处理小时
	 * @param now
	 * @param toCompare
	 * @return
	 */
	public static boolean compareHour(Date now,Date toCompare) {
		int rs =Integer.parseInt(sdf.format(now))- Integer.parseInt(sdf.format(toCompare));
		if(rs > 1 || (rs < 0 && (rs+24)>1)) {
			return true;
		}
		return false;
	}
	/**
	 * 处理天
	 * @param now
	 * @param toCompare
	 * @return
	 */
	public static boolean compareDay(Date now,Date toCompare) {
		int rs =Integer.parseInt(sdf.format(now))- Integer.parseInt(sdf.format(toCompare));
		if(rs > 24 || (rs < 0 && (rs+24)>24)) {
			return true;
		}
		return false;
	}
	
}
