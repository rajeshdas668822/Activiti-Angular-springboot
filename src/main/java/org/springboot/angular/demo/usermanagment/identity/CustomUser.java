package org.springboot.angular.demo.usermanagment.identity;


import org.activiti.engine.identity.User;
import org.springboot.angular.demo.usermanagment.entity.UserProfile;

/**
 * Custom User adapter from {@link UserProfile} to {@link User} interface. 
 * @author rdas
 *
 */
public class CustomUser implements User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2649609221604480100L;
	private UserProfile userProfile;

	public CustomUser(UserProfile userProfile) {
		this.userProfile =  userProfile;
	}
	
	@Override
	public String getId() {
		return userProfile.getLoginName();
	}

	@Override
	public void setId(String id) {
	}

	@Override
	public String getFirstName() {
		return userProfile.getFirstName();
	}

	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLastName(String lastName) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getLastName() {
		return userProfile.getLastName();
	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getEmail() {
		return userProfile.getEmailAddress();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPassword(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isPictureSet() {
		return false;
	}

}
