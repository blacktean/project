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
	private String name;
	private String card_number;
	private Float payMoney;
	private String place;
	private String phone;
	private String msg;
}
