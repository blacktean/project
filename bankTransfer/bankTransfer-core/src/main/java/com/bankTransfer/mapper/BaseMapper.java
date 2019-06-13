package com.bankTransfer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.CardType;
import com.bankTransfer.pojo.Contacts;
import com.bankTransfer.pojo.Currency;
import com.bankTransfer.pojo.Document;
import com.bankTransfer.pojo.User;

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
	/**
	 * 根据id查询user
	 * @param id
	 * @return
	 */
	User queryUserById(@Param("id")Integer id);
	/**
	 * 查询用户的联系人
	 * @param id
	 * @return
	 */
	Contacts queryContacts(@Param("id")Integer id);
}
