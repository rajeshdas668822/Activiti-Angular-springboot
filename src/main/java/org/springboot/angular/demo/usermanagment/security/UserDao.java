package org.springboot.angular.demo.usermanagment.security;

import java.util.List;

import org.activiti.engine.identity.UserQuery;
import org.springboot.angular.demo.usermanagment.entity.UserGroup;
import org.springboot.angular.demo.usermanagment.entity.UserProfile;
import org.springboot.angular.demo.usermanagment.entitymanager.CustomUserEntityManager;

/**
 * Simplified representation of custom User management DAO service.
 * It is called by {@link CustomUserEntityManager}.
 * @author rdas
 *
 */
public interface UserDao {

	List<UserProfile> findAll();

	UserProfile findUserByLoginName(String id);
	
	List<UserGroup> findGroupsByUser(String userId);
	UserProfile findUserFirstName(String firstName) ;
	UserProfile findUserFirstNameLike(String firstNameLike);
	UserProfile findUserLastName(String lastName);
	UserProfile findUserLastNameLike(String lastNameLike) ;
	UserProfile findUserFullNameLike(String fullNameLike);
	UserProfile findUserEmail(String email);
	UserProfile findMemberOfGroup(String groupId) ;
	UserProfile findPotentialStarter(String procDefId);
	UserProfile findOrderByUserId();
	UserProfile findOrderByUserFirstName();
	UserProfile findOrderByUserLastName() ;
	UserProfile findOrderByUserEmail();


}
