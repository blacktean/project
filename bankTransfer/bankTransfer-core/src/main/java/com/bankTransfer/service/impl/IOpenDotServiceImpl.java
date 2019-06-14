package com.bankTransfer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.OpenDotMapper;
import com.bankTransfer.pojo.OpenDot;
import com.bankTransfer.service.IOpenDotService;

@Service
public class IOpenDotServiceImpl implements IOpenDotService{
	@Autowired
	private OpenDotMapper openDotMapper;

	@Override
	public OpenDot queryOpenDotByDot_number(int dot_number) {
		return openDotMapper.queryOpenDotByDot_number(dot_number);
	}
	
	
}
