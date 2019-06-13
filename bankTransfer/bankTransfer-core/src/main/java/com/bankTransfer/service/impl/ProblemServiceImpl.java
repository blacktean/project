package com.bankTransfer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.ProblemMapper;
import com.bankTransfer.pojo.Currency;
import com.bankTransfer.pojo.JsonRate;
import com.bankTransfer.pojo.Problem;
import com.bankTransfer.service.IProblemService;
import com.bankTransfer.util.APIUtils;

@Service
public class ProblemServiceImpl implements IProblemService{
	@Autowired
	ProblemMapper problemMapper;
	
	@Override
	public List<Problem> queryAllProblem() {
		// TODO Auto-generated method stub
		return problemMapper.queryAllProblem();
	}

	@Override
	public List<Currency> queryAllCurrency() {
		// TODO Auto-generated method stub
		return problemMapper.queryAllCurrency();
	}

	@Override
	public JsonRate huilv(String from, String to, Float money) {
		// TODO Auto-generated method stub
		return APIUtils.getRate(money, from, to);
	}
}
