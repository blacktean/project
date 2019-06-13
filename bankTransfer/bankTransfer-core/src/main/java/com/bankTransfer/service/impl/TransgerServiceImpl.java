package com.bankTransfer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.TransgerMapper;
import com.bankTransfer.pojo.Account_openingCondition;
import com.bankTransfer.pojo.Ao_accout_opening;
import com.bankTransfer.pojo.C_collection;
import com.bankTransfer.pojo.CollectionCondition;
import com.bankTransfer.pojo.T_transger;
import com.bankTransfer.pojo.TransgerCondition;
import com.bankTransfer.service.ITransgerService;

@Service
public class TransgerServiceImpl implements ITransgerService {
	
	@Autowired
	private TransgerMapper transgerMapper;
	/**
	 * 转账记录
	 */
	public List<T_transger> queryAllTransgerByCondition(TransgerCondition condition) {
		/*
		 * List<T_transger> transgers =
		 * transgerMapper.queryAllTransgerByCondition(condition); for (T_transger
		 * transger : transgers) {
		 * transger.setT_handfee(transger.getT_handfee().setScale(2)); }
		 */
		return transgerMapper.queryAllTransgerByCondition(condition);
	}

	/**
	 * 资金归集记录
	 */
	public List<C_collection> queryAllCollectionByCondition(CollectionCondition condition) {
		return transgerMapper.queryAllCollctionByCondition(condition);
	}

	/**
	    *  开户记录
	 */
	public List<Ao_accout_opening> queryAllAccout_openingByCondition(Account_openingCondition condition) {
		return transgerMapper.queryAllAccout_openingByCondition(condition);
	}
}
