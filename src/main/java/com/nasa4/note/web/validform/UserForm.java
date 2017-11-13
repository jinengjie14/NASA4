package com.nasa4.note.web.validform;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class UserForm {
	@NotBlank(message="user.account.isnull", groups= {LoginForm.class, RegisterForm.class})
	private String account;
	
	@NotBlank(message="user.password.isnull", groups= {LoginForm.class, RegisterForm.class})
	@Size(min=6, max=16, message = "user.passwd.length.error", groups= {LoginForm.class, RegisterForm.class})
	private String password;
	
	@NotBlank(message="user.password.again", groups={RegisterForm.class})
	@Size(min=6, max=16, message = "user.passwd.length.error", groups={RegisterForm.class})
	private String apassword;
	
	public interface LoginForm {
		
	}
	public interface RegisterForm {
		
	}
}

