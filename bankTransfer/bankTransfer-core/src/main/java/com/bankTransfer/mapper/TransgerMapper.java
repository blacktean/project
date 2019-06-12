package com.bankTransfer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bankTransfer.pojo.Account_openingCondition;
import com.bankTransfer.pojo.Ao_accout_opening;
import com.bankTransfer.pojo.C_collection;
import com.bankTransfer.pojo.CollectionCondition;
import com.bankTransfer.pojo.T_transger;
import com.bankTransfer.pojo.TransgerCondition;
/**
 * 转账记录
 * @author BLACKTEA
 *
 */
@Repository
public interface TransgerMapper {
	
	/**
	 * 带条件分页查询所有转账记录
	 * @param condition
	 * @return
	 */
	List<T_transger> queryAllTransgerByCondition(TransgerCondition condition);
	
	/**
	 *   带条件分页查询所有资金归集记录
	 * @param condition
	 * @return
	 */
	List<C_collection> queryAllCollctionByCondition(CollectionCondition condition);
	
	
	/**
	  *     带条件分页查询所有开户记录
	 * @param condition
	 * @return
	 */
	List<Ao_accout_opening> queryAllAccout_openingByCondition(Account_openingCondition condition);
	
}
