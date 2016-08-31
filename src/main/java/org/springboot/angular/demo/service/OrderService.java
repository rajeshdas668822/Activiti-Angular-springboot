package org.springboot.angular.demo.service;

import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

@Service(value="order")
public class OrderService {
	
	public void validate(DelegateExecution execution) {
		System.out.println("validating order for isbn " + execution.getVariable("isbn"));
	}

	
	public Date validateIsbn(Long isbn) {
		System.out.println("validating order for isbn " + isbn);
		 return new Date();
	}
}
