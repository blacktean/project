package com.bankTransfer.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	/**主键*/
	private Integer id;
	/**用户名*/
	private String user_name;
	/**用户真实姓名*/
	private String real_name;
	/**证件类型*/
	private String card_type;
	/**证件号码*/
	private String card_number;
	/**姓名拼音*/
	private String xingming;
	/**手机号*/
	private String phone;
	/**注册方式*/
	private String reg_type;
	/**客户级别*/
	private Integer grade;
	/**英文名*/
	private String english_name;
	/**性别(1:男  0:女)*/
	private Integer sex;
	/**客户民族*/
	private String nation;
	/**客户国籍*/
	private String nationality;
	/**出生年月*/
	private Date birthday;
	/**婚姻状况*/
	private String marital_status;
	/**教育程度*/
	private String edu_level;
	/**职业*/
	private String profession;
	/**所属行业*/
	private String industry;
	/**技术职称*/
	private String technical_title;
	/**行政级别*/
	private String adminstrative_level;
	/**供养人口*/
	private Integer supporting_population;
	/**工作单位*/
	private String work_unit;
	/**进入单位时间*/
	private Date work_time;
	/**用户状态*/
	private Integer state;
	/**开户行*/
	private String create_bank;	
	/**关联logininfo id*/
	private Integer user_id;
	/**申请时间*/
	private Date applyTime; 
}
