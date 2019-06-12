package com.bankTransfer.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 后台开户审核条件类
 * @author zsy
 *
 */
@Data
public class CardCondition implements Serializable {

	private static final long serialVersionUID = 1L;
	/**银行卡号*/
	private String id_card;
	/**用户真实姓名*/
	private String real_name;
	/**证件号码*/
	private String card_number;
	/**开始时间 */
	private Date startTime;
	/**结束时间 */
	private Date endTime;
	/**银行卡状态*/
	private String card_state;
}
