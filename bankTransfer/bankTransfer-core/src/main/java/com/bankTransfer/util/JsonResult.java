package com.bankTransfer.util;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonResult {
	
	/**success 是否成功  true 表示成功, false 表示失败 */
	private boolean success = true;
	private String msg;
	public JsonResult() {
		
	}
	public JsonResult(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}
	public JsonResult(String msg) {
		super();
		this.msg = msg;
	}
	
}
