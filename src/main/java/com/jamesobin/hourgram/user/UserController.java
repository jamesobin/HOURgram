package com.jamesobin.hourgram.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {

	@GetMapping("/join-view")
	public String inputJoin() {
		return "user/join";
	}
	
	@GetMapping("/login-view")
	public String inputLogin() {
		return "user/login";
	}
	
	@GetMapping("/profile-view")
	public String profile() {
		return "user/profile";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userProfileName");
		
		return "redirect:/user/login-view";
	}
	
}
