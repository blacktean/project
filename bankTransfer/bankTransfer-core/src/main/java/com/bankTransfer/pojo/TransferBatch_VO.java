package com.bankTransfer.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TransferBatch_VO implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<UserVo> users;
	/**转账总金额*/
	private BigDecimal allMoney;
	/**付款卡号*/
	private String payCardNum;

}
