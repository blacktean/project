package com.bankTransfer.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String sex;
	private int age;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a",locale = "zh",timezone = "GMT+8")
	private Date birthday;
	@JsonInclude(Include.NON_NULL)
	private String desc;
	
	
	
}
