package com.bankTransfer.service;

import java.util.List;

import com.bankTransfer.pojo.Account_openingCondition;
import com.bankTransfer.pojo.Ao_accout_opening;
import com.bankTransfer.pojo.C_collection;
import com.bankTransfer.pojo.CollectionCondition;
import com.bankTransfer.pojo.T_transger;
import com.bankTransfer.pojo.TransgerCondition;

public interface ITransgerService {
	
	/**
	 * BLACKTEA
	    *   带条件分页查询转账记录
	 * @param condition
	 * @return
	 */
	List<T_transger> queryAllTransgerByCondition(TransgerCondition condition);
	
	/**
	 *   带条件分页查询资金归集记录
	 * @param condition
	 * @return
	 */
	List<C_collection> queryAllCollectionByCondition(CollectionCondition condition);
	
	/**
	   *    带条件分页查询开户记录
	 * @param condition
	 * @return
	 */
	List<Ao_accout_opening> queryAllAccout_openingByCondition(Account_openingCondition condition);
	
}
