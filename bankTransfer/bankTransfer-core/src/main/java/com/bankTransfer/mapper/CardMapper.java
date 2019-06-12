package com.bankTransfer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bankTransfer.pojo.Card;

import io.lettuce.core.dynamic.annotation.Param;
@Mapper
@Repository
public interface CardMapper {
	
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
	void addCard(@Param("id_card")String id_card,@Param("start_place")String start_place,@Param("currency")String currency,@Param("password")String password,@Param("user_id")String user_id);
	/**
	 * 通过id_card查到对应的银行卡卡号的card
	 * @param user_id
	 * @return 
	 */
	Card queryCardByIdCard(String id_card);
	/**
	 * 通过登录id查询当前资金归集用户名
	 * @param id
	 * @return
	 */
	String queryUserName(Integer id);
	
	/**
	 * 查询用户是否有银行卡
	 * @param user_id 关联的用户ID
	 * @param major_card 主卡或者副卡
	 * @return
	 */
	int queryCard(@Param("user_id")String user_id,@Param("major_card")String major_card);
	
	/**
	 * 查询用户所有的银行卡
	 * @param user_id 关联的用户ID
	 * @param major_card 主卡或者副卡
	 * @return
	 */
	List<Card> queryCardByUserIdAndMajorCard(@Param("user_id")String user_id,@Param("major_card")String major_card);
	/**
	 * 验证是否存在银行卡
	 * @param id_card
	 * @return
	 */
	int getCountByIdCard(@Param("id_card")String id_card);
	
	/**
	 * 验证交易密码 
	 * @param user_id
	 * @param pailPWD
	 * @return
	 */
	int checkPailPWD(@Param("user_id")String user_id,@Param("pailPWD")String pailPWD);
}
