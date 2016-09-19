package org.springboot.angular.demo.entity;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springboot.angular.demo.usermanagment.engine.CustomGroupQuery;
import org.springboot.angular.demo.usermanagment.entity.UserGroup;
import org.springboot.angular.demo.usermanagment.security.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class CustomGroupEntityManagerTest {
	
	
	@Autowired
	GroupDao groupDao;
	
	@Test
	public void testUserGroup(){
		
		CustomGroupQuery customGroupQuery = new CustomGroupQuery();
		customGroupQuery.setUserId("kermit");
		List<UserGroup> userGroupList = groupDao.listAll(customGroupQuery);
		assertNotNull(userGroupList);
		assertEquals(2, userGroupList.size());
		
	}
	
	

}
