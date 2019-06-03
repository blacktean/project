package com.bankTransfer.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

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
	private String start_place;
	private String curreny;
	private BigDecimal balance;
	private BigDecimal available_balance;
	private String start_place_id;
	private String password;
	private String card_type;
	private String user_id;
	private String card_state;
//	private 
	
}
