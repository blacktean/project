package com.bankTransfer.service;

import com.bankTransfer.pojo.CashSweep;

public interface ICashSweepService {
	
	/**
	 * 新建资金归集
	 * @param cashSweep
	 */
	void addCollection(CashSweep cashSweep);
}
