package com.school.sba.service;

import org.springframework.http.ResponseEntity;

import com.school.sba.requestdto.UserRequest;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.util.ResponseStructure;

import jakarta.validation.Valid;

public interface UserService 
{
	ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest user);	
}
