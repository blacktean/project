package com.bankTransfer.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	//跳转到新建归集
	@RequestMapping("/newcollection")
	@RequireLogin
	public String NewCollection(HttpSession session) {
		//System.err.println(session.getAttribute("logininfo"));
		String username = cardService.queryUserName(UserContext.getCurrent().getId());
		List<Card> CardList1 = cardService.queryCardByUserIdAndMajorCard(String.valueOf(UserContext.getCurrent().getId()), "0");
		List<Card> CardList2 = cardService.queryCardByUserIdAndMajorCard(String.valueOf(UserContext.getCurrent().getId()), "1");
		session.setAttribute("CardList1", CardList1);
		session.setAttribute("CardList2", CardList2);
		session.setAttribute("collectionname", username);
		return "newcollection";
		
	}
	//新建资金归集
	@RequestMapping("/addCollection")
	@RequireLogin
	@ResponseBody
	public JsonResult addCollection(CashSweep cashSweep,String debit_account2) {
		JsonResult json = new JsonResult();
		try {			
			if(debit_account2!=null && debit_account2.equals(cashSweep.getDebit_account())) {
				throw new RuntimeException("归集账号不能相同!");
			}
			//设置服务项目
			cashSweep.setService_items("资金归集");
			//设置当前状态
			cashSweep.setState("0");
			//设置扣款户名
			cashSweep.setDebit_name(cashSweep.getReceiver_name());
			//设置理财序号
			String Financial_number = cashSweep.getFinancial_number();
			//设置协议到期时间
			if(Financial_number.equals("1")) {
				cashSweep.setEnd_time(DateHelpher.subOneMonth(cashSweep.getStart_time()));
			}
			if(Financial_number.equals("2")) {
				cashSweep.setEnd_time(DateHelpher.subThreeMonth(cashSweep.getStart_time()));
			}
			if(Financial_number.equals("3")) {
				cashSweep.setEnd_time(DateHelpher.subSixMonth(cashSweep.getStart_time()));
			}
			if(Financial_number.equals("4")) {
				cashSweep.setEnd_time(DateHelpher.subTwlMonth(cashSweep.getStart_time()));
			}
			//调用添加资金归集方法
			cashSweepService.addCollection(cashSweep);
		}catch(RuntimeException e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
		//跳转到我的归集
		@RequestMapping("/mycollection")
		@RequireLogin
		public String MyCollection() {
			
			
			return "mycollection";
			
		}
	//验证短信是否发送成功
		@RequestMapping("/checkphoneNumberById")
		@ResponseBody
		//@RequireLogin
		public JsonResult checkphoneNumberById(String debit_tel,HttpSession session) {
			JsonResult json = new JsonResult();
			System.err.println(debit_tel);	
			//调用发送手机短信的接口
			if(MsgUtil.mobileQuery(debit_tel)) {
				//true:发送成功  
				json.setSuccess(true);
				return json;
			}				  
				json.setSuccess(false);
				json.setMsg("手机号错误 发送失败");	
			return json;	
		}
		
		//验证验证码是否正确
		@RequestMapping("/checkverifyCode")
		@ResponseBody
		//@RequireLogin
		public JsonResult checkverifyCode(String verifyCode,HttpSession session) {
			//如果发送验证码成功 从域中拿到随机发送的验证码
			String mathCode = (String) session.getAttribute("mathCode");
			JsonResult json = new JsonResult();
			System.err.println("传过来的验证码"+verifyCode);
			System.err.println("随机生成的验证码"+mathCode);
			if(verifyCode.equals(mathCode)) {
				//如果验证码相等 返回true
				json.setSuccess(true);
				json.setMsg("验证码正确");
				return json;
			}	
			json.setSuccess(false);
			json.setMsg("验证码错误,请输入正确的验证码");
			return json;		
		}
		//判断该银行卡是否存在
		@RequestMapping("checkDebit_account2")
		@ResponseBody
		public boolean checkDebit_account2(String debit_account2) {		
			return cardService.getCountByIdCard(debit_account2);
					
		}
}
