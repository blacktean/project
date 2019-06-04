package com.bankTransfer.service;

import java.util.List;

import com.bankTransfer.pojo.Card;

public interface ICardService {

	/**
	 * 查询用户所有的银行卡
	 * @param user_id 关联的用户ID
	 * @param major_card 主卡或者副卡
	 * @return
	 */
	List<Card> queryCardByUserIdAndMajorCard(String user_id,String major_card);
}
