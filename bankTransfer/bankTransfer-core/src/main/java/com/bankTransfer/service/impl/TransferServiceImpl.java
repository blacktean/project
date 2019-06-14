package com.bankTransfer.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankTransfer.mapper.TransferMapper;
import com.bankTransfer.pojo.JsonCountry;
import com.bankTransfer.pojo.TransactionCondition;
import com.bankTransfer.pojo.TransactionState;
import com.bankTransfer.pojo.TransferSingle_VO;
import com.bankTransfer.pojo.UserVo;
import com.bankTransfer.service.ITransferService;
import com.bankTransfer.util.APIUtils;
import com.bankTransfer.util.CalculationRate;
import com.bankTransfer.util.DateCompare;
import com.bankTransfer.util.UserContext;

/*
 使用@Transactional注解到会进行增删改的方法上面,出现异常会回滚
 */

@Service
public class TransferServiceImpl implements ITransferService {

	@Autowired
	private TransferMapper transferMapper;

	@Override
	@Transactional
	public void subtractBalance(BigDecimal transferAmount, String paymentAccount) {
		transferMapper.subtractBalance(transferAmount, paymentAccount);
	}

	@Override
	public boolean judgeContact(String receiveCardId) {
		int rs = transferMapper.judgeContact(receiveCardId);
		if (rs == 0) {
			return false;
		}
		return true;
	}

	@Override
	public void addContact(String receiveName, String receiveCardId) {
		transferMapper.addContact(UserContext.getCurrent().getId(), receiveName, receiveCardId);
	}

	@Override
	public void insertRecord(TransferSingle_VO singleVO) {
		singleVO.setPaymentId(UserContext.getCurrent().getId());
		singleVO.setPaymentName(UserContext.getCurrent().getUsername());
		singleVO.setPaymentDate(new Date());
		transferMapper.insertRecord(singleVO);
	}

	@Override
	public BigDecimal getMaxPrice(String paymentAccount) {
		Double price = transferMapper.getMaxPrice(paymentAccount);
		return new BigDecimal(price);
	}

	@Override
	public boolean judgeDocumentNum(String card, String name) {
		int num = transferMapper.judgeDocumentNum(card, name, UserContext.getCurrent().getId());
		if (num == 1) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public void judgeReceiving(List<UserVo> users, String payCardNum, BigDecimal allMoney) {
		for (UserVo user : users) {
			String name = user.getName();
			String cardNum = user.getCard_number();
			// 验证收款账号和收款人是否存在于数据库并对应
			int rs = transferMapper.judgeUserMsg(name, cardNum);
			if (rs < 1) {
				throw new RuntimeException(name + "收款人信息有误,请核对后重试!");
			}
		}
		// 所有收款人信息验证成功,进行转账操作和日志记录
		TransferSingle_VO singleVO = new TransferSingle_VO();// 日志信息对象
		JsonCountry jsonCountry = APIUtils.getJsonCountry();// 定位
		String city = jsonCountry.getCity();// 获取当前城市
		BigDecimal allRate = new BigDecimal(0);// 记录所有手续费的变量
		// 验证转账金额和手续费是否大于余额
		for (UserVo user : users) {
			BigDecimal transfer = user.getPayMoney();
			String receivingCity = user.getPlace_2();
			// 记录手续费的变量
			BigDecimal rate;
			if (city.equals(receivingCity)) {
				rate = new BigDecimal(1);
				allRate = allRate.add(rate);
			} else {
				rate = CalculationRate.getRate(transfer);
				allRate = allRate.add(rate);
			}
		}
		// 判断转账总金额加上手续费余额是否足够
		Double ba = transferMapper.getBalance(payCardNum);
		BigDecimal balance = new BigDecimal(ba);
		if (allRate.add(allMoney).compareTo(balance) == 1) {
			throw new RuntimeException("余额不足!");
		}
		// 给所有收款账号添加指定金额
		for (UserVo user : users) {
			String cardNum = user.getCard_number();
			BigDecimal transferMoney = user.getPayMoney();
			transferMapper.subtractBalance(transferMoney.negate(), cardNum);
			// 给每个收款账户添加一条收款记录
			singleVO.setTransferAmount(transferMoney);
			singleVO.setPaymentAccount(payCardNum);
			singleVO.setPaymentName(UserContext.getCurrent().getUsername());
			singleVO.setPaymentId(UserContext.getCurrent().getId());
			singleVO.setPaymentDate(new Date());
			singleVO.setReceivingAccount(cardNum);
			singleVO.setReceivingName(user.getName());
			singleVO.setTransfer_type("批量转账");
			singleVO.setTransaction_type("支出");
			singleVO.setTransfer_mode("实时到账");
			singleVO.setMsg(user.getMsg());
			// 手续费
			String receivingCity = user.getPlace_2();
			// 记录手续费的变量
			BigDecimal rate = new BigDecimal(1);
			if (city.equals(receivingCity)) {
				singleVO.setServiceCharge(rate);
			} else {
				rate = CalculationRate.getRate(transferMoney);
				singleVO.setServiceCharge(rate);
			}
			singleVO.setResult(TransactionState.TRANSFER_STATE_SUCCESS);
			System.err.println(singleVO);
			transferMapper.insertRecord(singleVO);
		}
		// 减少付款账号的金额
		transferMapper.subtractBalance(allMoney.add(allRate), payCardNum);
	}

	@Override
	public void handleTransfer() {
		Date now = new Date();
		List<TransferSingle_VO> transfers = transferMapper.queryTranferInfoByTransferMode();

		for (TransferSingle_VO transferSingle_VO : transfers) {
			boolean flag = false;
			if (transferSingle_VO.getTransfer_mode().equals("普通到账")
					&& DateCompare.compareHour(now, transferSingle_VO.getPaymentDate())) {
				flag = true;
			} else if (transferSingle_VO.getTransfer_mode().equals("次日到账")
					&& DateCompare.compareDay(now, transferSingle_VO.getPaymentDate())) {
				flag = true;
			}
			if (flag) {
				this.subtractBalance(transferSingle_VO.getTransferAmount().negate(),
						transferSingle_VO.getReceivingAccount());
				transferMapper.changeTransferInfo(transferSingle_VO.getId());
			}
		}
	}
	
	@Override
	public List<TransferSingle_VO> queryTranferInfoAllByT_id(TransactionCondition condition) {
		return transferMapper.queryTranferInfoAllByT_id(condition);
	}

}
