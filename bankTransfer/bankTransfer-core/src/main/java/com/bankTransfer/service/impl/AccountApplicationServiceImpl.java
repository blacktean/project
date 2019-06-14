package com.bankTransfer.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.AccountApplicationMapper;
import com.bankTransfer.pojo.User;
import com.bankTransfer.service.IAccountApplicationService;
import com.bankTransfer.util.APIUtils;
import com.bankTransfer.util.UserContext;

@Service
public class AccountApplicationServiceImpl implements IAccountApplicationService {
	
	@Autowired
	private AccountApplicationMapper accountApplicationMapper;
	
	@Override
	public void addUser(User user) {
		//获取用户的证件号
		String personId = user.getCard_number();
		//获取用户的真实姓名
		String realName = user.getReal_name();
		boolean rs = APIUtils.checkNumberAndName(personId, realName);
		if(!rs){
			throw new RuntimeException("证件信息输入有误,请验证后重试!");
		}
		user.setUser_id(UserContext.getCurrent().getId());
		user.setUser_name(UserContext.getCurrent().getUsername());
		user.setReg_type("网上开户");
		user.setGrade(1);
		user.setState(1);
		user.setCreate_bank("41");
		user.setApplyTime(new Date());
		accountApplicationMapper.addUser(user);
	}

	@Override
	public boolean queryCardById(Integer id) {
		int rows = accountApplicationMapper.queryCardById(id);
		if(rows>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean queryUserById(Integer id) {
		int rows  = accountApplicationMapper.queryUserById(id);
		if(rows>0) {
			return true;
		}
		return false;
	}

}
