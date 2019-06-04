package com.bankTransfer.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.bankTransfer.pojo.CashSweep;
@Mapper
public interface CashSweepMapper {
	/**
	 * 新建资金归集
	 * @param cashSweep
	 */
	void addCollection(CashSweep cashSweep);
}
