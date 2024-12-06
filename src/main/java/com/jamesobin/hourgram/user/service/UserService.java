package com.jamesobin.hourgram.user.service;

import org.springframework.stereotype.Service;

import com.jamesobin.hourgram.common.MD5HashingEncoder;
import com.jamesobin.hourgram.user.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	
	// autowired를 위한 생성자만 존재하는 경우 생략 가능
//	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public boolean addUser(
			String loginId
			, String password
			, String name
			, String email
			, String profileName) {
		
		String encodingPassword = MD5HashingEncoder.encode(password);
		
		int count = userRepository.insertUser(loginId, encodingPassword, name, email, profileName);
		
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}
	
}

