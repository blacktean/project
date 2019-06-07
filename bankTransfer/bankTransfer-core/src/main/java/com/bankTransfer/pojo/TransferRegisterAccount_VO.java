package com.bankTransfer.pojo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransferRegisterAccount_VO implements Serializable {

	private static final long serialVersionUID = 1L;
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

}
