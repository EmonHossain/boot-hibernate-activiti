package com.csit.project.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
	
	@Autowired
	private UserDao userDao;
	

	public void doRegistration(UserEntity userEntity) {
		
		UserRoleEntity userRole = new UserRoleEntity();
		userRole.setRole("ROLE_ADMIN");
		
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		userEntity.setPassword(encoder.encode(userEntity.getPassword()));
		userEntity.setEnabled(true);
		
		userRole.setUserEntity(userEntity);
		
		
		try {
			userDao.saveUser(userEntity);
			userDao.saveUserRole(userRole);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	

}
