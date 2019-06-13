package com.bankTransfer.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 *  开户记录表
 * @author BLACKTEA
 *
 */
@Data
public class Ao_accout_opening implements Serializable {

	private static final long serialVersionUID = 1L;
	/**主键*/
	private Integer id;
	/**姓名*/
	private String ao_name;
	/**开户账号*/
	private String ao_accout_number;
	/**开户时间*/
	private Date ao_time;
	/**开户地点*/
	private String ao_location;
	/**卡类型*/
	private String ao_cardtype;
	/**结果*/
	private String result;

}
