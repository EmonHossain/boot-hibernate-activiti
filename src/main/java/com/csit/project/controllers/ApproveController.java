package com.csit.project.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csit.project.LoanStatus;
import com.csit.project.enetities.LoanInfo;
import com.csit.project.loanServices.LoanInfoServices;
import com.csit.project.services.ActivitiServices;

@Controller
public class ApproveController {

	@Autowired
	private TaskService taskService;

	/*@Autowired
	private IdentityService identityService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;*/

	@Autowired
	private ActivitiServices activitiServices;

	@Autowired
	private LoanInfoServices loanInfoServices;

	/*
	 * @RequestMapping("/approverA") public String showApvrA(Model model) {
	 * System.out.println("hello there"); List<Task> tasks =
	 * getApprovertask("apvrA"); model.addAttribute("tasks", tasks);
	 * 
	 * return "apvrA"; }
	 */

	@RequestMapping(value = "/approvedA/{taskId}", method = RequestMethod.GET)
	public String taskApprovedForApvrA(Model model, @PathVariable String taskId) {
		boolean approvedTaskA = true;
		try {
			taskService.complete(taskId, Collections.singletonMap("approvedTaskA", approvedTaskA));
			System.out.println("Task for A is approved....!!!");
			LoanInfo loanInfo = loanInfoServices.getClientLoanInfoByTaskId(taskId);

			loanInfoServices.deleteSingleLoanInfo(loanInfo);

			/*
			 * loanInfo.setApproved(true);
			 * loanInfo.setCurrentStatus(LoanStatus.APPROVED.name());
			 * loanInfo.setApprovedBy("apvrA");
			 */

			loanInfoServices.updateClientLoanInfo(loanInfo);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * List<Task> tasks = getApprovertask("apvrC");
		 * model.addAttribute("tasks", tasks);
		 */

		return "redirect:/approverA";
	}

	@RequestMapping(value = "/approverB/{taskId}", method = RequestMethod.GET)
	public String delegateToApvrB(Model model, @PathVariable String taskId) {

		// boolean reviewRequestToB = true;

		try {

			// taskService.complete(taskId,
			// Collections.singletonMap("reviewRequestToB", reviewRequestToB));
			taskService.delegateTask(taskId, "apvrB");
			LoanInfo loanInfo = loanInfoServices.getClientLoanInfoByTaskId(taskId);
			loanInfo.setRequestedTo("apvrB");
			loanInfo.setCurrentStatus(LoanStatus.LEVEL_ONE.name());
			loanInfoServices.updateClientLoanInfo(loanInfo);

			System.out.println("Task delegated from Approver A to Approver B");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * List<Task> tasks = getApprovertask("apvrA");
		 * model.addAttribute("tasks", tasks);
		 */

		return "redirect:/approverA";
	}

	@RequestMapping("/approverB")
	public String showApvrB(Model model) {

		// User approver =
		// identityService.createUserQuery().userId("apvrB").singleResult();
		List<Task> tasks = getApprovertask("apvrB");

		List<LoanInfo> loanInfoList = new ArrayList<>();

		for (Task task : tasks) {
			loanInfoList.add(loanInfoServices.getClientLoanInfoByTaskId(task.getId()));
		}

		model.addAttribute("loanInfoList", loanInfoList);

		return "apvrB";
	}

	@RequestMapping(value = "/approvedB/{taskId}", method = RequestMethod.GET)
	public String taskApprovedForApvrB(Model model, @PathVariable String taskId) {
		// boolean approvedTaskB = true;
		try {
			// taskService.complete(taskId,
			// Collections.singletonMap("approvedTaskB", approvedTaskB));
			taskService.resolveTask(taskId);
			LoanInfo loanInfo = loanInfoServices.getClientLoanInfoByTaskId(taskId);
			loanInfo.setResolvedBy("apvrB");
			loanInfo.setCurrentStatus(LoanStatus.RESOLVED.name());
			loanInfoServices.updateClientLoanInfo(loanInfo);

			System.out.println("Task for B is approved....!!!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * List<Task> tasks = getApprovertask("apvrC");
		 * model.addAttribute("tasks", tasks);
		 */

		return "redirect:/approverB";
	}

	@RequestMapping(value = "/approverC/{taskId}", method = RequestMethod.GET)
	public String delegateToApvrC(Model model, @PathVariable String taskId) {

		try {
			taskService.delegateTask(taskId, "apvrC");

			LoanInfo loanInfo = loanInfoServices.getClientLoanInfoByTaskId(taskId);
			loanInfo.setRequestedTo("apvrC");
			loanInfo.setCurrentStatus(LoanStatus.LEVEL_TWO.name());
			loanInfoServices.updateClientLoanInfo(loanInfo);

			System.out.println("Task delegated from Approver B to Approver C");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * List<Task> tasks = getApprovertask("apvrC");
		 * model.addAttribute("tasks", tasks);
		 */

		return "redirect:/approverB";
	}

	@RequestMapping("/approverC")
	public String showApvrC(Model model) {

		// User approver =
		// identityService.createUserQuery().userId("apvrA").singleResult();
		List<Task> tasks = getApprovertask("apvrC");

		List<LoanInfo> loanInfoList = new ArrayList<>();
		

		List<LoanInfo> reqLoan = loanInfoServices.getClientInfoByRequestedTo("apvrC");

		for (Task task : tasks) {
						
			loanInfoList.add(loanInfoServices.getClientLoanInfoByTaskId(task.getId()));
		}

		model.addAttribute("loanInfoList", loanInfoList);

		model.addAttribute("requestedInfo", reqLoan);

		return "apvrC";
	}

	@RequestMapping(value = "/approvedC/{taskId}", method = RequestMethod.GET)
	public String taskApprovedForApvrC(Model model, @PathVariable String taskId) {
		//boolean approvedTaskC = true;
		try {
			// taskService.complete(taskId,
			// Collections.singletonMap("approvedTaskC", approvedTaskC));
			taskService.resolveTask(taskId);
			
			LoanInfo loanInfo = loanInfoServices.getClientLoanInfoByTaskId(taskId);
			loanInfo.setResolvedBy("apvrC");
			loanInfo.setCurrentStatus(LoanStatus.RESOLVED.name());
			loanInfoServices.updateClientLoanInfo(loanInfo);

			System.out.println("Task Approved by C");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * List<Task> tasks = getApprovertask("apvrC");
		 * model.addAttribute("tasks", tasks);
		 */

		return "redirect:/approverC";
	}

	@RequestMapping("/approverA")
	public String showApvrA(Model model) {

		System.out.println("hello there");
		List<LoanInfo> listOfLoan = loanInfoServices.getUnresolvebLoansByAssignee("apvrA");
		List<LoanInfo> resolvedLoan = loanInfoServices.getAllResolvedLoan("apvrA");
		model.addAttribute("loans", listOfLoan);
		model.addAttribute("resolvedLoan", resolvedLoan);

		return "apvrA";
	}

	@Transactional
	private List<Task> getApprovertask(String apvrName) {
		return activitiServices.getTasks(apvrName);
	}
}
