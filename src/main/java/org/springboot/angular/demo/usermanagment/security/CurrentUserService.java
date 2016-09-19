package org.springboot.angular.demo.usermanagment.security;


import org.springboot.angular.demo.usermanagment.entity.UserProfile;


public interface CurrentUserService {

	UserProfile getCurrentUser();

}
