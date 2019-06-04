package com.bankTransfer.service;

import java.util.List;

import com.bankTransfer.pojo.Logininfo;
import com.bankTransfer.util.LogininfoException;

public interface ILogininfoService {
	/**
	 * 查询所有信息
	 * @return
	 */
	List<Logininfo> queryAll();

	boolean queryByUsernameOrPhoneAndPassword(String login_number, String login_password, int usertype) throws LogininfoException;
}
