package com.bankTransfer.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bankTransfer.pojo.Account_openingCondition;
import com.bankTransfer.pojo.Ao_accout_opening;
import com.bankTransfer.pojo.C_collection;
import com.bankTransfer.pojo.CollectionCondition;
import com.bankTransfer.pojo.T_transger;
import com.bankTransfer.pojo.TransgerCondition;
import com.bankTransfer.service.ITransgerService;
import com.bankTransfer.util.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author BLACKTEA
 *
 */
@Controller
public class TransgerController {
	@Autowired
	private ITransgerService transgerService;
	
	/**
	    *     带条件分页查询转账记录
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("/T_transger")
	public String logininfoList(@RequestParam(required=true,defaultValue="1") Integer page,
		HttpSession session,String t_name,String p_name,Date beginDate,Date endDate){
		//在查询调用方法前声明分页信息（当前页，每页记录数）
	    //PageHelper.startPage(page, pageSize);这段代码表示，程序开始分页了，
		//page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录。
	    PageHelper.startPage(page, 5);
	    //创建条件类
	    TransgerCondition condition = new TransgerCondition();
	    condition.setT_name(t_name);
	    condition.setP_name(p_name);
	    condition.setStartTime(DateUtil.beginForDate(beginDate));
	    condition.setEndTime(DateUtil.endForDate(endDate));
	    //System.err.println("传过来的条件"+condition);
	    //查询数据
	    List<T_transger> Transgerlist = transgerService.queryAllTransgerByCondition(condition);
		
	    //System.err.println("=============================Transgerlist:"+Transgerlist);		
	    //在查询调用方法对查询结果进行包装成PageInfo对象
	    //创建PageInfo对象，保存查询出的结果，PageInfo是pageHelper中的对象
	    PageInfo<T_transger> p=new PageInfo<T_transger>(Transgerlist);  
		/* System.err.println("=======================================p:"+p); */
	    //将数据存放到session域中
	    session.setAttribute("tpage", p);
	    session.setAttribute("transcondition", condition);
	    session.setAttribute("Transgerlist",Transgerlist);
	    //返回页面
	    return "Transfer";
	}
	
	
	/**
	    *    带条件分页查询资金归集记录
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("/Fund_collection")
	public String collectionList(@RequestParam(required=true,defaultValue="1") Integer page, 
		HttpSession session, String c_name, String c_main_account, Double c_main_amount, String c_sub_account) {
		PageHelper.startPage(page,5);
		//创建条件类
		CollectionCondition condition = new CollectionCondition();
		condition.setC_name(c_name);
		condition.setC_main_account(c_main_account);
		condition.setC_main_amount(c_main_amount);
		condition.setC_sub_account(c_sub_account);
		//查询数据
		List<C_collection> collectionList = transgerService.queryAllCollectionByCondition(condition);
		//创建pageInfo对象  pageInfo是pageHelper中的对象
		PageInfo<C_collection> p = new PageInfo<C_collection>(collectionList);
		//将数据存放到session域中
		session.setAttribute("c_page", p);
		session.setAttribute("collectionCondition", condition);
		session.setAttribute("collectionList", collectionList);
		return "Fund_collection";		
	}
	
	/**
	   *   带条件分页查询开户记录
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("/Account_opening")
	public String account_openingList(@RequestParam(required=true,defaultValue="1") Integer page, HttpSession session,
		String ao_name,String ao_accout_number,Date beginDate,Date endDate,String ao_cardtype ) {
		PageHelper.startPage(page,5);
		//创建条件类
		Account_openingCondition condition = new Account_openingCondition();
		condition.setAo_name(ao_name);
		condition.setAo_accout_number(ao_accout_number);
		condition.setStartTime(DateUtil.beginForDate(beginDate));
		condition.setEndTime(DateUtil.endForDate(endDate));
		condition.setAo_cardtype(ao_cardtype);
		//查询数据
		List<Ao_accout_opening> accout_openingList = transgerService.queryAllAccout_openingByCondition(condition);
		//创建pageInfo对象
		PageInfo<Ao_accout_opening> p = new PageInfo<Ao_accout_opening>(accout_openingList);
		//将数据保存到session中
		session.setAttribute("a_page", p);
		session.setAttribute("accout_openingCondition", condition);
		session.setAttribute("ao_accout_opening_list", accout_openingList);
		return "Account_opening";	
	}

	
	// 自定义类型转换器
	@InitBinder
	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
}
