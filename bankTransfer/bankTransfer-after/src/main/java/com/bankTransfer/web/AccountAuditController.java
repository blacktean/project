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

import com.bankTransfer.pojo.User;
import com.bankTransfer.pojo.UserCondition;
import com.bankTransfer.service.IAccountAuditService;
import com.bankTransfer.util.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 开户申请
 * @author zsy
 *
 */
@Controller
public class AccountAuditController {
	@Autowired
	private IAccountAuditService accountAuditService;
	
	/**
	 * 高级带条件分页查询
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("/AccountAudit")
	  public String logininfoList(@RequestParam(required=true,defaultValue="1") Integer page,
			  HttpSession session,Date beginDate,Date endDate,Integer state,String card_number){
		 //在查询调用方法前声明分页信息（当前页，每页记录数）
	     //PageHelper.startPage(page, pageSize);这段代码表示，程序开始分页了，
		 //page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录。
	      PageHelper.startPage(page, 5);
	      //创建条件类
	      UserCondition condition = new UserCondition();
	      condition.setCard_number(card_number);
	      condition.setStartTime(DateUtil.beginForDate(beginDate));
	      condition.setEndTime(DateUtil.endForDate(endDate));
	      condition.setState(state);
	      System.err.println("传过来的条件"+condition);
	      //查询数据
	      List<User> AccountAuditlist = accountAuditService.queryAllByCondition(condition);
	      //在查询调用方法对查询结果进行包装成PageInfo对象
	      //创建PageInfo对象，保存查询出的结果，PageInfo是pageHelper中的对象
	      PageInfo<User> p=new PageInfo<User>(AccountAuditlist);      
	      //将数据存放到session域中
	      session.setAttribute("page", p);
	      session.setAttribute("condition", condition);
	      session.setAttribute("AccountAuditlist",AccountAuditlist);
	      //返回页面
	      return "kaihushenhe";
	  }
	
	
	
		// 自定义类型转换器
		@InitBinder
		public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {

			binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
		}
}
