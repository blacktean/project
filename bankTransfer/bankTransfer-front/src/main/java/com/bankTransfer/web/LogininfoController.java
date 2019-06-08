package com.bankTransfer.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bankTransfer.pojo.Logininfo;
import com.bankTransfer.service.ILogininfoService;
import com.bankTransfer.util.JsonResult;
import com.bankTransfer.util.LogininfoException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 登陆模块
 * 
 * @author 康志龙
 * @version 创建时间 2019年6月1日 下午2:32:44
 */
@Controller
public class LogininfoController {
	@Autowired
	private ILogininfoService logininfoService;

	/***
	 * 
	 * @param login_number   用户名/电话
	 * @param login_password 密 码
	 * @return
	 */
<<<<<<< HEAD
@RequestMapping("/logininfo")	
@ResponseBody
	public JsonResult Logininfo(String login_number,String login_password ) {
	System.err.println("进来了");
	System.err.println(login_number+""+login_password);
	JsonResult js = new JsonResult();
	boolean isOk;
	try {
		isOk = logininfoService.queryByUsernameOrPhoneAndPassword(login_number, login_password, com.bankTransfer.pojo.Logininfo.USERTYPE_NORMAL);
		System.err.println(isOk);
		if(!isOk) {
			js.setSuccess(false);
			js.setMsg("用户名或密码错误");
=======
	@RequestMapping("/logininfo")
	@ResponseBody
	public JsonResult Logininfo(String login_number, String login_password) {
		System.err.println("进来了");
		System.err.println(login_number + "" + login_password);
		JsonResult js = new JsonResult();
		boolean isOk;
		try {
			isOk = logininfoService.queryByUsernameOrPhoneAndPassword(login_number, login_password, 0);
>>>>>>> branch 'master' of https://github.com/blacktean/project.git
			System.err.println(isOk);
			if (!isOk) {
				js.setSuccess(false);
				js.setMsg("用户名或密码错误");
				System.err.println(isOk);
			}
		} catch (LogininfoException e) {
			js.setSuccess(false);
			js.setMsg(e.getMessage());
		}

		return js;
	}
	
	/**
	 * 分页查询例子
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("logininfoList")
	  public String logininfoList(@RequestParam(required=true,defaultValue="1") Integer page,
			  HttpSession session){
		 //在查询调用方法前声明分页信息（当前页，每页记录数）
	      //PageHelper.startPage(page, pageSize);这段代码表示，程序开始分页了，
		 //page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录。
	      PageHelper.startPage(page, 10);
	      //查询
	      List<Logininfo> logininfolist = logininfoService.queryAll();
	      //在查询调用方法对查询结果进行包装成PageInfo对象
	      //创建PageInfo对象，保存查询出的结果，PageInfo是pageHelper中的对象
	      PageInfo<Logininfo> p=new PageInfo<Logininfo>(logininfolist);      
	      //将数据存放到request域中
	      session.setAttribute("page", p);
	      session.setAttribute("logininfolist",logininfolist);
	      //返回页面
	      return "logininfoList";
	  }
}
