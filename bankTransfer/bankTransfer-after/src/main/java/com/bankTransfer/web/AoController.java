package com.bankTransfer.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bankTransfer.pojo.Ao_accout_opening;
import com.bankTransfer.service.IAoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class AoController {
	@Autowired
	private IAoService aoService;
	
	@RequestMapping("/aoselect")
	public String AoSelect(@RequestParam(required=true,defaultValue="1") Integer page,
			  HttpSession session) {
		System.out.println("++++++++++++++++++++++++++++++++++++==");
		PageHelper.startPage(page,6);
		List<Ao_accout_opening> ao_accout = aoService.queryAll();
		PageInfo<Ao_accout_opening> p = new PageInfo<Ao_accout_opening>(ao_accout);
		p.setPages(6);
		session.setAttribute("page", p);
		session.setAttribute("==================================ao_accout_opening", ao_accout);
		System.out.println(ao_accout);
		return "index";		
	}	
}
