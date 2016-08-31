package org.springboot.angular.demo.domain;

import java.io.Serializable;

public class LoanApplication implements Serializable {
	
	private String name;
	private String email;
	private Long loanAmount;
	private Long income;
	private boolean creditCheck;
	public boolean isCreditCheck() {
		return creditCheck;
	}
	public void setCreditCheck(boolean creditCheck) {
		this.creditCheck = creditCheck;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Long loanAmount) {
		this.loanAmount = loanAmount;
	}
	public Long getIncome() {
		return income;
	}
	public void setIncome(Long income) {
		this.income = income;
	}

}
