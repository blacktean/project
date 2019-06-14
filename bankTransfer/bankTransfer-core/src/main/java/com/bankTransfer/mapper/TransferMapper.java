package com.bankTransfer.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bankTransfer.pojo.TransactionCondition;
import com.bankTransfer.pojo.TransferSingle_VO;
import com.bankTransfer.pojo.User;
import com.bankTransfer.pojo.UserCondition;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface TransferMapper {
	
	void subtractBalance(@Param("transferAmount")BigDecimal transferAmount, @Param("paymentAccount")String paymentAccount);

	int judgeContact(@Param("receiveCardId")String receiveCardId);

	void addContact(@Param("user_id")Integer user_id,@Param("receiveName")String receiveName, @Param("receiveCardId")String receiveCardId);

	void insertRecord(TransferSingle_VO singleVO);

	Double getMaxPrice(@Param("paymentAccount")String paymentAccount);

	int judgeDocumentNum(@Param("card")String card, @Param("name")String name, @Param("id")Integer id);

	int judgeUserMsg(@Param("name")String name, @Param("cardNum")String cardNum);

	Double getBalance(@Param("payCardNum")String payCardNum);

	List<TransferSingle_VO> queryTranferInfoByTransferMode();
	
	void changeTransferInfo(@Param("id") Integer id);
	/**
	   * 带条件分页查询所有交易记录
	 * @param condition
	 * @return
	 */
	List<TransferSingle_VO> queryTranferInfoAllByT_id(TransactionCondition condition);
	
}
