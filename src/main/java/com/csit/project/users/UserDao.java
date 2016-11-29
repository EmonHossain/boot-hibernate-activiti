package com.csit.project.users;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDao {
	
	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void saveUser(UserEntity userEntity) {
		getSession().save(userEntity);		
	}

	public void saveUserRole(UserRoleEntity userRole) {
		
		getSession().save(userRole);
		
	}
	
	
	

}
