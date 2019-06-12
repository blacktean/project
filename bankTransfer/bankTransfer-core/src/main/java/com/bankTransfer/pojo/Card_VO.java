package com.bankTransfer.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
@Data
public class Card_VO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**主键*/
	private Integer id;
	/**银行卡号*/
	private String id_card;
	/**开户地点*/
	private String start_place;
	/**币种*/
	private String currency;
	/**余额*/
	private BigDecimal balance;
	/**可用余额*/
	private BigDecimal available_balance;
	/**卡种*/
	private String card_type;
	/**关联用户id*/
	private String c_user_id;
	/**银行卡状态*/
	private String card_state;
	/**主副卡标志*/
	private String major_card;
	/**办卡日期*/
	private Date create_time;
	/**有效期*/
	private String effective_time;
	/**卡片限额*/
	private BigDecimal max_price;
	/**用户真实姓名*/
	private String real_name;
	/**证件号码*/
	private String card_number;
	private Integer u_user_id;
}
