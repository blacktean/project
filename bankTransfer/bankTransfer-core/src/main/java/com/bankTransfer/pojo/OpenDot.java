package com.bankTransfer.pojo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OpenDot implements Serializable {

	private static final long serialVersionUID = 1L;
	/**主键*/
	private Integer id;
	/**开户地区*/
	private String open_place;
	/**开户网点名*/
	private String open_dot;
	/**网点号*/
	private Integer dot_number;
	/**联系电话*/
	private String telephone;
	/**网点地址*/
	private String dot_place;
}
