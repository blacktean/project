package com.bankTransfer.service;

import java.util.List;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.Card_VO;

public interface ISetupService {

	boolean setup(String password, Integer id);

	boolean checkPassword(String password, Integer id);

	List<Card> queryCardIdByUserId(Integer user_id);

	boolean update(Card_VO card_vo);

}
