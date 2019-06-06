package com.bankTransfer.pojo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionState implements Serializable {
	/**待处理*/
	public static final String TRANSFER_STATE_ACCEPTED = "1" ;
	/**成功*/
	public static final String TRANSFER_STATE_SUCCESS = "2" ;
	/**失败*/
	public static final String TRANSFER_STATE_FAILD = "3" ;
	/**已取消*/
	public static final String TRANSFER_STATE_CANCEL = "4" ;

	private static final long serialVersionUID = 1L;
	/**主键*/
	private Integer id;
	/**交易状态*/
	private String tradingStatus;
}
