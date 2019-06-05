package com.bankTransfer.service.impl;




import java.util.Date;

import org.springframework.stereotype.Service;

import com.bankTransfer.pojo.VerifyCodeVo;
import com.bankTransfer.service.IVerifyCodeService;
import com.bankTransfer.util.DateUtil;
import com.bankTransfer.util.SystemConst;
import com.bankTransfer.util.UserContext;
import com.bankTransfer.util.VerifyCodeUtil;

@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {

	@Override
	public void sendVerifyCode(String phoneNumber) {
		VerifyCodeVo vo = UserContext.getCurrentVerifyCodeVo();
		if(vo==null||DateUtil.secondBetwen(vo.getLastTime(), new Date())>30) {
			VerifyCodeUtil.sendMsg(phoneNumber);
		}else {
			throw new RuntimeException("操作过于频繁");
		}

	}
	@Override
	public boolean checkVerifyCode(String phoneNumber, String verifyCode) {
		VerifyCodeVo vc = UserContext.getCurrentVerifyCodeVo();
		if(vc != null 
				&& vc.getPhoneNumber().equals(phoneNumber)
				&& vc.getVerifyCode().equalsIgnoreCase(verifyCode)
				&& DateUtil.secondBetwen(new Date(), vc.getLastTime()) < SystemConst.VERIFYCODE_VALIDATE_TIME){ //不通过
			return true;
		}
		return false;
	}


}
