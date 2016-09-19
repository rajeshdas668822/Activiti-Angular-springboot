package org.springboot.angular.demo.usermanagment.security.impl;

import org.springboot.angular.demo.usermanagment.entity.UserProfile;
import org.springboot.angular.demo.usermanagment.security.CurrentUserService;

/**
 * Primitive implementation that returns hardcoded user.
 * 
 * @author mondhs
 *
 */
public class CurrentUserServiceStaticImpl implements CurrentUserService{

	UserProfile currentUser;
	
	public CurrentUserServiceStaticImpl() {
		currentUser = new UserProfile();
		currentUser.setLoginName("qa");
		currentUser.setFirstName("Qa");
		currentUser.setLastName("Qa");
		currentUser.setEmailAddress("qa@qa.lt");
	}
	
	@Override
	public UserProfile getCurrentUser() {
		return currentUser;
	}

}
