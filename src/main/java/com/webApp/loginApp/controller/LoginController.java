package com.webApp.loginApp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webApp.loginApp.entity.StringResponse;
import com.webApp.loginApp.entity.User;
import com.webApp.loginApp.entity.UserCookie;
import com.webApp.loginApp.service.OtpService;
import com.webApp.loginApp.service.UserCookieService;
import com.webApp.loginApp.service.UserService;

@RestController
public class LoginController {
	
	User userBean;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OtpService otpService;
	
	@Autowired
	UserCookieService userCookieService;
	
	@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
	@PostMapping("/login")
	StringResponse loadingLogin(HttpServletRequest request, @RequestBody User bean/*@RequestParam String email, @RequestParam String  pwd*/) {
		String returnMsg = "", cookieValue = "";
		UserCookie userCookie = null;		
		
		if(null != request.getCookies()) {
			Cookie cookie[] = request.getCookies();
			System.out.println("cookie array length :" + cookie.length);
			cookieValue = cookie[0].getValue();
			userCookie = userCookieService.getCookieByValue(cookieValue);
			if(cookieValue.equals(userCookie.getValue())) {
				returnMsg = "Reload";
			}
		}
		else {
			List<UserCookie> cookieList = userCookieService.getAllCookie();
			System.out.println(cookieList);
			//System.out.println("Stored Cookie : " + cookieList.get(0));
			if(cookieList == null) {
				returnMsg = "A User is already Logged In";
			}
			else {
				userBean = userService.getUserByEmail(bean.getEmail());
				if(null != userBean) {
					if(bean.getPassword().equals(userBean.getPassword())) {
						String Otp = otpService.sendOtp(userBean);
						System.out.println(Otp);
						userBean.setOtp(Otp);
						returnMsg = "OTP sent successfully";
					}
					else {
						returnMsg = "Wrong Password";
					}
				}
				else {
					returnMsg = "Account does not exits";
				}
			}	
		}
		StringResponse resp = new StringResponse(returnMsg);
		return resp;
	}
	
	@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
	@GetMapping("/logout")
	StringResponse loadingLogout(HttpServletResponse response) {
		Cookie cookie = new Cookie("UserCookie", "");
		//cookie.setMaxAge(60*3);
		//cookie.setDomain("/");
		/*
		 * UserCookie userCookie = new UserCookie(); userCookie.setValue("TestCookie");
		 * if(null == userCookieService.getCookieByValue("TestCookie")) {
		 * userCookieService.saveCookie(userCookie); }
		 */
		response.addCookie(cookie);
		StringResponse resp = new StringResponse("User was successfully logged out");
		return resp;
	}
	
	@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")	
	@RequestMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	StringResponse loadingRegister(@RequestBody User bean) {
		System.out.println(bean.toString());
		userService.saveUser(bean);
		String returnMsg = "User is created";
		StringResponse resp = new StringResponse(returnMsg);
		return resp;
	}
	
	@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
	@GetMapping("/validate")
	StringResponse validateOtp(HttpServletResponse response, @RequestParam String Otp) {
		String returnMsg;
		if(null != Otp && Otp.equals(userBean.getOtp())) {
			/*
			 * List<String> headerList = new ArrayList<String>();
			 * headerList.add("Set-Cookie"); HttpHeaders headers = new HttpHeaders();
			 * headers.setAccessControlExposeHeaders(headerList);
			 */
			//response.setHeader("Set-Cookie", "key=value; HttpOnly; SameSite=None");
			 Cookie cookie = new Cookie("UserCookie", "TestCookie");
			 cookie.setSecure(false);
			 cookie.setMaxAge(60*3);
			 //cookie.setDomain("/");
			 UserCookie userCookie = new UserCookie();
			 userCookie.setValue("TestCookie");
			 if(null == userCookieService.getCookieByValue("TestCookie")) {
				 userCookieService.saveCookie(userCookie);
			 }
			 
			 response.addHeader("Access-Control-Expose-Headers", "Set-Cookie");
			 response.addCookie(cookie);
			 returnMsg = "OTP is valid";
			 
		}
		else {
			 returnMsg = "OTP is invalid";
		}
		StringResponse resp = new StringResponse(returnMsg);
		return resp;
	}
}
