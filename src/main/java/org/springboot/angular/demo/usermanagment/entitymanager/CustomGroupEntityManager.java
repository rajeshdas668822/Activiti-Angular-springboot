package org.springboot.angular.demo.usermanagment.entitymanager;

import java.util.List;
import java.util.stream.Collectors;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springboot.angular.demo.usermanagment.engine.CustomGroupQuery;
import org.springboot.angular.demo.usermanagment.entity.UserGroup;
import org.springboot.angular.demo.usermanagment.identity.CustomGroup;
import org.springboot.angular.demo.usermanagment.security.GroupDao;
import org.springboot.angular.demo.usermanagment.security.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomGroupEntityManager  extends GroupEntityManager{

	private GroupDao groupDao;	
	
	private UserDao userDao;

	public GroupDao getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}
	
	
	
	@Override
	public List<Group> findGroupsByUser(String userId) {	
	 List<UserGroup> userGroups =	userDao.findGroupsByUser(userId);
	 return userGroups.stream().map(userGrp ->{
		 return new CustomGroup(userGrp.getGroupId(), userGrp.getType());
	 }).collect(Collectors.toList());
		 
	}
	
	
	@Override
	public GroupQuery createNewGroupQuery() {
		CustomGroupQuery customGroupQuery =	new CustomGroupQuery();
		customGroupQuery.setGroupDao(groupDao);
		return customGroupQuery;
	}
	
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	

}
