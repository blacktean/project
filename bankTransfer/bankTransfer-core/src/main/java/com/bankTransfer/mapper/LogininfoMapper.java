package com.bankTransfer.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bankTransfer.pojo.Logininfo;



@Repository
public interface LogininfoMapper {
	/**
	 * 查询所有信息
	 * @return
	 */
	List<Logininfo> queryAll();
}
