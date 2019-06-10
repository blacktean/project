package com.bankTransfer.pojo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *注册用户信息实体类
 * @author hcf
 *
 */
@Getter
@Setter
@ToString
public class Logininfo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**正常状态*/
	public static final int STATE_NORMAL=0;
	/**锁定状态*/
	public static  final int  STATE_LOCK = 1;
	/**已删除状态*/
	public static  final int  STATE_DELETE = -1;
	/**未审核状态*/
	public static final int   STATE_EXAMINE = 2;
	
	/**前台用户*/
	public static  final int  USERTYPE_NORMAL = 0;
	/**后台用户(管理员)*/
	public static  final int  USERTYPE_SYSTEM = 1;
	
	/**id*/
	private Integer id;
	/**登录用户名*/
	private String username;
	/**登录密码*/
	private String password;
	/**登录状态*/
	private Integer state;
	/**用户类型 是否为前台还是后台*/
	private Integer usertype;
	/**手机号*/
	private String telphone;
	/**剩余登陆次数*/
	private Integer times;
}	
