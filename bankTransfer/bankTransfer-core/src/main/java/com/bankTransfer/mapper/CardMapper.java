package com.bankTransfer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bankTransfer.pojo.Card;

import io.lettuce.core.dynamic.annotation.Param;
@Mapper
public interface CardMapper {

	/**
	 * 查询用户所有的银行卡
	 * @param user_id 关联的用户ID
	 * @param major_card 主卡或者副卡
	 * @return
	 */
	List<Card> queryCardByUserIdAndMajorCard(@Param("user_id")String user_id,@Param("major_card")String major_card);
}
