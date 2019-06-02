package com.bankTransfer.service;

import java.util.List;

import com.bankTransfer.pojo.Logininfo;



public interface ILogininfoService {
	/**
	 * 查询所有信息
	 * @return
	 */
	List<Logininfo> queryAll();
}
