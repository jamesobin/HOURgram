package com.jamesobin.hourgram.user.service;

import org.springframework.stereotype.Service;

import com.jamesobin.hourgram.common.SHA256HashingEncoder;
import com.jamesobin.hourgram.user.domain.User;
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
		
		String encodingPassword = SHA256HashingEncoder.encode(password);
		
		int count = userRepository.insertUser(loginId, encodingPassword, name, email, profileName);
		
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isDuplicateId(String loginId) {
		
		int count = userRepository.selectCountLoginId(loginId);
		
		if(count > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isDuplicateProfileName(String profileName) {
		
		int count = userRepository.selectCountProfileName(profileName);
		
		if(count > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public User getUser(String loginId, String password) {
		
		String encodingPassword = SHA256HashingEncoder.encode(password);
		
		return userRepository.selectUser(loginId, encodingPassword);
	}
	
	
	public User getUserById(int id) {
		return userRepository.selectUserById(id);
	}
	
}

