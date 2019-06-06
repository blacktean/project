package com.bankTransfer.service;

public interface IVerifyCodeService {

	/**
	 * 发送验证码
	 * @param phoneNumber 电话
	 */
	public void sendVerifyCode(String phoneNumber);
	
	/**
	 * 验证
	 * @param phoneNumber 电话
	 * @param verifyCode 验证码
	 * @return
	 */
	public boolean checkVerifyCode(String phoneNumber, String verifyCode);
}
