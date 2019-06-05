package com.bankTransfer.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 给当前时间添加 一个月 三个月 六个月 十二个月
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

}
