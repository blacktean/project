package com.bankTransfer.pojo;

import java.io.Serializable;

import lombok.Data;

/**
   * 资金归集记录条件类
 * @author BLACKTEA
 *
 */
@Data
public class CollectionCondition implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**主卡人姓名*/
	private String c_name;
	/**主账号*/
	private String c_main_account;
	/**主卡金额*/
	private Double c_main_amount;
	/**子账号*/
	private String c_sub_account;
	/**资金归集状态1归集成功 0归集失败 */
	private Integer result;
	
}
