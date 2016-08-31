package org.springboot.angular.demo.orderflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricFormProperty;
import org.activiti.engine.history.HistoricVariableUpdate;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springboot.angular.demo.domain.LoanApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.subethamail.wiser.Wiser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-activiti.cfg.xml")
public class LoanRequestProcessTest extends AbstractTest{
	
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


	@Value("classpath:loanrequest/loan-request.bpmn")
	Resource ressource;
	
	private Wiser wiser;
	
    @Before
    public void setup() {
        wiser = new Wiser();
        wiser.setPort(25);
        wiser.start();
    }

    @After
    public void cleanup() {
        wiser.stop();
    }
	
	
	
	@Test
	public void testLoanRequestRejectFlow() {
		Map<String, String> processVariable = new HashMap<String, String>();
		processVariable.put("name", "Rajesh");
		processVariable.put("emailAddress", "rdas@numerix.com");
		processVariable.put("loanAmount", "2000");
		processVariable.put("income", "6000");

		ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey("loanRequest").singleResult();
		assertNotNull(definition);

		formService.submitStartFormData(definition.getId(), processVariable);
		waitAndCompleteEvaluateLoanRequest("false");
		wiatAndCompleteProcessRequest("Close Request");
		List<HistoricDetail> historicDetails = historyService.createHistoricDetailQuery().variableUpdates()
				.orderByVariableName().asc().list();
		assertNotNull(historicDetails);

		assertEquals(7, historicDetails.size());
		HistoricVariableUpdate loanAppUpdate = ((HistoricVariableUpdate) historicDetails.get(4));
		assertEquals("loanApplication", loanAppUpdate.getVariableName());
		LoanApplication la = (LoanApplication) loanAppUpdate.getValue();
		assertEquals(true, la.isCreditCheck());
		
		
		
		//List<WiserMessage> mesages = wiser.getMessages();
		/*assertEquals(1, mesages.size());
		WiserMessage message = mesages.get(0);
		try {
			MimeMessage mimeMessage = message.getMimeMessage();
			assertEquals("Hello Rajesh", mimeMessage.getHeader("Subject",null));
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		

	}
	
	
	
	@Test
	public void testLoanRequestApproveFlow() {
		Map<String, String> processVariable = new HashMap<String, String>();
		processVariable.put("name", "Rajesh");
		processVariable.put("emailAddress", "rdas@numerix.com");
		processVariable.put("loanAmount", "2000");
		processVariable.put("income", "6000");

		ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey("loanRequest").singleResult();
		assertNotNull(definition);

		formService.submitStartFormData(definition.getId(), processVariable);
		waitAndCompleteEvaluateLoanRequest("true");
		wiatAndCompleteProcessRequest("Process Request");
		List<HistoricDetail> historicDetails = historyService.createHistoricDetailQuery().variableUpdates()
				.orderByVariableName().asc().list();
		assertNotNull(historicDetails);

		assertEquals(7, historicDetails.size());
		HistoricVariableUpdate loanAppUpdate = ((HistoricVariableUpdate) historicDetails.get(4));
		assertEquals("loanApplication", loanAppUpdate.getVariableName());
		LoanApplication la = (LoanApplication) loanAppUpdate.getValue();
		assertEquals(true, la.isCreditCheck());

	}
	
	
	private void wiatAndCompleteProcessRequest(String taskName){
		
		List<Task> taskList = taskService.createTaskQuery().taskCandidateGroup("sales").list();

		for (Task task : taskList) {
			System.out.println(" Task Name ::" + task.getName());
			//task.getProcessVariables().put("requestApproved", isApproved);
			
			Map<String, Object> variables = task.getProcessVariables();
			System.out.println("Task Local Variable ::" + variables);
			//variables.put("requestApproved", "true");
			assertEquals(taskName, task.getName());
			taskService.complete(task.getId());
		}
		
	}
	
	
	private void waitAndCompleteEvaluateLoanRequest(String isApproved) {
		List<Task> taskList = taskService.createTaskQuery().taskAssignee("fozzie").list();

		for (Task task : taskList) {
			System.out.println(" Task Name ::" + task.getName());
			task.getProcessVariables().put("requestApproved", "true");
			System.out.println("Task Local Variable ::" + task.getTaskLocalVariables());
			Map<String, Object> variables = task.getProcessVariables();
			variables.put("requestApproved", isApproved);
			taskService.setVariables(task.getId(), variables);
			taskService.complete(task.getId());
		}
	}
	
	@Test
	public void testFormSubmit() {

		ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey("loanRequest").singleResult();
		assertNotNull(definition);

		List<FormProperty> formProperties = formService.getStartFormData(definition.getId()).getFormProperties();
		assertEquals(4, formProperties.size());
		Map<String, String> processVariable = new HashMap<String, String>();
		processVariable.put("name", "Rajesh");
		processVariable.put("emailAddress", "rdas@numerix.com");
		processVariable.put("loanAmount", "2000");
		processVariable.put("income", "6000");

		formService.submitStartFormData(definition.getId(), processVariable);

		List<HistoricDetail> historicDetails = historyService.createHistoricDetailQuery().formProperties().list();
		assertNotNull(historicDetails);

		assertEquals(4, historicDetails.size());

		HistoricFormProperty formProperty = (HistoricFormProperty) historicDetails.get(0);
		assertNotNull(formProperty);
		assertEquals("loanAmount", formProperty.getPropertyId());
		assertEquals("2000", formProperty.getPropertyValue());

		formProperty = (HistoricFormProperty) historicDetails.get(1);
		assertNotNull(formProperty);
		assertEquals("income", formProperty.getPropertyId());
		assertEquals("6000", formProperty.getPropertyValue());

	}
	
	

}
