package org.springboot.angular.demo.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springboot.angular.demo.domain.LoanApplication;
import org.springframework.stereotype.Service;



@Service(value="loanApplication")
public class CreateApplicationTask implements JavaDelegate {
	
	public void createLoanApplication(DelegateExecution execution){
		LoanApplication loanApplication = new LoanApplication();		
		loanApplication.setCreditCheck(Boolean.valueOf(execution.getVariable("creditCheckOk").toString()));
		loanApplication.setIncome(Long.valueOf(execution.getVariable("income").toString()));
		loanApplication.setEmail(execution.getVariable("emailAddress").toString());
		loanApplication.setLoanAmount(Long.valueOf(execution.getVariable("loanAmount").toString()));
		execution.setVariable("loanApplication", loanApplication);
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		LoanApplication loanApplication = new LoanApplication();		
		loanApplication.setCreditCheck(Boolean.valueOf(execution.getVariable("creditCheckOk").toString()));
		loanApplication.setIncome(Long.valueOf(execution.getVariable("income").toString()));
		loanApplication.setEmail(execution.getVariable("emailAddress").toString());
		loanApplication.setLoanAmount(Long.valueOf(execution.getVariable("loanAmount").toString()));
		execution.setVariable("loanApplication", loanApplication);
		
	}

}
