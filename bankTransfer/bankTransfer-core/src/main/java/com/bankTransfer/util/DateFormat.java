package com.bankTransfer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 将String类型的值转换成Date类型的值
 * @author fj
 *
 */
public class DateFormat {
	private static SimpleDateFormat[] formatArr={
		new SimpleDateFormat("yyyy-MM-dd"),
		new SimpleDateFormat("yyyy/MM/dd"),
		new SimpleDateFormat("yyyy年MM月dd"),
	}; 
	
	public static Date parseDate(String str){
		for (SimpleDateFormat format : formatArr) {
			try {
				return format.parse(str);
			} catch (ParseException e) {
				continue;
			}
		}
		throw new RuntimeException("日期格式匹配错误!");
	}
}
