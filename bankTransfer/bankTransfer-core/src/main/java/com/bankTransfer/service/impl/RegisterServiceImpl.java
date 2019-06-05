package com.bankTransfer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransfer.mapper.RegisterMapper;
import com.bankTransfer.service.IRegisterService;
import com.bankTransfer.util.MD5;

/**
 * 注册
 * @author 康志龙
 *@version  创建时间   2019年6月5日  下午3:22:41
 */
@Service
public class RegisterServiceImpl implements IRegisterService {
	@Autowired
	RegisterMapper registerMapper;
	
	/**
	 * 检验用户名是否存在
	 */
	@Override
	public boolean checkUsername(String username,int usertype) {
		// TODO Auto-generated method stub
		return registerMapper.checkUsername(username,usertype)==null;
	}

	@Override
	/**
	 * 注册
	 */
	public boolean register(String username, String password, String telphone,int state,int usertype) {
		// TODO Auto-generated method stub
		return registerMapper.register( username, MD5.encode(password), telphone,state,usertype)==1;
	}
}
