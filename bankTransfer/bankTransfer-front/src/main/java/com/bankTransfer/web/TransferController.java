package com.bankTransfer.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankTransfer.pojo.JsonRate;
import com.bankTransfer.pojo.TransferBatch_VO;
import com.bankTransfer.pojo.TransferCrossBorder_VO;
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
	

	
	
	@PostMapping("batchTranfer")
	public JsonResult batchTransfer(TransferBatch_VO batch_VO) {
		JsonResult jsonResult = new JsonResult();
		/*收款人信息都在对象.users里,对一个个遍历进行逻辑判断(姓名和卡号,从数据库查询),如果信息正确就插入表执行转账操作,
		如果失败就也插入一条记录到表中,状态为失败*/
		return jsonResult;
	}
	
	
	@PostMapping("crossBorderTransfer")
	public JsonResult crossBorderTransfer(TransferCrossBorder_VO crossBorder_VO) {
		JsonResult jsonResult = new JsonResult();
		//验证和姓名是否正确
		String rs = APIUtils.checkCard( crossBorder_VO.getReciverName(),crossBorder_VO.getReciverCardNumber(),crossBorder_VO.getDocumentNumber());
		if(rs == null) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg("信息不正确,转账失败!!");
		}else {
		//正确直接添加信息到数据库,状态为银行已受理
			//计算手续费
			Float f =  null;
			if("乔治银行".equals(crossBorder_VO.getAffiliatedBank())) {
				f = (float) (crossBorder_VO.getTransferAmount()*0.05);
			}else {
				f = (float) (crossBorder_VO.getTransferAmount()*0.01);
			}
			
			if(f < 50) {
				f = 50f;
			}else if(f > 260){
				f = 260f;
			}
			
		//调用api计算汇率,然后再运算
				JsonRate rate = APIUtils.getRate(crossBorder_VO.getTransferAmount(), crossBorder_VO.getPayCurrency(), crossBorder_VO.getReciverCurrency());
				System.err.println(rate);
				//转出账号减余额,转入账号加余额
				transferService.subtractBalance(crossBorder_VO.getBalance().subtract(rate.getCamount()).subtract(new BigDecimal(f).negate()),crossBorder_VO.getPayCardNum());
				transferService.subtractBalance(crossBorder_VO.getBalance(),crossBorder_VO.getReciverCardNumber());
				//存日志(转入和转出)
				TransferSingle_VO singleVO = new TransferSingle_VO();
				singleVO.setPaymentAccount(crossBorder_VO.getPayCardNum());
				singleVO.setReceivingAccount(crossBorder_VO.getReciverCardNumber());
				singleVO.setTransferAmount(new BigDecimal(crossBorder_VO.getTransferAmount()));
				singleVO.setReceivingName(UserContext.getCurrent().getUsername());
				
			
				singleVO.setServiceCharge(new BigDecimal(f));
				transferService.insertRecord(singleVO);
		}		
				//错误返回信息不正确
		return jsonResult;
	}
	
//	@GetMapping("aaa")
//	public String test() {
//		DynamicScheduleTaskSecond timing = new DynamicScheduleTaskSecond();
//		return "asdasd";
//	}
}
