package com.bankTransfer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bankTransfer.pojo.Card;

import io.lettuce.core.dynamic.annotation.Param;
@Mapper
public interface CardMapper {
	
	/**
	 * 通过登录user_id查询当前用户的银行卡
	 * @param user_id
	 * @return 
	 */
	Card queryCardByUser_Id(String user_id);
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
	List<Card> queryCardByUserIdAndMajorCard(@Param("user_id")String user_id,@Param("major_card")String major_card);
	
	/**
	 * 验证是否存在银行卡
	 * @param id_card
	 * @return
	 */
	int getCountByIdCard(@Param("id_card")String id_card);
}
