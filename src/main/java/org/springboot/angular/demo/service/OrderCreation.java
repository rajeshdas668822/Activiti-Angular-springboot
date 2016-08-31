package org.springboot.angular.demo.service;

import java.util.Random;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springboot.angular.demo.domain.Order;



public class OrderCreation implements JavaDelegate {
	
	Random random = new Random(100);

	public enum Standalone{

		YES("Yes", true),
		NO("No",false);

		public String getOption() {
			return option;
		}

		public boolean isValue() {
			return value;
		}

		private String option;
		private boolean value;

		Standalone(String option, boolean value){
			 this.option = option;
			 this.value  = value;
		}

		/*public boolean findByValue(String value){
			Standalone [] standalones = Standalone.values();
			if(Standalone stdAlone:standalones){

			}

		}*/


	}

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Order order = new Order();
		
		order.setAmount(Double.valueOf(execution.getVariable("amount").toString()));
		order.setCostPrice(Double.valueOf(execution.getVariable("costPrice").toString()));
		order.setCounterParty(execution.getVariable("counterParty").toString());
		order.setProductType(execution.getVariable("productType").toString());
		order.setQuantity(Double.valueOf(execution.getVariable("quantity").toString()));
        if("Yes".equals(execution.getVariable("isStandalone").toString())) {
		   order.setStandalone(Standalone.YES.isValue());
		}
		order.setStandalone(true);
        order.setStatus("Init");
		execution.setVariable("order", order);
		order.setOrderId(Long.valueOf(execution.getProcessInstanceId()));		
		System.out.println("Order Created : Order ID:="+order.getOrderId());
		
		System.out.println(" getParentId :"+execution.getParentId());
		System.out.println(" getId() :"+execution.getId());
		
		
		
	}

}
