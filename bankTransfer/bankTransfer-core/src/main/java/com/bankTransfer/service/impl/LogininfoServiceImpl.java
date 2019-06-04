package com.bankTransfer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.LogininfoMapper;
import com.bankTransfer.pojo.Logininfo;
import com.bankTransfer.service.ILogininfoService;
import com.bankTransfer.util.LogininfoException;
import com.bankTransfer.util.MD5;
import com.bankTransfer.util.UserContext;
@Service
public class LogininfoServiceImpl implements ILogininfoService {

	
	@Autowired
	private LogininfoMapper logininfoMapper;
	
	public List<Logininfo> queryAll() {
		return logininfoMapper.queryAll();
	}

	@Override
	public boolean queryByUsernameOrPhoneAndPassword(String login_number, String login_password, int usertype) throws LogininfoException {
		Logininfo logininfo =logininfoMapper.queryByUsernameOrPhoneAndPassword(login_number, MD5.encode(login_password));
		if(logininfo!=null) {
			if(logininfo.getState()==Logininfo.STATE_LOCK) {
				throw new LogininfoException("账户已锁定");
			}
			if(logininfo.getState()==Logininfo.STATE_DELETE) {
				throw new LogininfoException("账户已注销");
			}
			UserContext.setCurrent(logininfo);
			
		}
		return logininfo!=null;
	}
}
