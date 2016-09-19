package org.springboot.angular.demo.usermanagment.security;

import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.springboot.angular.demo.usermanagment.engine.CustomGroupQuery;
import org.springboot.angular.demo.usermanagment.entity.UserGroup;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author rdas
 *
 */

public interface GroupDao {
	
	
	
	public List<UserGroup> listAll(CustomGroupQuery groupQuery);	
	public List<UserGroup> listPage(int firstResult, int maxResults); 
	
	public UserGroup findByGroupId(String groupId) ;

	
	public UserGroup findByGroupName(String groupName);
	
	
	public UserGroup findByGroupNameLike(String groupNameLike);

	public UserGroup findByGroupType(String groupType);

	
	public UserGroup findByGroupMember(String groupMemberUserId);

	
	public UserGroup potentialStarter(String procDefId) ;

	
	public UserGroup orderByGroupId() ;

	
	public UserGroup orderByGroupName() ;


	public UserGroup orderByGroupType() ;
	
	

}
