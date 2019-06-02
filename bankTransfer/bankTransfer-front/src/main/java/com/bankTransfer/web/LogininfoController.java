package com.bankTransfer.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bankTransfer.pojo.Logininfo;
import com.bankTransfer.pojo.User;
import com.bankTransfer.service.ILogininfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class LogininfoController {
	@Autowired
	private ILogininfoService logininfiService;
	
	@ResponseBody
	@RequestMapping("logininfo")
	  public List<Logininfo> logininfoList(@RequestParam(required=true,defaultValue="1") Integer page,
			  HttpSession session){
		List<Logininfo> queryAll = logininfiService.queryAll();
		for (Logininfo logininfo : queryAll) {
			System.err.println(logininfo);
		}
		 //在查询调用方法前声明分页信息（当前页，每页记录数）
	      //PageHelper.startPage(page, pageSize);这段代码表示，程序开始分页了，
		 //page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录。
	      PageHelper.startPage(page, 2);
	      //查询
	      List<Logininfo> logininfolist = logininfiService.queryAll();
	      System.out.println(logininfolist);
	      
	      //在查询调用方法对查询结果进行包装成PageInfo对象
	      //创建PageInfo对象，保存查询出的结果，PageInfo是pageHelper中的对象
	      PageInfo<Logininfo> p=new PageInfo<Logininfo>(logininfolist);      
	      //将数据存放到request域中
	      session.setAttribute("page", p);
	      session.setAttribute("logininfolist",logininfolist);
//	      返回页面
//	      return "show";
		return queryAll;
	  }
	
	 @RequestMapping("home")
	public String goHome(/* Map<String, Object> paramMap, */Model model) {
	        /** 默认Map的内容会放大请求域中，页面可以直接使用Thymeleaf取值*/
//	        paramMap.put("name", "张三");
//	        paramMap.put("age", 35);
	        
	        User user1 = new User();
	        user1.setUsername("王五");
	        user1.setSex("男");
	        user1.setBirthday(new Date());
	        user1.setAge(20);
	        user1.setDesc("<font color='green'><b>hello world!</b></font>");
	        model.addAttribute("user", user1);
	        
	        User user2 = new User();
	        user2.setUsername("李四");
	        user2.setSex("女");
	        user2.setBirthday(new Date());
	        user2.setAge(22);
	        
	        User user3 = new User();
	        user3.setUsername("王五");
	        user3.setSex("男");
	        user3.setBirthday(new Date());
	        user3.setAge(18);
	        
	        List<User> userList = new ArrayList<>();
	        userList.add(user1);
	        userList.add(user2);
	        userList.add(user3);
	        
	        model.addAttribute("userList",userList);
	        return "home";	       
	    }
	 
	 @RequestMapping("user")
	 public String User(User user) {
		 System.err.println(user);
		 return "user";	
	 }
	 
	 @PostMapping("postForm")
	 public String postForm(User user) {
		 
		 System.err.println(user);
		 return "home";	
	 }
	 
	// 自定义类型转换器
		@InitBinder
		public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
			
			System.out.println("initBinder  &&&&"+request.getParameter("hiredate")+"***"+request.getParameter("username"));
			
			binder.registerCustomEditor(Date.class,
					new CustomDateEditor(new SimpleDateFormat("yyyyMMdd"), true));
		}
	
}
