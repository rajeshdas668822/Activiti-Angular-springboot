package org.springboot.angular.demo.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class CreditCheckService implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
	  boolean approved = false;
		String customerID = (String) execution.getVariable("customerID");
		int intCustomer = Integer.parseInt(customerID);
		if(intCustomer > 1000 && intCustomer < 10000) {
		  approved = true;
		}
		execution.setVariable("creditCheckApproved", approved);
	}

}
