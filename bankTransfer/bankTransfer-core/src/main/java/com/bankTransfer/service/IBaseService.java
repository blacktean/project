package com.bankTransfer.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.CardType;
import com.bankTransfer.pojo.Contacts;
import com.bankTransfer.pojo.Currency;
import com.bankTransfer.pojo.Document;
import com.bankTransfer.pojo.User;

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
	 * 查询用户所有的银行卡
	 * @param  
	 * @return
	 */
	List<Card> queryCardByUserId(Integer id);
	
	/**
	 *	通過卡號查詢銀行卡
	 * @param id_card
	 * @return
	 */
	Card queryCardByCardNumber(String id_card);
	/**
	 * 查询密码是否正确
	 * @param id_card
	 * @param password
	 */
	void queryCardByIdCardAndPassword(String id_card, String password);
	/**
	 * 根据logininfo的id查询user
	 * @param id
	 * @return
	 */
	User checkUser(Integer id);
	
	/**
	 * 查询用户的联系人
	 * @param id
	 * @return
	 */
	Contacts queryContacts(Integer id);
}
