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
	private Integer id;
	private String username;
	private String password;
	private Integer state;
	private Integer usertype;
	
	
}	
