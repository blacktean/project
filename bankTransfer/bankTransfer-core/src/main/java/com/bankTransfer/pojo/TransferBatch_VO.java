package com.bankTransfer.pojo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TransferBatch_VO {
	private List<UserVo> users;
	private Float allMoney;
	private String payCardNum;

}

@Setter
@Getter
@ToString
class UserVo {
	/** 收款人姓名*/
	private String name;
	/** 收款人卡号*/
	private String card_number;
	/** 转账金额*/
	private Float payMoney;
//	private String place;
	/** 收款人省份*/
	private String place_1;
	/** 收款人地区*/
	private String place_2;
	/** 收款人手机号*/
	private String phone;
	/** 短信内容*/
	private String msg;
}
