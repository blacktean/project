package com.bankTransfer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.CardType;
import com.bankTransfer.pojo.Currency;
import com.bankTransfer.pojo.Document;
import com.bankTransfer.pojo.Logininfo;

import io.lettuce.core.dynamic.annotation.Param;
@Mapper
public interface BaseMapper {

	/**
	 * 查询所有银行卡种类
	 * @return
	 */
	List<CardType> queryCardType();
	/**
	 * 查询所有证件类型
	 * @return
	 */
	List<Document> queryDocument();
	/**
	 * 查询所有币种
	 * @return
	 */
	List<Currency> queryCurrency();
	
	
	/**
	 * 查询用户的所有银行卡
	 * @param id
	 * @return
	 */
	List<Card> queryCardByUserId(@Param("id")Integer id);
	/**
	 *	通過卡號查詢銀行卡
	 * @param id_card
	 * @return
	 */
	Card queryCardByCardNumber(@Param("id_card")String id_card);
	/**
	 * 验证密码是否正确
	 * @param id_card
	 * @param password
	 */
	Card queryCardByIdCardAndPassword(@Param("id_card")String id_card,@Param("password")String password);
}
