package com.bankTransfer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.CashSweepMapper;
import com.bankTransfer.pojo.C_collection;
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

	@Override
	public Double queryCardBalance(String id_card) {
		return cashSweepMapper.queryCardBalance(id_card);
	}

	@Override
	public boolean queryCollection(String collection_accout) {
		int rows = cashSweepMapper.queryCollection(collection_accout);
		if(rows>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<CashSweep> queryCollectionList(String collection_accout) {
		return cashSweepMapper.queryCollectionList(collection_accout);
	}

	@Override
	public CashSweep queryOneCollection(String collection_accout) {
		return cashSweepMapper.queryOneCollection(collection_accout);
	}

	@Override
	public void shutDownService(String collection_accout) {
		cashSweepMapper.shutDownService(collection_accout);
	}

	@Override
	public void saveUpdateCollection(CashSweep cashSweep) {
		cashSweepMapper.saveUpdateCollection(cashSweep);
	}

	@Override
	public void insert_C_collection(C_collection c_collection) {
		cashSweepMapper.insert_C_collection(c_collection);
	}


}
