package com.bankTransfer.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
=======
import com.bankTransfer.pojo.TransferBatch_VO;
>>>>>>> branch 'master' of https://github.com/blacktean/project.git
import com.bankTransfer.pojo.TransferRegisterAccount_VO;
import com.bankTransfer.pojo.TransferSingle_VO;
import com.bankTransfer.service.ITransferService;
import com.bankTransfer.util.APIUtils;
import com.bankTransfer.util.JsonResult;
import com.bankTransfer.util.UserContext;

@RestController
public class TransferController {
	
	@Autowired 
	private ITransferService transferService;
	
	@PostMapping("singleTransfer")
	public JsonResult singleTransfer(TransferSingle_VO singleVO) {
		JsonResult jsonResult = new JsonResult();
		//收款人卡号
		String receiveCardId = singleVO.getReceivingAccount();
		//收款人姓名
		String receiveName = singleVO.getReceivingName();
		//扣款账号
		String paymentAccount = singleVO.getPaymentAccount();
		//对收款人的卡号和姓名进行验证
		String obj = APIUtils.checkCard(receiveName, receiveCardId);
		if(obj == null) {//验证不通过
			jsonResult.setSuccess(false);
			jsonResult.setMsg("账号或用户名不存在!");
		}else {//验证通过
			//判断转账金额是否超过转账限额
			BigDecimal transferMoney = singleVO.getTransferAmount();
			//获取该用户的转账最大限额
			BigDecimal maxPrice = transferService.getMaxPrice(paymentAccount);
			if(transferMoney.compareTo(maxPrice) == 1){
				jsonResult.setSuccess(false);
				jsonResult.setMsg("转账金额超过限额!!");
			}else {
				//汇款人账户余额减掉汇款金额
				//汇款金额
				BigDecimal transferAmount = singleVO.getTransferAmount();
				transferService.subtractBalance(transferAmount.negate(),paymentAccount);
				//判断收款账户是否为常用联系账户,不是则添加
				if(!transferService.judgeContact(receiveCardId)){
					//将收款人添加进汇款人的常用联系人表中(先判断该收款人是否已存在)
					transferService.addContact(receiveName,receiveCardId);
				}			
				//往转账交易记录表中插入一条记录
				transferService.insertRecord(singleVO);
			}			
		}				
		return jsonResult;
	}
	
	@PostMapping("registerAccountTransfer")
	public JsonResult registerAccountTransfer(TransferRegisterAccount_VO transferRgister_VO) {
		JsonResult jsonResult = new JsonResult();
		//获取用户输入的身份证号
		String card = transferRgister_VO.getDocumentNum();
		//获取用户输入的姓名
		String name = transferRgister_VO.getRealName();
		if(transferService.judgeDocumentNum(card,name)){//身份证验证成功
			//转出账号减余额,转入账号加余额
			transferService.subtractBalance(transferRgister_VO.getBalance().negate(),transferRgister_VO.getPaymentAccount());
			transferService.subtractBalance(transferRgister_VO.getBalance(),transferRgister_VO.getReceivingAccount());
			//存日志(转入和转出)
			TransferSingle_VO singleVO = new TransferSingle_VO();
			singleVO.setPaymentAccount(transferRgister_VO.getPaymentAccount());
			singleVO.setReceivingAccount(transferRgister_VO.getReceivingAccount());
			singleVO.setTransferAmount(transferRgister_VO.getBalance());
			singleVO.setReceivingName(UserContext.getCurrent().getUsername());
			transferService.insertRecord(singleVO);
		}else{
			jsonResult.setSuccess(false);
			jsonResult.setMsg("身份信息输入有误!");
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
		System.err.println(batch_VO);
		
		
		return jsonResult;
	}
	
//	@GetMapping("aaa")
//	public String test() {
//		DynamicScheduleTaskSecond timing = new DynamicScheduleTaskSecond();
//		return "asdasd";
//	}
}
