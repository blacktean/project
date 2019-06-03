package com.bankTransfer.pojo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardType implements Serializable {

	private static final long serialVersionUID = 1L;
	/**主键*/
	private Integer id;
	/**种类名*/
	private String type_name;
	/**种类序号*/
	private String type_code;
}
