package com.bankTransfer.service;

import java.util.List;

import com.bankTransfer.pojo.Card;

public interface ICardService {
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
}
