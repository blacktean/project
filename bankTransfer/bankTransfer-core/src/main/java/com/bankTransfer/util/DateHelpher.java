package com.bankTransfer.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 给当前时间添加 一个月 三个月 六个月 十二个月 10年
 * 
 * @author zsy
 *
 */
public class DateHelpher {
	public static Date subOneMonth(Date date) {

		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.MONTH, 1);
		Date dt1 = rightNow.getTime();
		return dt1;
	}

	public static Date subThreeMonth(Date date) {

		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.MONTH, 3);
		Date dt1 = rightNow.getTime();
		return dt1;
	}

	public static Date subSixMonth(Date date) {

		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.MONTH, 6);
		Date dt1 = rightNow.getTime();
		return dt1;
	}

	public static Date subTwlMonth(Date date) {

		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.MONTH, 12);
		Date dt1 = rightNow.getTime();
		return dt1;
	}

	public static Date subTenYear(Date date) {

		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.YEAR, 10);
		Date dt1 = rightNow.getTime();
		return dt1;
	}

	/**
	 * 减少指定的天数
	 * 
	 * @param number
	 * @return
	 */
	public static Date addDay(Integer number) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(new Date());
		rightNow.add(Calendar.DATE, -number);
		return rightNow.getTime();

	}
}
