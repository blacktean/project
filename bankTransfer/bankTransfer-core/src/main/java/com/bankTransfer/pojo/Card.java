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
public class Card implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**主键*/
	private Integer id;
	/**银行卡号*/
	private String id_card;
	/**开户地点*/
	private String start_place;
	/**币种*/
	private String curreny;
	/**余额*/
	private BigDecimal balance;
	/**可用余额*/
	private BigDecimal available_balance;
	/**开户网点*/
	private String start_place_id;
	/**银行卡密码(md5加密)*/
	private String password;
	/**银行卡类型*/
	private String card_type;
	/**关联用户id*/
	private String user_id;
	/**银行卡状态*/
	private String card_state;
	/**卡种*/
	private String card_type_1;
	/**主副卡标志*/
	private String major_card;
	/**办卡日期*/
	private Date create_time;
	/**有效期*/
	private String effective_time;
	/**注册类型*/
	private String register_type;
	/**卡片限额*/
	private BigDecimal max_price;
	
}
