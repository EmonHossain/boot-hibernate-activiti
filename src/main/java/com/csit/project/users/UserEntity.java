package com.csit.project.users;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@Column(name = "username", length = 20, nullable = false)
	private String userName;

	@Column(nullable = false)
	private String password;

	private boolean enabled;

	@OneToOne(optional = false, cascade = CascadeType.ALL, mappedBy = "userEntity", targetEntity = UserRoleEntity.class)
	private UserRoleEntity userRole;

	/*
	 * @OneToOne(optional = false, cascade = CascadeType.ALL, mappedBy =
	 * "userEntity", targetEntity = RegisterEntity.class) private RegisterEntity
	 * registerEntity;
	 */

	public UserEntity() {

	}

	public UserEntity(String userName, String password, boolean enabled, UserRoleEntity userRole) {
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public UserRoleEntity getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoleEntity userRole) {
		this.userRole = userRole;
	}

	/*
	 * public RegisterEntity getRegisterEntity() { return registerEntity; }
	 * 
	 * public void setRegisterEntity(RegisterEntity registerEntity) {
	 * this.registerEntity = registerEntity; }
	 */

}
