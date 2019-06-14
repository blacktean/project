package com.bankTransfer.pojo;

import java.io.Serializable;
import java.util.Date;

import com.github.pagehelper.PageInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
  * 前台条件查询
 * @author zsy
 *
 */
@Setter
@Getter
@ToString
public class TransactionCondition  implements Serializable {
	private static final long serialVersionUID = 1L;
	/** */
	private Integer time = 1;
	/**资金流向 */
	private String money_flow = "全部";
	/* 用于精确当前用户 */
	private int t_id;
	private Date startTime;
}
