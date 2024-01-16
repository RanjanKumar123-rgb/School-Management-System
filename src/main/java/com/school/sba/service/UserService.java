package com.school.sba.service;

import org.springframework.http.ResponseEntity;

import com.school.sba.requestdto.UserRequest;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.util.ResponseStructure;

public interface UserService 
{
	ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest user);

	ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId);	
}
