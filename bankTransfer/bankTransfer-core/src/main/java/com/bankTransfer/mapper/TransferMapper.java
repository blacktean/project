package com.bankTransfer.mapper;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.bankTransfer.pojo.TransferSingle_VO;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface TransferMapper {
	
	void subtractBalance(@Param("transferAmount")BigDecimal transferAmount, @Param("paymentAccount")String paymentAccount);

	boolean judgeContact(@Param("receiveCardId")String receiveCardId);

	void addContact(@Param("receiveName")String receiveName, @Param("receiveCardId")String receiveCardId);

	void insertRecord(TransferSingle_VO singleVO);

}
