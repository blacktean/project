package com.bankTransfer.pojo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Problem implements Serializable{

	private static final long serialVersionUID = 1L;
	/**主键*/
	private Integer id;
	/**问题名*/
	private String problem_name;
	/**问题类型*/
	private String problem_type;
	/**问题详情*/
	private String problem_detail;
}
