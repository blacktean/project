package com.bankTransfer.service;

import java.util.List;

import com.bankTransfer.pojo.Card;

import io.lettuce.core.dynamic.annotation.Param;

public interface ICardService {
	
	/**
	 * 通过登录user_id查询当前用户的银行卡
	 * @param user_id
	 * @return 
	 */
	List<Card> queryCardByUserId(int user_id);
	/**
	  * 增加银行卡
	 * @param user_id
	 * @return 
	 */
	public void addCard(String id_card,String start_place,String currency,String password,String user_id);
	/**
	 * 通过id_card查到对应的银行卡卡号的card
	 * @param user_id
	 * @return 
	 */
	Card queryCardByIdCard(String id_card);
	/**
	 * 通过登录id查询当前用户名
	 * @param id
	 * @return
	 */
	String queryUserName(Integer id);
	
	/**
	 * 查询用户所有的银行卡
	 * @param user_id 关联的用户ID
	 * @param major_card 主卡或者副卡
	 * @return
	 */
	List<Card> queryCardByUserIdAndMajorCard(String user_id,String major_card);
	
	/**
	 * 验证是否存在银行卡
	 * @param id_card
	 * @return
	 */
	boolean getCountByIdCard(String id_card);
	
	/**
	 * 验证交易密码 
	 * @param user_id
	 * @param pailPWD
	 * @return
	 */
	boolean checkPailPWD(String user_id,String pailPWD);
}
