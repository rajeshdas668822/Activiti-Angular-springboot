package org.springboot.angular.demo.usermanagment.security.impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.activiti.engine.identity.UserQuery;
import org.hibernate.Hibernate;
import org.springboot.angular.demo.usermanagment.engine.CustomUserQuery;
import org.springboot.angular.demo.usermanagment.entity.UserGroup;
import org.springboot.angular.demo.usermanagment.entity.UserGroupMapping;
import org.springboot.angular.demo.usermanagment.entity.UserProfile;
import org.springboot.angular.demo.usermanagment.security.UserDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


 /* Static DAO implementation that contains collection of system user in map
 * 
 * @author rdas
 *
 */

@Repository(value="userDao")
public class UserDaoImpl implements UserDao{

	 @PersistenceContext
     EntityManager entityManager;

	Map<String, UserProfile> userMap = new HashMap<String, UserProfile>();
	
	
	
	@Override
	public List<UserProfile> findAll() {
		return entityManager.createNamedQuery("user.findAll",UserProfile.class).getResultList();
	}

	@Override
	public UserProfile findUserByLoginName(String id) {
		return entityManager.createNamedQuery("user.findByName",UserProfile.class)
				.setParameter("name", id).getResultList().get(0);
	}

	@Override
	@Transactional(readOnly=true)
	public List<UserGroup> findGroupsByUser(String userId) {
		UserProfile userProfile = entityManager.createNamedQuery("findGroupByName", UserProfile.class)
				.setParameter("name", userId).getSingleResult();		
		Set<UserGroup> userGroups = userProfile.getUserGroups();
		return new ArrayList<>(userGroups);
	}

	@Override
	public UserProfile findUserFirstName(String firstName) {
		

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserProfile> q = cb.createQuery(UserProfile.class);
		Root o = q.from(UserProfile.class);
		q.select(o);
		q.where(cb.equal(o.get("firstName"), firstName));
		UserProfile userPofile = (UserProfile)this.entityManager.createQuery(q).getSingleResult();		
		return userPofile;
		
	}

	@Override
	public UserProfile findUserFirstNameLike(String firstNameLike) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserProfile> q = cb.createQuery(UserProfile.class);
		Root o = q.from(UserProfile.class);
		q.select(o);
		q.where(cb.like(o.get("firstName"), firstNameLike));
		UserProfile userPofile = (UserProfile)this.entityManager.createQuery(q).getSingleResult();		
		return userPofile;
	}

	@Override
	public UserProfile findUserLastName(String lastName) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserProfile> q = cb.createQuery(UserProfile.class);
		Root o = q.from(UserProfile.class);
		q.select(o);
		q.where(cb.like(o.get("lastName"), lastName));
		UserProfile userPofile = (UserProfile)this.entityManager.createQuery(q).getSingleResult();		
		return userPofile;
	}

	@Override
	public UserProfile findUserLastNameLike(String lastNameLike) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserProfile> q = cb.createQuery(UserProfile.class);
		Root o = q.from(UserProfile.class);
		q.select(o);
		q.where(cb.like(o.get("lastName"), lastNameLike));
		UserProfile userPofile = (UserProfile)this.entityManager.createQuery(q).getSingleResult();		
		return userPofile;
	}

	@Override
	public UserProfile findUserFullNameLike(String fullNameLike) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile findUserEmail(String email) {		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserProfile> q = cb.createQuery(UserProfile.class);
		Root o = q.from(UserProfile.class);
		q.select(o);
		q.where(cb.like(o.get("emailAddress"), email));
		UserProfile userPofile = (UserProfile)this.entityManager.createQuery(q).getSingleResult();		
		return userPofile;
	}

	@Override
	public UserProfile findMemberOfGroup(String groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile findPotentialStarter(String procDefId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile findOrderByUserId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile findOrderByUserFirstName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile findOrderByUserLastName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile findOrderByUserEmail() {
		// TODO Auto-generated method stub
		return null;
	}



}
