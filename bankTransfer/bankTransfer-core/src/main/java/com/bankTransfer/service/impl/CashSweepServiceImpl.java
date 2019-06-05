package com.bankTransfer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.CashSweepMapper;
import com.bankTransfer.pojo.CashSweep;
import com.bankTransfer.service.ICashSweepService;
@Service
public class CashSweepServiceImpl implements ICashSweepService {
	
	@Autowired
	private CashSweepMapper cashSweepMapper;
	
	@Override
	public void addCollection(CashSweep cashSweep) {
		cashSweepMapper.addCollection(cashSweep);
	}

}
