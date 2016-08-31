package org.springboot.angular.demo.validation;

import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

public class ValidateServiceWithField implements JavaDelegate {
	private Expression validateText;
	private Expression isbn;

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		
		System.out.println(" Process Id :"+delegateExecution.getId());
		System.out.println("Recieved ISBN"+isbn.getValue(delegateExecution));
		delegateExecution.setVariable("validdatetime", new Date());
		System.out.println(validateText.getValue(delegateExecution).toString()+delegateExecution.getVariable("validdatetime"));		
		
	}

}
