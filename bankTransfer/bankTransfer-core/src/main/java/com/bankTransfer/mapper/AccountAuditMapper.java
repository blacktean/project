package com.bankTransfer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.CardCondition;
import com.bankTransfer.pojo.Card_VO;
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
	
	/**
	 * 查询当前审核用户信息
	 * @param id
	 * @return
	 */
	User queryCurrentUserById(Integer id);
	
	/**
	 * 审核开户信息
	 * @param id
	 * @param user_id
	 * @param updateState
	 */
	void CheckeditAccount(Integer id,Integer user_id,Integer updateState);
	
	/**
	 * 开户成功
	 * @param card
	 */
	void addId_Card(Card card);
	
	/**
	 * 带条件分页查询所有银行卡
	 * @param condition
	 * @return
	 */
	List<Card_VO> queryAllCardByCondition(CardCondition condition);
	
	/**
	 * <!-- 修改银行卡状态 -->
	 * @param id
	 * @param card_state
	 */
	void updateCardState(@Param("id")Integer id,@Param("card_state")String card_state);
}
