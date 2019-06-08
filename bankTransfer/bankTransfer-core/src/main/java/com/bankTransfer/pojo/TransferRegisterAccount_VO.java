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
public class TransferRegisterAccount_VO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**付款人id*/
	private Integer paymentId;
	/**转出卡号*/
	private String paymentAccount;
	/**转入卡号*/
	private String receivingAccount;
	/**真实姓名*/
	private String realName;
	/**证件类型*/
	private String documentType;
	/**证件号*/
	private String documentNum;
	/**余额:默认转全部余额*/
	private BigDecimal balance;
	/**到账时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date paymentDate;
	/**转账结果*/
	private String result;
	/**转账手续费*/
	private BigDecimal serviceCharge = new BigDecimal(0);

}
