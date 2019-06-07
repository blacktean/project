package com.bankTransfer.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankTransfer.pojo.TransferBatch_VO;
import com.bankTransfer.pojo.TransferRegisterAccount_VO;
import com.bankTransfer.pojo.TransferSingle_VO;
import com.bankTransfer.service.ITransferService;
import com.bankTransfer.util.APIUtils;
import com.bankTransfer.util.JsonResult;

@RestController
public class TransferController {
	
	@Autowired 
	private ITransferService transferService;
	
	@PostMapping("singleTransfer")
	public JsonResult singleTransfer(TransferSingle_VO singleVO) {
		System.err.println("进来了");
		JsonResult jsonResult = new JsonResult();
		//收款人卡号
		String receiveCardId = singleVO.getReceivingAccount();
		//收款人姓名
		String receiveName = singleVO.getReceivingName();
		//对收款人的卡号和姓名进行验证
		String obj = APIUtils.checkCard(receiveName, receiveCardId);
		if(obj == null) {//验证不通过
			jsonResult.setSuccess(false);
			jsonResult.setMsg("账号或用户名不存在!");
		}else {//验证通过
			//汇款人账户余额减掉汇款金额
			//汇款金额
			BigDecimal transferAmount = singleVO.getTransferAmount();
			//扣款账号
			String paymentAccount = singleVO.getPaymentAccount();
//			//余额
//			BigDecimal balance = singleVO.getBalance();
//			BigDecimal currentAmount = balance.subtract(transferAmount);
			transferService.subtractBalance(transferAmount,paymentAccount);
			//判断收款账户是否为常用联系账户,不是则添加
			if(!transferService.judgeContact(receiveCardId)){
				//将收款人添加进汇款人的常用联系人表中(先判断该收款人是否已存在)
				transferService.addContact(receiveName,receiveCardId);
			}			
			//往转账交易记录表中插入一条记录
			transferService.insertRecord(singleVO);
		}		
		
		return jsonResult;
	}
	
	@PostMapping("registerAccountTransfer")
	public JsonResult registerAccountTransfer(TransferRegisterAccount_VO transferRgister_VO) {
		JsonResult jsonResult = new JsonResult();
		
		return jsonResult;
	}
	
	
	@PostMapping("batchTranfer")
	public JsonResult batchTransfer(TransferBatch_VO batch_VO) {
		JsonResult jsonResult = new JsonResult();
		
		
		
		return jsonResult;
	}
	
//	@GetMapping("aaa")
//	public String test() {
//		DynamicScheduleTaskSecond timing = new DynamicScheduleTaskSecond();
//		return "asdasd";
//	}
}
