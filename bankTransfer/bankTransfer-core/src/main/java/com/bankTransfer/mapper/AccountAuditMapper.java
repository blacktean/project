package com.bankTransfer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bankTransfer.pojo.User;
import com.bankTransfer.pojo.UserCondition;
@Mapper
/**
 * 开户申请
 * @author zsy
 *
 */
public interface AccountAuditMapper {
	
	/**
	 * 带条件分页查询所有开户申请
	 * @param condition
	 * @return
	 */
	List<User> queryAllByCondition(UserCondition condition);
	
}
