package com.bankTransfer.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankTransfer.mapper.TransferMapper;
import com.bankTransfer.pojo.TransactionState;
import com.bankTransfer.pojo.TransferSingle_VO;
import com.bankTransfer.service.ITransferService;
import com.bankTransfer.util.UserContext;

/*
 使用@Transactional注解到会进行增删改的方法上面,出现异常会回滚
 */

@Service
public class TransferServiceImpl implements ITransferService {

	@Autowired
	private TransferMapper transferMapper;

	@Override
	@Transactional
	public void subtractBalance(BigDecimal transferAmount, String paymentAccount) {
		transferMapper.subtractBalance(transferAmount, paymentAccount);		
	}

	@Override
	public boolean judgeContact(String receiveCardId) {
		int rs = transferMapper.judgeContact(receiveCardId);
		if(rs == 0) {
			return false;
		}
		return true;
	}

	@Override
	public void addContact(String receiveName, String receiveCardId) {		
		transferMapper.addContact(UserContext.getCurrent().getId(),receiveName, receiveCardId);
	}

	@Override
	public void insertRecord(TransferSingle_VO singleVO) {
		singleVO.setPaymentId(UserContext.getCurrent().getId());
		singleVO.setPaymentName(UserContext.getCurrent().getUsername());
		singleVO.setPaymentDate(new Date());
		singleVO.setResult(TransactionState.TRANSFER_STATE_SUCCESS);
		System.out.println("=========singleVO:"+singleVO);
		transferMapper.insertRecord(singleVO);
	}

	
}
