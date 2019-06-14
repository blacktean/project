package com.bankTransfer.service;

import java.math.BigDecimal;
import java.util.List;

import com.bankTransfer.pojo.TransactionCondition;
import com.bankTransfer.pojo.TransferSingle_VO;
import com.bankTransfer.pojo.UserVo;

public interface ITransferService {
	/**减少指定账户上的指定金额*/
	void subtractBalance(BigDecimal transferAmount,String paymentAccount);
	/**判断指定账户是否为当前用户的常用联系账户*/
	boolean judgeContact(String receiveCardId);
	/**给当前用户添加一个常用联系人*/
	void addContact(String receiveName, String receiveCardId);
	/**插入一条转账交易成功记录*/
	void insertRecord(TransferSingle_VO singleVO);
	/**根据卡号查询该用户的转账限额*/
	BigDecimal getMaxPrice(String paymentAccount);
	/**验证身份证号与姓名是否存在于数据库并匹配*/
	boolean judgeDocumentNum(String card, String name);
	/**判断所有收款人信息是否正确并转账,添加日志信息*/
	void judgeReceiving(List<UserVo> users,String payCardNum,BigDecimal allMoney);
	/**处理定时任务*/
	void handleTransfer();
	/**
	   * 带条件分页查询所有交易记录
	 * @param condition
	 * @return
	 */
	List<TransferSingle_VO> queryTranferInfoAllByT_id(TransactionCondition condition);
}
