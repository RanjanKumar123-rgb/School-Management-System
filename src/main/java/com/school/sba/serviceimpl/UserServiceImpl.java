package com.school.sba.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.User;
import com.school.sba.enums.UserRole;
import com.school.sba.exceptions.AdminException;
import com.school.sba.exceptions.UniqueConstraintViolationException;
import com.school.sba.repository.UserRepo;
import com.school.sba.requestdto.UserRequest;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.service.UserService;
import com.school.sba.util.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ResponseStructure<UserResponse> structure;

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@Valid UserRequest userRequest) 
	{
		if(userRequest.getUserRole()==UserRole.ADMIN && userRepo.existsByUserRole(UserRole.ADMIN))
		{
			throw new AdminException("Cannot create a 2nd admin");
		}else {
			User user;
			try {
				user = userRepo.save(mapToUser(userRequest));
				structure.setStatusCode(HttpStatus.CREATED.value());
				structure.setMessage("Saved to Database");
				structure.setData(mapToUserResponse(user));
			} catch (Exception e) {
				throw new UniqueConstraintViolationException("Username or Email or Contact number is not unique");
			}
			return new ResponseEntity<ResponseStructure<UserResponse>> (structure, HttpStatus.OK);
		}
		
	}
	
	public User mapToUser(UserRequest request)
	{
		return User.builder()
				.username(request.getUsername())
				.password(request.getPassword())
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.contactNo(request.getContactNo())
				.email(request.getEmail())
				.userRole(request.getUserRole())
				.build();
	}
	
	public UserResponse mapToUserResponse(User user)
	{
		return UserResponse.builder()
				.userId(user.getUserId())
				.username(user.getUsername())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.contactNo(user.getContactNo())
				.email(user.getEmail())
				.userRole(user.getUserRole())
				.build();
	}
}
