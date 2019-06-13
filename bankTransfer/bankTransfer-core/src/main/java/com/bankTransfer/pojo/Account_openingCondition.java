package com.bankTransfer.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
    * 开户记录分页查询条件类
 * @author BLACKTEA
 *
 */
@Data
public class Account_openingCondition implements Serializable {

	private static final long serialVersionUID = 1L;

	/**姓名*/
	private String ao_name;
	/**账号*/
	private String ao_accout_number;
	/**开始时间 */
	private Date startTime;
	/**结束时间 */
	private Date endTime;
	/**卡类型*/
	private String ao_cardtype;
	/**开户地址*/
	/* private String ao_location; */
	/**开户状态 0开户失败 1开户成功 */
	private Integer state;
}
