package com.bankTransfer.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bankTransfer.pojo.Problem;
import com.bankTransfer.service.IProblemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class ProblemController {
	@Autowired
	IProblemService problemService;
	/**
	 * 分页查询问题
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("problemList")
	  public String problemList(@RequestParam(required=true,defaultValue="1") Integer page,
			  HttpSession session){
		 //在查询调用方法前声明分页信息（当前页，每页记录数）
	      //PageHelper.startPage(page, pageSize);这段代码表示，程序开始分页了，
		 //page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录。
	      PageHelper.startPage(page, 10);
	      //查询
	      List<Problem> problemlist = problemService.queryAllProblem();
	      //在查询调用方法对查询结果进行包装成PageInfo对象
	      //创建PageInfo对象，保存查询出的结果，PageInfo是pageHelper中的对象
	      PageInfo<Problem> p=new PageInfo<Problem>(problemlist);      
	      //将数据存放到request域中
	      session.setAttribute("page", p);
	      session.setAttribute("problemlist",problemlist);
	      //返回页面
	      return "problemList";
	  }
}
