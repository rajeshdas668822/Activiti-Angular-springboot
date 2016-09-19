package org.springboot.angular.demo.usermanagment.entitymanager;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.springboot.angular.demo.usermanagment.security.GroupDao;
import org.springboot.angular.demo.usermanagment.security.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;

@Service
public class CustomGroupEntityManagerFactory implements SessionFactory{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private GroupDao groupDao;

    @Override
    public Class<?> getSessionType() {
        
        return GroupIdentityManager.class;
    }

    @Override
    public Session openSession() {
    	
    	CustomGroupEntityManager groupEntityManager = new CustomGroupEntityManager();
    	groupEntityManager.setGroupDao(groupDao);
    	groupEntityManager.setUserDao(userDao);        
        return groupEntityManager;
    }

}
