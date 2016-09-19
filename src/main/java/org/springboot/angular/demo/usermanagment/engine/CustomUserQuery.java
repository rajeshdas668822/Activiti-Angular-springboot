package org.springboot.angular.demo.usermanagment.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.springboot.angular.demo.usermanagment.entity.UserProfile;
import org.springboot.angular.demo.usermanagment.identity.CustomUser;
import org.springboot.angular.demo.usermanagment.security.UserDao;

/**
 * Simplified implementation of {@link UserQuery}. It is adapts results from {@link UserDao}.
 * Implementation is just as sample and it is not performance efficient. 
 * 
 * @author mondhs
 *
 */
public class CustomUserQuery implements UserQuery {
	
	private UserDao userDao;
	private UserProfile userProfile;
	

	public CustomUserQuery() {
		super();
	}
	

	public CustomUserQuery (UserProfile userProfile){
		this.userProfile = userProfile;
		
	}
	



	@Override
	public UserQuery asc() {
		throw new IllegalArgumentException("Not Impl");
	}

	@Override
	public UserQuery desc() {
		throw new IllegalArgumentException("Not Impl");
	}

	@Override
	public long count() {
		return userDao.findAll().size();
	}

	@Override
	public User singleResult() {		
		CustomUser user = new CustomUser(userProfile);
		return user;
	}

	@Override
	public List<User> list() {
		List<User> users = new ArrayList<User>();
		List<UserProfile> userProfiles = userDao.findAll();
		for(UserProfile userProfile :userProfiles){
			CustomUser user = new CustomUser(userProfile);
			users.add(user);
		}
		
		return users;
	}

	@Override
	public List<User> listPage(int firstResult, int maxResults) {
		List<User> users = new ArrayList<User>();
		Iterator<UserProfile> userProfiles = userDao.findAll().iterator();
		for (int i = 0; i < firstResult; i++) {
			userProfiles.next();
		}
		for (int i = 0; i < maxResults; i++) {
			if (!userProfiles.hasNext()) {
				break;
			}
			UserProfile userProfile = userProfiles.next();
			CustomUser user = new CustomUser(userProfile);
			users.add(user);
		}

		return users;
	}

	@Override
	public UserQuery userId(String id) {
		final UserProfile userProfile = userDao.findUserByLoginName(id);
		UserQuery aSubUserQuery = new CustomUserQuery(userProfile);
		return aSubUserQuery;
	}

	@Override
	public UserQuery userFirstName(String firstName) {
		final UserProfile userProfile = userDao.findUserFirstName(firstName);
		UserQuery aSubUserQuery = new CustomUserQuery(userProfile);
		return aSubUserQuery;
	}

	@Override
	public UserQuery userFirstNameLike(String firstNameLike) {
		final UserProfile userProfile = userDao.findUserFirstNameLike(firstNameLike);
		UserQuery aSubUserQuery = new CustomUserQuery(userProfile);
		return aSubUserQuery;
	}

	@Override
	public UserQuery userLastName(String lastName) {
		final UserProfile userProfile = userDao.findUserLastName(lastName);
		UserQuery aSubUserQuery = new CustomUserQuery(userProfile);
		return aSubUserQuery;
	}

	@Override
	public UserQuery userLastNameLike(String lastNameLike) {
		final UserProfile userProfile = userDao.findUserLastNameLike(lastNameLike);
		UserQuery aSubUserQuery = new CustomUserQuery(userProfile);
		return aSubUserQuery;
	}

	@Override
	public UserQuery userFullNameLike(String fullNameLike) {
		return null;
	}

	@Override
	public UserQuery userEmail(String email) {
		final UserProfile userProfile = userDao.findUserEmail(email);
		UserQuery aSubUserQuery = new CustomUserQuery(userProfile);
		return aSubUserQuery;
	}

	@Override
	public UserQuery userEmailLike(String emailLike) {
		throw new IllegalArgumentException("Not Impl");
	}

	@Override
	public UserQuery memberOfGroup(String groupId) {
		throw new IllegalArgumentException("Not Impl");
	}

	@Override
	public UserQuery potentialStarter(String procDefId) {
		throw new IllegalArgumentException("Not Impl");
	}

	@Override
	public UserQuery orderByUserId() {
		throw new IllegalArgumentException("Not Impl");
	}

	@Override
	public UserQuery orderByUserFirstName() {
		throw new IllegalArgumentException("Not Impl");
	}

	@Override
	public UserQuery orderByUserLastName() {
		throw new IllegalArgumentException("Not Impl");
	}

	@Override
	public UserQuery orderByUserEmail() {
		throw new IllegalArgumentException("Not Impl");
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
