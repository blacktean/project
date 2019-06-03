package com.bankTransfer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.TransferMapper;
import com.bankTransfer.service.ITransferService;

/*
 使用@Transactional注解到会进行增删改的方法上面,出现异常会回滚
 */

@Service
public class TransferServiceImpl implements ITransferService {

	@Autowired
	private TransferMapper transferMapper;
	
	
	
}
