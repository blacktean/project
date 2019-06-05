package com.bankTransfer.web;

import org.springframework.stereotype.Controller;

import com.bankTransfer.pojo.TransferSingle_VO;
import com.bankTransfer.util.APIUtils;

@Controller
public class SingleTransferController {

	public String singleTransfer(TransferSingle_VO singleVO){
		//收款人卡号
		String receiveCardId = singleVO.getReceivingAccount();
		//收款人姓名
		String receiveName = singleVO.getReceivingName();
		//对收款人的卡号和姓名进行验证
		String obj = APIUtils.checkCard(receiveName, receiveCardId);
		if(obj == null) {//验证不通过
			
		}else {//验证通过
			
		}
		return null;
	}
}
