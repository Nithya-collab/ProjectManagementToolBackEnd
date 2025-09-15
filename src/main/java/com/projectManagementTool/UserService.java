package com.projectManagementTool;

public interface UserService {

	User findUserProfileByJwt(String jwt) throws Exception;
	
	User findUserByEmail(String email) throws Exception;
	
	User findUserById(Long userId) throws Exception;
	
	User updateusersProjectSize(User user , int number);
}
