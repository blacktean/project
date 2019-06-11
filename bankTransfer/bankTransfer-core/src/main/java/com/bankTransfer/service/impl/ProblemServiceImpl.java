package com.bankTransfer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.ProblemMapper;
import com.bankTransfer.pojo.Problem;
import com.bankTransfer.service.IProblemService;

@Service
public class ProblemServiceImpl implements IProblemService{
	@Autowired
	ProblemMapper problemMapper;

	@Override
	public List<Problem> queryAllProblem() {
		// TODO Auto-generated method stub
		return problemMapper.queryAllProblem();
	}
}
