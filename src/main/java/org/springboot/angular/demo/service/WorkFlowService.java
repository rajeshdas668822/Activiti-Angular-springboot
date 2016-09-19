package org.springboot.angular.demo.service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springboot.angular.demo.domain.Order;
import org.springboot.angular.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("workFlowService")
public class WorkFlowService {
	@Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FormService formService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private HistoryService historyService;
    
    
    @Autowired
   private  IdentityService identityService;
    
    private static final String processName="ipdsOrder";
    
    
	public void initWorkFlow(Order order){
		
		
		Map<String, Object> processVariable = new HashMap<String, Object>();
        processVariable.put("productType", order.getProductType());
        processVariable.put("amount", order.getAmount());
        processVariable.put("quantity",order.getQuantity());
        processVariable.put("counterParty", order.getCounterParty());
        processVariable.put("costPrice", order.getCostPrice());
        processVariable.put("limitAmount", "10000");
        processVariable.put("isStandalone", order.isStandalone());

        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processName).latestVersion().singleResult();
       if(definition!=null){
    	 ProcessInstance processInstance =  runtimeService.startProcessInstanceById(definition.getId(), processVariable);
    	
    	 logProcessActivity(processInstance);
       }
		
		
	}


	public void processOrder(Order order, String actionType){
		//List<Task> taskList = taskService.createTaskQuery().taskAssignee("fozzie").list();		
		 List<Task> taskList = taskService.createTaskQuery().executionId(String.valueOf(order.getOrderId())).list();
		for (Task task : taskList) {
			System.out.println(" Task Name ::" + task.getName());
			//assertEquals("Init",task.getName());
			Map<String, Object> variables = taskService.getVariables(task.getId());
			System.out.println("Task Local Variable ::" + variables);
			Order storedOrder = (Order)variables.get("order");
			if(storedOrder!=null && storedOrder.getOrderId().equals(order.getOrderId())){
				order.setTaskId(task.getId());
			if(actionType.equals("Accept")){
					order.setStatus("Working");
				}else{
					order.setStatus(actionType);
				}
				variables.put("order",order);
				variables.put("actionType", actionType);
				taskService.complete(task.getId(),variables);
			}else {
				 System.out.println("No Action Reqire for this Order :"+order);
			}
		}

	}
	
	
	public void processFillOrder(Order order, String actionType) {
		List<Task> taskList = taskService.createTaskQuery().executionId(String.valueOf(order.getOrderId())).list();
		for (Task task : taskList) {
			System.out.println(" Task Name ::" + task.getName());
			Map<String, Object> variables = taskService.getVariables(task.getId());
			variables.put("filledAmount", order.getFillAmount());
			variables.put("pendingCancelAction",actionType);
			//order.setStatus("Fill");
			variables.put("order", order);
			taskService.complete(task.getId(), variables);
		}

	}
	

	
	public void assignOrder(String userId, Order order) {
		List<Task> taskList = taskService.createTaskQuery().executionId(String.valueOf(order.getOrderId())).list();
		for (Task task : taskList) {
			task.delegate(userId);
			System.out.println(" Task Name ::" + task.getName());
			Map<String, Object> variables = taskService.getVariables(task.getId());
			taskService.complete(task.getId(), variables);
			
		}
	}
	
	
	
	public void processCancelOrder(Order order, String actionType) {
		List<Task> taskList = taskService.createTaskQuery().executionId(String.valueOf(order.getOrderId())).list();
		for (Task task : taskList) {
			System.out.println(" Task Name ::" + task.getName());
			// assertEquals("Init",task.getName());
			Map<String, Object> variables = taskService.getVariables(task.getId());
			System.out.println("Task Local Variable ::" + variables);
			Order storedOrder = (Order) variables.get("order");
			if (storedOrder != null && storedOrder.getOrderId().equals(order.getOrderId())) {
				order.setTaskId(task.getId());
				order.setStatus(actionType);
				variables.put("order", order);
				variables.put("actionType", actionType);
				variables.put("previousAction", task.getName()); 
				taskService.complete(task.getId(), variables);
			} else {
				System.out.println("No Action Reqire for this Order :" + order);
			}
		}

	}

	public Map<String, List<Order>> loadTask(String userId) {
		Map<String, List<Order>> taskListMap = new HashMap<String, List<Order>>();
		List<Task> taskList = taskService.createTaskQuery().taskCandidateOrAssigned(userId).list();

		List<Group> groupList = identityService.createGroupQuery().groupMember(userId).list();
		
		if (groupList != null && !groupList.isEmpty()) {
			for (Group group : groupList) {
				if (group.getName() != null) {
					List<Task> grouptaskList = taskService.createTaskQuery().taskCandidateGroup(group.getName()).list();
					if (grouptaskList != null && !grouptaskList.isEmpty() ) {
						taskList.addAll(grouptaskList);
					}
				}
			}

		}
		List<Order> orderList = null;
		for (Task task : taskList) {
			Map<String, Object> variables = taskService.getVariables(task.getId());
			Order order = (Order) variables.get("order");
			if (order != null) {
				orderList = taskListMap.getOrDefault(task.getName(), new ArrayList<Order>());
				orderList.add(order);
				taskListMap.putIfAbsent(task.getName(), orderList);

			}
		}
		System.out.println(taskListMap);
		return taskListMap;
	}

	public void logProcessActivity(ProcessInstance processInstance){
		System.out.println(" Activity ID :" +processInstance.getActivityId());
		
		
	}
	
	
	public boolean isOrderEqual(Order passOrder, Order storedOrder){
		boolean orderEqual = false;
		if(passOrder == null || storedOrder == null){
			orderEqual = false;
		}
		
		//if(passOrder.getCounterParty())
		
		return orderEqual;
	}
	

}
