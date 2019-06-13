package com.bankTransfer.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Card_VO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id_card;
	BigDecimal max_price;
}
