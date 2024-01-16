package com.school.sba.requestdto;

import com.school.sba.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest 
{
	@NotBlank(message = "Username cannot be blank")
	@NotNull(message = "Username cannot be null")
	@Column(unique = true)
	private String username;
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must contain at least one letter, one number, one special character")
	private String password;
	private String firstName;
	private String lastName;
	private long contactNo;
	@NotEmpty(message = "Email cannot be blank/empty")	//combination of @NotBlank + @NotNull
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email")
	private String email;
	private UserRole userRole;
}
