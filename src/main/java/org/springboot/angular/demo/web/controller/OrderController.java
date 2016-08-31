package org.springboot.angular.demo.web.controller;

import java.util.List;
import java.util.Map;

import org.springboot.angular.demo.domain.Order;
import org.springboot.angular.demo.domain.User;
import org.springboot.angular.demo.service.WorkFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {
	
	public enum Action{
		Reject,Accept,Cancel
	}
	
	@Autowired
	WorkFlowService workFlowService;
	
	 @RequestMapping(value="/submitOrder",method = RequestMethod.POST)
	 @ResponseBody
	 public void submit(@RequestBody Order order){
		 System.out.println("Hi");
		 if(order!=null){
			 order.setStatus("init");
		     workFlowService.initWorkFlow(order);
		 }
		
	}
	 
	 @RequestMapping(value="/loadTask",method = RequestMethod.POST)
	 @ResponseBody
	 public Map<String, List<Order>> loadTaskUser(@RequestBody User user){
		 return workFlowService.loadTask(user.getUserId());
		 
	 }
	 
	 
	 @RequestMapping(value="/approveOrder",method = RequestMethod.POST)
	 @ResponseBody
	 public boolean approveOrder(@RequestBody Order order){
		 System.out.println("Inside Approve"+order);
		 workFlowService.processOrder(order,"Accept");
		 return true;
	 }
	 
	 
	 @RequestMapping(value="/rejectOrder",method = RequestMethod.POST)
	 @ResponseBody
     public boolean rejectOrder(@RequestBody Order order){
		 
		 System.out.println("Inside Reject"+order);
		 workFlowService.processOrder(order,"Reject");
		 
		 return true;
	 }
    
    
	 @RequestMapping(value="/cancelOrder",method = RequestMethod.POST)
	 @ResponseBody
     public boolean cancelOrder(@RequestBody Order order){
		 
		 System.out.println("Inside cancelOrder"+order);		 
		 workFlowService.processCancelOrder(order,"Cancel");
		 
		 return true;
	 }
	 
	 
	@RequestMapping(value = "/fillOrder", method = RequestMethod.POST)
	@ResponseBody
	public boolean fillOrder(@RequestBody Order order) {
		System.out.println("Inside cancelOrder" + order.getFillAmount());
		workFlowService.processFillOrder(order);
		return true;
	}
	
	
	
	 
	
	 
	 
	 @RequestMapping(value="/assignOrder",method = RequestMethod.POST)
	 @ResponseBody
     public boolean assignOrder(@RequestBody String userId){
		 
		 System.out.println("Inside Assign"+userId);
		 
		 return true;
	 }

}
