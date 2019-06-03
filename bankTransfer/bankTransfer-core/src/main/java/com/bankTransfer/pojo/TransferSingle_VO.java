package com.bankTransfer.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransferSingle_VO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**付款卡号*/
	private String paymentAccount;
	/**收款卡号*/
	private String receivingAccount;
	/**余额*/
	private BigDecimal balance;
	/**收款人姓名*/
	private String receivingName;
	/**到账时间*/
	private Date paymentDate;
	/**所属银行*/
	private String bankName;
	/**转账金额*/
	private BigDecimal transferAmount;
	/**证件类型*/
	private String documentType;
	/**证件号*/
	private String documentNum;

}
