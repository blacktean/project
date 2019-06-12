package com.bankTransfer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bankTransfer.pojo.C_collection;
import com.bankTransfer.pojo.CashSweep;

import io.lettuce.core.dynamic.annotation.Param;
@Mapper
public interface CashSweepMapper {
	/**
	 * 新建资金归集
	 * @param cashSweep
	 */
	void addCollection(CashSweep cashSweep);
	
	/**
	 * 查询卡余额
	 * @param id_card
	 * @return
	 */
	Double queryCardBalance(@Param("id_card")String id_card);
	
	/**
	 * 查询是否有资金归集
	 * @param collection_accout
	 * @return
	 */
	int queryCollection(@Param("collection_accout")String collection_accout);
	
	/**
	 * 通过主卡查询资金列表
	 * @param collection_accout
	 * @return
	 */
	List<CashSweep> queryCollectionList(@Param("collection_accout")String collection_accout);
	
	/**
	 * 通过主卡查询第一条资金列表记录
	 * @param collection_accout
	 * @return
	 */
	CashSweep queryOneCollection(@Param("collection_accout")String collection_accout);
	
	/**
	 * 终止资金归集服务
	 * @param collection_accout
	 */
	void shutDownService(@Param("collection_accout")String collection_accout);
	
	/**
	 * 修改资金归集
	 * @param cashSweep
	 */
	void saveUpdateCollection(CashSweep cashSweep);
	
	/**
	 * 往资金归集记录表添加记录
	 * @param c_collection
	 */
	void insert_C_collection(C_collection c_collection);
}
