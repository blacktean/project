package com.bankTransfer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.AoMapper;
import com.bankTransfer.pojo.Ao_accout_opening;
import com.bankTransfer.service.IAoService;

@Service
public class AoServiceImp implements IAoService {
	@Autowired
	private AoMapper aoMapper;

	@Override
	public List<Ao_accout_opening> queryAll() {
		// TODO Auto-generated method stub
		return aoMapper.queryAll();
	}
}
