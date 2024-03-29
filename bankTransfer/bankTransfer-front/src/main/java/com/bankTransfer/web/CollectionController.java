package com.bankTransfer.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bankTransfer.pojo.C_collection;
import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.CashSweep;
import com.bankTransfer.service.ICardService;
import com.bankTransfer.service.ICashSweepService;
import com.bankTransfer.util.DateHelpher;
import com.bankTransfer.util.JsonResult;
import com.bankTransfer.util.MsgUtil;
import com.bankTransfer.util.RequireLogin;
import com.bankTransfer.util.UserContext;

@Controller
public class CollectionController {

	@Autowired
	private ICardService cardService;
	@Autowired
	private ICashSweepService cashSweepService;

	// 跳转到新建归集
	@RequestMapping("/newcollection")
	@RequireLogin
	public String NewCollection(HttpSession session,HttpServletResponse response) throws IOException {
	    response.setCharacterEncoding("UTF-8"); 
		String username = cardService.queryUserName(UserContext.getCurrent().getId());
		String Id_card = null;//主卡
		String url="/accountApplicationHtml";
		//查询是否有主卡和副卡
		if(!cardService.queryCard(String.valueOf(UserContext.getCurrent().getId()), "0")) {			
			response.getWriter().print("<script>alert('您还有没账号 请先开户！');window.location.href='"
					+ url + "';</script>");
		}
		if(!cardService.queryCard(String.valueOf(UserContext.getCurrent().getId()), "1")) {			
			response.getWriter().print("<script>alert('您还没有副卡 不能进行资金归集业务！');window.location.href='"
					+ url + "';</script>");
		}
		//查询主卡信息
		List<Card> CardList1 = cardService
				.queryCardByUserIdAndMajorCard(String.valueOf(UserContext.getCurrent().getId()), "0");
		for (Card card : CardList1) {
			Id_card = card.getId_card();
		}
		//查询副卡信息
		List<Card> CardList2 = cardService
				.queryCardByUserIdAndMajorCard(String.valueOf(UserContext.getCurrent().getId()), "1");
		// 通过主卡查询是否有归集
		if(Id_card!=null) {
			boolean isHaveCard = cashSweepService.queryCollection(Id_card);
			session.setAttribute("isHaveCard", isHaveCard);
		}
		session.setAttribute("CardList1", CardList1);
		session.setAttribute("CardList2", CardList2);
		session.setAttribute("collectionname", username);
		return "newcollection";

	}

	// 新建资金归集
	@RequestMapping("/addCollection")
	@RequireLogin
	@ResponseBody
	public JsonResult addCollection(CashSweep cashSweep, String debit_account2) {
		JsonResult json = new JsonResult();
		//资金归集记录表
		C_collection c = new C_collection();
		c.setC_name(cashSweep.getReceiver_name());
		c.setC_main_account(cashSweep.getCollection_accout());
		c.setC_sub_account_a(cashSweep.getDebit_account());
		c.setC_main_amount(cashSweepService.queryCardBalance(cashSweep.getCollection_accout()));
		c.setC_sub_amount_a(cashSweepService.queryCardBalance(cashSweep.getDebit_account()));
		c.setResult("成功");
		try {
			if (debit_account2 != null && debit_account2.equals(cashSweep.getDebit_account())) {
				throw new RuntimeException("归集账号不能相同!");
			}
			// 设置主卡余额
			cashSweep.setBalance1(cashSweepService.queryCardBalance(cashSweep.getCollection_accout()));
			// 设置副卡余额
			cashSweep.setBalance2(cashSweepService.queryCardBalance(cashSweep.getDebit_account()));
			// 设置服务项目
			cashSweep.setService_items("资金归集");
			// 设置当前状态
			cashSweep.setState("0");
			// 设置扣款户名
			cashSweep.setDebit_name(cashSweep.getReceiver_name());
			// 设置理财序号
			String Financial_number = cashSweep.getFinancial_number();
			// 设置协议到期时间
			if (Financial_number.equals("1")) {
				cashSweep.setEnd_time(DateHelpher.subOneMonth(cashSweep.getStart_time()));
			}
			if (Financial_number.equals("2")) {
				cashSweep.setEnd_time(DateHelpher.subThreeMonth(cashSweep.getStart_time()));
			}
			if (Financial_number.equals("3")) {
				cashSweep.setEnd_time(DateHelpher.subSixMonth(cashSweep.getStart_time()));
			}
			if (Financial_number.equals("4")) {
				cashSweep.setEnd_time(DateHelpher.subTwlMonth(cashSweep.getStart_time()));
			}
			// 调用添加资金归集方法
			cashSweepService.addCollection(cashSweep);
			System.err.println("只有一个账户时 " + cashSweep);
			if (debit_account2 != null) {
				// 如果归集账户2不为空的话 再多添加一条记录
				cashSweep.setDebit_account(debit_account2);
				// 设置副卡2余额
				cashSweep.setBalance2(cashSweepService.queryCardBalance(debit_account2));
				//设置资金归集记录表参数
				c.setC_sub_account_b(debit_account2);
				c.setC_sub_amount_b(cashSweepService.queryCardBalance(debit_account2));
				cashSweepService.addCollection(cashSweep);
				System.err.println("两个账户时 " + cashSweep);
			}
			//调用添加资金归集记录方法
			cashSweepService.insert_C_collection(c);
			json.setSuccess(true);
		} catch (RuntimeException e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}

		return json;

	}

	// 跳转到我的归集
	@RequestMapping("/mycollection")
	@RequireLogin
	public String MyCollection(HttpSession session,HttpServletResponse response) throws IOException {
		  response.setCharacterEncoding("UTF-8"); 
		// 查询当前用户的主账户卡号
		String Id_card = null;
		String url="/accountApplicationHtml";
		//查询是否有主卡和副卡 没有则返回开户页面
		if(!cardService.queryCard(String.valueOf(UserContext.getCurrent().getId()), "0")) {			
			response.getWriter().print("<script>alert('您还有没账号 请先开户！');window.location.href='"
					+ url + "';</script>");
		}
		if(!cardService.queryCard(String.valueOf(UserContext.getCurrent().getId()), "1")) {			
			response.getWriter().print("<script>alert('您还没有副卡 不能进行资金归集业务！');window.location.href='"
					+ url + "';</script>");
		}
		//查询主卡信息
		List<Card> CardList1 = cardService
				.queryCardByUserIdAndMajorCard(String.valueOf(UserContext.getCurrent().getId()), "0");
		for (Card card : CardList1) {
			Id_card = card.getId_card();
		}
		//当主卡卡号不为空时
		if(Id_card!=null) {
			// 通过主卡查询是否有归集
			boolean isHaveCard = cashSweepService.queryCollection(Id_card);
			session.setAttribute("isHaveCard", isHaveCard);
			// 查询资金归集明细
			List<CashSweep> collectionList = cashSweepService.queryCollectionList(Id_card);
			session.setAttribute("collectionList", collectionList);
			// 查询第一条资金归集明细
			CashSweep cashSweep = cashSweepService.queryOneCollection(Id_card);
			session.setAttribute("cashSweep", cashSweep);
		}		
		return "mycollection";

	}

	// 跳转到我的归集明细
	@RequestMapping("/collectiondetails")
	@RequireLogin
	public String CollectionDetails(HttpSession session,HttpServletResponse response) throws IOException {
		 response.setCharacterEncoding("UTF-8"); 
		// 查询当前用户的主账户卡号
		// 查询当前用户的主账户卡号
			String Id_card = null;
			String url="/accountApplicationHtml";
			//查询是否有主卡和副卡 没有则返回开户页面
			if(!cardService.queryCard(String.valueOf(UserContext.getCurrent().getId()), "0")) {			
				response.getWriter().print("<script>alert('您还有没账号 请先开户！');window.location.href='"
						+ url + "';</script>");
			}
			if(!cardService.queryCard(String.valueOf(UserContext.getCurrent().getId()), "1")) {			
				response.getWriter().print("<script>alert('您还没有副卡 不能进行资金归集业务！');window.location.href='"
						+ url + "';</script>");
			}
		//查询主卡信息拿到主卡
		List<Card> CardList1 = cardService
				.queryCardByUserIdAndMajorCard(String.valueOf(UserContext.getCurrent().getId()), "0");
		for (Card card : CardList1) {
			Id_card = card.getId_card();
		}		
		//当主卡卡号不为空时
		if(Id_card!=null) {
			// 通过主卡查询是否有归集
			boolean isHaveCard = cashSweepService.queryCollection(Id_card);
			session.setAttribute("isHaveCard", isHaveCard);
			// 查询资金归集明细
			List<CashSweep> collectionList = cashSweepService.queryCollectionList(Id_card);
			session.setAttribute("collectionList", collectionList);
			// 查询第一条资金归集明细
			CashSweep cashSweep = cashSweepService.queryOneCollection(Id_card);
			session.setAttribute("cashSweep", cashSweep);
		}	
		
		return "collectiondetails";

	}

	// 跳转到我的归集明细设置
	@RequestMapping("/setupcollection")
	@RequireLogin
	public String setUpCollection() {

		return "setupcollection";

	}
	
	//修改资金归集
	@RequestMapping("/saveUpdateCollection")
	@RequireLogin
	@ResponseBody
	public JsonResult saveUpdateCollection(CashSweep cashSweep,HttpSession session) {
		JsonResult json = new JsonResult();
		try {		
		//初始副卡卡号
		String startDebit_account = cashSweep.getDebit_account();
		//截取第一张卡 设置第一张副卡卡号
		String debit_account1 = cashSweep.getDebit_account().substring(0,19);
		if(cashSweep.getCollection_accout().equals(debit_account1)) {
			throw new RuntimeException("收款账户和归集账户不能相同");
		}
		cashSweep.setDebit_account(debit_account1);
		System.err.println(cashSweep);
		//调用修改方法
		cashSweepService.saveUpdateCollection(cashSweep);
		//如果存在第二张卡
		if(startDebit_account.length()>19) {
			String debit_account2 = startDebit_account.substring(20);
			if(cashSweep.getCollection_accout().equals(debit_account2)) {
				throw new RuntimeException("收款账户和归集账户不能相同");
			}
			cashSweep.setDebit_account(debit_account2);
			System.err.println(cashSweep);
			cashSweepService.saveUpdateCollection(cashSweep);
		}
		
		}catch(RuntimeException e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}				
		return json;
	}
	
	// 验证短信是否发送成功
	@RequestMapping("/checkphoneNumberById")
	@ResponseBody
	@RequireLogin
	public JsonResult checkphoneNumberById(String debit_tel, HttpSession session) {
		JsonResult json = new JsonResult();
		System.err.println(debit_tel);
		// 调用发送手机短信的接口
		if (MsgUtil.mobileQuery(debit_tel)) {
			// true:发送成功
			json.setSuccess(true);
			return json;
		}
		json.setSuccess(false);
		json.setMsg("手机号错误 发送失败");
		return json;
	}

	// 验证验证码是否正确
	@RequestMapping("/checkverifyCode")
	@ResponseBody
	@RequireLogin
	public JsonResult checkverifyCode(String verifyCode, HttpSession session) {
		// 如果发送验证码成功 从域中拿到随机发送的验证码
		String mathCode = (String) session.getAttribute("mathCode");
		JsonResult json = new JsonResult();
		System.err.println("传过来的验证码" + verifyCode);
		System.err.println("随机生成的验证码" + mathCode);
		if (verifyCode.equals(mathCode)) {
			// 如果验证码相等 返回true
			json.setSuccess(true);
			json.setMsg("验证码正确");
			return json;
		}
		json.setSuccess(false);
		json.setMsg("验证码错误,请输入正确的验证码");
		return json;
	}

	// 判断该银行卡是否存在
	@RequestMapping("checkDebit_account2")
	@ResponseBody
	public boolean checkDebit_account2(String debit_account2) {
		return cardService.getCountByIdCard(debit_account2);

	}
	
	//验证支付密码
	@RequestMapping("/checkPailPWD")
	@ResponseBody
	@RequireLogin
	public JsonResult checkPailPWD(String pailPWD, HttpSession session) {
		JsonResult json = new JsonResult();
		boolean isTrue = cardService.checkPailPWD(String.valueOf(UserContext.getCurrent().getId()), pailPWD);
		if(!isTrue) {
			json.setSuccess(false);
			json.setMsg("密码错误 请重新输入");
		}
		
		return json;
	}
		//终止资金归集服务
		@RequestMapping("/shutDownService")
		@ResponseBody
		@RequireLogin
		public JsonResult shutDownService(String pailPWD, HttpSession session) {
			JsonResult json = new JsonResult();

			boolean isTrue = cardService.checkPailPWD(String.valueOf(UserContext.getCurrent().getId()), pailPWD);
			if(!isTrue) {
				json.setSuccess(false);
				json.setMsg("密码错误 请重新输入");
			}else{
				//从域中拿到主卡卡号
				CashSweep cashSweep = (CashSweep) session.getAttribute("cashSweep");
				//调用终止服务方法
				cashSweepService.shutDownService(cashSweep.getCollection_accout());
				json.setMsg("终止服务成功");
				json.setSuccess(true);
			}						
			return json;
		}
		
//		@RequestMapping("/myaccount")
//		@RequireRealauth
//		public String MyAccount() {
//			return "myaccount";
//			
//		}
	// 自定义类型转换器
	@InitBinder
	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {

		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
}
