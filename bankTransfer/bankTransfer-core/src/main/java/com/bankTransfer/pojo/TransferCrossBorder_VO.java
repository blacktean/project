package com.bankTransfer.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
/**
 * 跨境汇款类
 * 
 * @author Administrator
 *
 */
public class TransferCrossBorder_VO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 收款姓名 */
	private String reciverName;

	/** 收款国家 */
	private String reciverCountry;

	/** 收款卡号 */
	private String reciverCardNumber;

	/** 收款人常驻国家 */
	private String reciverAlwayCountry;

	/** 收款人地址 */
	private String reciverPlace;

	/** 转账金额 */
	private Float transferAmount;

	/** 收款方标识 */
	private String userOfFund;

	/** 证件号码 */
	private String documentNumber;

	/** 所属银行 */
	private String affiliatedBank;

	/** 收款币种 */
	private String reciverCurrency;

	/** 汇款人卡号 */
	private String payCardNum;

	/** 证件类型 */
	private String document_type;

	/** 附言 */
	private String msg;

	/** 汇款人币种 */
	private String payCurrency = "CNY";
	/** 余额*/
	private BigDecimal balance;
}
