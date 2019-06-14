package com.bankTransfer.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HuiLvData {
	/**success 是否成功  true 表示成功, false 表示失败 */
	private boolean success = true;
	private String msg;
	private Float money;
	private String from;
	private String to;
	
	
}
