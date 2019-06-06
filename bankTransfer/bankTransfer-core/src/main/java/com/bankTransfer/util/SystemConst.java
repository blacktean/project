package com.bankTransfer.util;

import java.math.BigDecimal;

public class SystemConst {
	// 防止丢失精度, 不要使用 new BigDecimal(double) 而使用new BigDecimal(String)
	public static final BigDecimal ZERO = new BigDecimal("0.0000");
	public static final BigDecimal INITIAL_BORROW_LIMIT = new BigDecimal(
			"2000.0000");
	
	//图片公共文件夹
	public static final String PUBLIC_IMG_PATH ="D:/uplaod/p2p";
	
	//验证码有效时间
	public static final int VERIFYCODE_VALIDATE_TIME = 20 * 60;
}
