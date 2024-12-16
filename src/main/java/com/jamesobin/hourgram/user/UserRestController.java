package com.jamesobin.hourgram.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jamesobin.hourgram.user.domain.User;
import com.jamesobin.hourgram.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@RequestMapping("/user")
@RestController
public class UserRestController {
	
	private UserService userService;
	
//	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("email") String email
			, @RequestParam("profileName") String profileName) {
		Map<String, String> resultMap = new HashMap<>();
		
		if(userService.addUser(loginId, password, name, email, profileName)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	@GetMapping("/duplicate-id")
	public Map<String, Boolean> isDuplicateId(@RequestParam("loginId") String loginId) {
		
		Map<String, Boolean> resultMap = new HashMap<>();
		
		resultMap.put("isIdDuplicate", userService.isDuplicateId(loginId));
		
		return resultMap;
	}
	
	@GetMapping("/duplicate-profileName")
	public Map<String, Boolean> isDuplicateProfileName(@RequestParam("profileName") String profileName) {
		
		Map<String, Boolean> resultMap = new HashMap<>();
		
		resultMap.put("isProfileDuplicate", userService.isDuplicateProfileName(profileName));
		
		return resultMap;
	}
	
	@PostMapping("/login")
	public Map<String, String> login(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			,  HttpServletRequest request) {
		
		User user = userService.getUser(loginId, password);
		
		Map<String, String> resultMap = new HashMap<>();
		if(user != null) {
			HttpSession session = request.getSession();
			
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userProfileName", user.getProfileName());
			
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
}
