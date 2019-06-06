package com.bankTransfer.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransferSingle_VO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**付款人id*/
	private Integer paymentId;
	/**付款人姓名*/
	private String paymentName;
	/**付款卡号*/
	private String paymentAccount;
	/**收款卡号*/
	private String receivingAccount;
	/**余额*/
	private BigDecimal balance;
	/**收款人姓名*/
	private String receivingName;
	/**到账时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date paymentDate;
	/**所属银行*/
	private String bankName;
	/**转账金额*/
	private BigDecimal transferAmount;
	/**证件类型*/
	private String documentType;
	/**证件号*/
	private String documentNum;
	/**手机号码*/
	private String phone;
	/**转账结果*/
	private String result;
	/**转账手续费*/
	private BigDecimal serviceCharge = new BigDecimal(0);

}
