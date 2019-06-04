package com.bankTransfer.service;

import java.util.List;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.CardType;
import com.bankTransfer.pojo.Currency;
import com.bankTransfer.pojo.Document;

public interface IBaseService {

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
	List<Card> queryCardByUserId(Integer id);
	
	/**
	 * 根据银行卡号查询银行卡,用来显示余额
	 */
	Card queryCardByCardNumber(String number);
}
