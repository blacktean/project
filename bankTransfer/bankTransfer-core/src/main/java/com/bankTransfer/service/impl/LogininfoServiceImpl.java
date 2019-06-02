package com.bankTransfer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.LogininfoMapper;
import com.bankTransfer.pojo.Logininfo;
import com.bankTransfer.service.ILogininfoService;

@Service
public class LogininfoServiceImpl implements ILogininfoService {

	
	@Autowired
	private LogininfoMapper logininfoMapper;
	
	public List<Logininfo> queryAll() {
		return logininfoMapper.queryAll();
	}

}
