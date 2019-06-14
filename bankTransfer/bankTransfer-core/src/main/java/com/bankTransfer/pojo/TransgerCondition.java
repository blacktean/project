package com.bankTransfer.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 后台转账记录条件类
 * @author BLACKTEA
 *
 */
@Data
public class TransgerCondition implements Serializable {

	private static final long serialVersionUID = 1L;
	/**转款人姓名*/
	private String t_name;
	/**收款人姓名*/
	private String p_name;
	/**开始时间 */
	private Date startTime;
	/**结束时间 */
	private Date endTime;
	/**转账状态0转账成功 1转账失败 */
	private Integer result;
}
