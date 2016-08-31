package org.springboot.angular.demo.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springboot.angular.demo.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	public Map<String,User> existingUsers = new HashMap<String, User>();
	
	@PostConstruct
	public void initUserDetails(){
		
		User user = new User();
		user.setUserId("kermit");
		user.setPassword("kermit");
		//user.setUserName(userName);
		existingUsers.put(user.getUserId(),user);
		
		user = new User();
		user.setUserId("fozzie");
		user.setPassword("fozzie");
		existingUsers.put(user.getUserId(),user);
		
		
		
		
	}
	
   @RequestMapping(value="/validateUser",method = RequestMethod.POST)
   @ResponseBody
   public  boolean verifyUser(@RequestBody User user){
		boolean validUser=false;
		User storedUser = existingUsers.get(user.getUserId());
		if(storedUser.getPassword().equals(user.getPassword())){
			validUser = true;
		}
		 
		System.out.println("Valid User : "+validUser);
		return validUser;
		 
	 }
   
   
   
   @RequestMapping(value="/users",method = RequestMethod.GET)
   @ResponseBody
   public  Collection<User> lodUser(){		
	   return existingUsers.values();
	 }

}
