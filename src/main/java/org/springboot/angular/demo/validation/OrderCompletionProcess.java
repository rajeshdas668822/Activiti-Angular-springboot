package org.springboot.angular.demo.validation;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class OrderCompletionProcess implements JavaDelegate {

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		
		System.out.println(" OrderCompletionProcess :: execute");
		

	}

}
