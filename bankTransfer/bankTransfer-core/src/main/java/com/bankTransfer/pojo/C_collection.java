package com.bankTransfer.pojo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 资金归集记录表
 * @author Administrator
 *
 */
@Setter
@Getter
@ToString
public class C_collection implements Serializable {

	private static final long serialVersionUID = 1L;
	/**主键*/
	private Integer id;
	/**主卡人姓名*/
	private String c_name;
	/**主账号*/
	private String c_main_account;
	/**子账号a*/
	private String c_sub_account_a;
	/**子账号b*/
	private String c_sub_account_b;
	/**主卡金额*/
	private Double c_main_amount;
	/**子账号a金额*/
	private Double c_sub_amount_a;
	/**子账号b金额*/
	private Double c_sub_amount_b;
	/**结果*/
	private String result;

}
