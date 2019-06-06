package com.bankTransfer.pojo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Contacts implements Serializable {

	private static final long serialVersionUID = 1L;
	/**主键*/
	private Integer id;
	/**关联用户id*/
	private Integer user_id;
	/**收款人账号*/
	private String receiving_account;
	/**收款人姓名*/
	private String receiving_name;
}
