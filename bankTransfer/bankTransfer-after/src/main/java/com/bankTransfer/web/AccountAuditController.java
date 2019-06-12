package com.bankTransfer.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.CardCondition;
import com.bankTransfer.pojo.Card_VO;
import com.bankTransfer.pojo.User;
import com.bankTransfer.pojo.UserCondition;
import com.bankTransfer.service.IAccountAuditService;
import com.bankTransfer.util.DateUtil;
import com.bankTransfer.util.JsonResult;
import com.bankTransfer.util.MD5;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 开户申请
 * 
 * @author zsy
 *
 */
@Controller
public class AccountAuditController {
	@Autowired
	private IAccountAuditService accountAuditService;

	/**
	 * 高级带条件分页查询
	 * 
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("/AccountAudit")
	public String logininfoList(@RequestParam(required = true, defaultValue = "1") Integer page, HttpSession session,
			Date beginDate, Date endDate, Integer state, String card_number) {
		// 在查询调用方法前声明分页信息（当前页，每页记录数）
		// PageHelper.startPage(page, pageSize);这段代码表示，程序开始分页了，
		// page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录。
		PageHelper.startPage(page, 5);
		// 创建条件类
		UserCondition condition = new UserCondition();
		condition.setCard_number(card_number);
		condition.setStartTime(DateUtil.beginForDate(beginDate));
		condition.setEndTime(DateUtil.endForDate(endDate));
		condition.setState(state);
		System.err.println("传过来的条件" + condition);
		// 查询数据
		List<User> AccountAuditlist = accountAuditService.queryAllByCondition(condition);
		// 在查询调用方法对查询结果进行包装成PageInfo对象
		// 创建PageInfo对象，保存查询出的结果，PageInfo是pageHelper中的对象
		PageInfo<User> p = new PageInfo<User>(AccountAuditlist);
		// 将数据存放到session域中
		session.setAttribute("page", p);
		session.setAttribute("condition", condition);
		session.setAttribute("AccountAuditlist", AccountAuditlist);
		// 返回页面
		return "kaihushenhe";
	}

	// 开户审核
	@RequestMapping("/editAccount")
	public String EditAccount(Integer id, Model model) {
		System.err.println("传过来的ID" + id);
		User currentUser = accountAuditService.queryCurrentUserById(id);
		model.addAttribute("currentUser", currentUser);
		return "editAccount";

	}

	// 审核
	@RequestMapping("/CheckeditAccount")
	@ResponseBody
	public JsonResult CheckeditAccount(Integer id, Integer user_id,Integer state) {
		JsonResult json = new JsonResult();
		// 修改状态值 0审核通过 1未审核  2已注销 3审核失败
		accountAuditService.CheckeditAccount(id, user_id, state);
		// 如果审核通过的话 给当前用户随机生成一张卡号
		if(state==0) {
			Card card = new Card();
			//随机生成卡号
			String mathcard = String.valueOf((int)((Math.random()*9+1)*10000));
			card.setId_card("45092319960628"+mathcard);
			card.setStart_place("深圳南山区");
			card.setCurrency("2");
			card.setBalance(new BigDecimal(10000));
			card.setAvailable_balance(new BigDecimal(10000));
			card.setStart_place_id("1");
			card.setPassword(MD5.encode("123456"));
			card.setCard_type("乔治银行");
			card.setUser_id(String.valueOf(user_id));
			card.setCard_state("1");
			card.setMajor_card("0");
			card.setCreate_time(new Date());
			card.setEffective_time("10");
			card.setRegister_type("个人");
			card.setMax_price(new BigDecimal(10000));
			card.setBank_type("乔治银行");
			//调用开户方法
			System.err.println(card);
			accountAuditService.addId_Card(card);
		}
		return json;
	}
	
	//删除 把状态变为已注销
	@RequestMapping("/delAccount")
	public String delAccount(Integer id, Integer user_id) {
		// 修改状态值 0审核通过 1未审核  2已注销 3审核失败
		accountAuditService.CheckeditAccount(id, user_id, 2);
		return "redirect:/AccountAudit";
	}
	
	
//	// 审核失败
//	@RequestMapping("/FaileditAccount")
//	public String FaileditAccount(Integer id, Integer user_id) {
//		System.err.println("传过来的ID" + id + "user_id" + user_id);
//		// 修改状态值 3审核失败
//		accountAuditService.CheckeditAccount(id, user_id, 3);
//
//		return "redirect:/AccountAudit";
//
//	}

	//取消审核
	@RequestMapping("/CloseeditAccount")
	public String CloseeditAccount() {	
		
		return "kaihushenhe";
	}
	
	/**
	 * 带条件分页查询所有卡
	 * @param page
	 * @param session
	 * @param beginDate
	 * @param endDate
	 * @param card_state
	 * @param card_number
	 * @param id_card
	 * @param real_name
	 * @return
	 */
	@RequestMapping("/CardListAccount")
	public String CardList(@RequestParam(required = true, defaultValue = "1") Integer page, HttpSession session,
			Date beginDate, Date endDate, String card_state, String card_number,String id_card,String real_name) {
		// 在查询调用方法前声明分页信息（当前页，每页记录数）
		// PageHelper.startPage(page, pageSize);这段代码表示，程序开始分页了，
		// page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录。
		PageHelper.startPage(page, 5);
		// 创建条件类
		CardCondition condition = new CardCondition();
		condition.setCard_number(card_number);
		condition.setStartTime(DateUtil.beginForDate(beginDate));
		condition.setEndTime(DateUtil.endForDate(endDate));
		condition.setCard_state(card_state);
		condition.setReal_name(real_name);
		condition.setId_card(id_card);
		System.err.println("传过来的条件" + condition);
		// 查询数据
		List<Card_VO> AllCardList = accountAuditService.queryAllCardByCondition(condition);
		// 在查询调用方法对查询结果进行包装成PageInfo对象
		// 创建PageInfo对象，保存查询出的结果，PageInfo是pageHelper中的对象
		PageInfo<Card_VO> p = new PageInfo<Card_VO>(AllCardList);
		// 将数据存放到session域中
		session.setAttribute("cardpage", p);
		session.setAttribute("cardcondition", condition);
		session.setAttribute("AllCardList", AllCardList);
		// 返回页面
		return "kaguanli";
	}
	
	/**
	 * 冻结账户
	 * @param id
	 * @return
	 */
	@RequestMapping("/shutDownCardAccount")
	public String shutDownCardAccount(Integer id,String card_state) {
		System.err.println("id为"+id+"状态值为"+card_state);
		accountAuditService.updateCardState(id, card_state);
		return "redirect:/CardListAccount";
	}
	
	
	// 自定义类型转换器
	@InitBinder
	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {

		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
}
