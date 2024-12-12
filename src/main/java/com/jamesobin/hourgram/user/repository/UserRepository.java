package com.jamesobin.hourgram.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jamesobin.hourgram.user.domain.User;

@Mapper
public interface UserRepository {

	public int insertUser(
			@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("name") String name
			, @Param("email") String email
			, @Param("profileName") String profileName);
		
	public int selectCountLoginId(@Param("loginId") String loginId);
	
	public int selectCountProfileName(@Param("profileName") String profileName);
	
	public User selectUser(
			@Param("loginId") String loginId
			, @Param("password") String password);
	
	public User selectUserById(@Param("id") int id);
	
}