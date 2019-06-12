package com.bankTransfer.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankTransfer.pojo.Card;
import com.bankTransfer.pojo.JsonRate;
import com.bankTransfer.service.IBaseService;
import com.bankTransfer.util.APIUtils;
import com.bankTransfer.util.JsonResult;
import com.bankTransfer.util.UserContext;
import com.google.code.kaptcha.impl.DefaultKaptcha;

/**
 * 显示指定的内容
 * 
 * @author Administrator
 *
 */
@RestController
public class ShowInfoController {
	 @Autowired
	    private DefaultKaptcha captchaProducer;
	@Autowired
	private IBaseService baseService;

	@GetMapping("showBalance")
	public Card showBalance(String id_card) {
		return baseService.queryCardByCardNumber(id_card);
	}

	
	@GetMapping("checkPassword")
	public JsonResult checkPassowrd(String id_card,String password) {
		System.err.println(id_card+"       "+password);
		JsonResult jsonResult = new JsonResult();
		try {
			baseService.queryCardByIdCardAndPassword(id_card,password);
		}catch(Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}
	
	@PostMapping("checkPhone")
	public boolean checkPhone(String phone,HttpSession session) {
		return phone.equals(UserContext.getCurrent().getTelphone());
	}
	
	
	
	@GetMapping("defaultKaptcha")
	public void defaultKaptcha(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception{  
        byte[] captchaChallengeAsJpeg = null;    
         ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();    
         try {    
         //生产验证码字符串并保存到session中  
         String createText = captchaProducer.createText();  
         httpServletRequest.getSession().setAttribute("vrifyCode", createText);  
         //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中  
         BufferedImage challenge = captchaProducer.createImage(createText);  
         ImageIO.write(challenge, "jpg", jpegOutputStream);  
         } catch (IllegalArgumentException e) {    
             httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);    
             return;    
         }   
         //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组  
         captchaChallengeAsJpeg = jpegOutputStream.toByteArray();    
         httpServletResponse.setHeader("Cache-Control", "no-store");    
         httpServletResponse.setHeader("Pragma", "no-cache");    
         httpServletResponse.setDateHeader("Expires", 0);    
         httpServletResponse.setContentType("image/jpeg");    
         ServletOutputStream responseOutputStream =    
                 httpServletResponse.getOutputStream();    
         responseOutputStream.write(captchaChallengeAsJpeg);    
         responseOutputStream.flush();    
         responseOutputStream.close();    
}  
	
	@PostMapping("checkImgCode")
	public boolean checkImgCode(String imgCode,HttpSession session){  
        return imgCode.equals(session.getAttribute("vrifyCode"));  
    }  
	
	@PostMapping("checkCode")
	public boolean checkCode(String code,HttpSession session){
		  return code.equals(session.getAttribute("code"));
    }  
	
	
	@PostMapping("sendMssage")
	public JsonResult sendMssage(String phone,HttpSession session) {
			JsonResult jsonResult = new JsonResult();
//			String code = APIUtils.sendMessage(phone);
			String code = "9999";
			session.setAttribute("code", code);
		/*
		 * if(code == null) { session.setAttribute("code", code);
		 * jsonResult.setSuccess(false); jsonResult.setMsg("系统繁忙,请稍后再试"); }
		 */
		return jsonResult;
	}
	
	@PostMapping("checkCardNumber")
	public boolean checkCardNumber(String reciverCardNumber) {
		return baseService.queryCardByCardNumber(reciverCardNumber)!=null;
	}
		
	

}
