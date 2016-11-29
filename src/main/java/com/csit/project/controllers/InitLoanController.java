package com.csit.project.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.csit.project.LoanStatus;
import com.csit.project.enetities.LoanInfo;
import com.csit.project.loanServices.LoanInfoServices;
import com.csit.project.services.ActivitiServices;

@Controller
public class InitLoanController {

	@Autowired
	private ActivitiServices activitiServices;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private LoanInfoServices loanInfoServices;

	@RequestMapping(value = "/loan", method = RequestMethod.GET)
	public String showLoanInitForm() {
		return "loanForm";
	}

	@RequestMapping(value = "/loan", method = RequestMethod.POST)
	public String getDataFromLoanInitForm(Model model, @RequestParam(name = "accNo") String accNo,
			@RequestParam(name = "accNo") String amount, BindingResult result) {

		String clientAccNo = null;
		int loanAmount;

		if (!result.hasErrors()) {
			try {
				clientAccNo = accNo;
				loanAmount = Integer.parseInt(amount);
				User person = (User) identityService.createUserQuery().userId("apvrA").singleResult();
				Map<String, Object> variable = new HashMap<String, Object>();
				ProcessInstance processInstance = activitiServices.startProcess(variable);
				Task startedTaskInfo = taskService.createTaskQuery().processInstanceId(processInstance.getId())
						.singleResult();

				// LoanInfo loanInfo = new LoanInfo(accNo, loanAmount,
				// person.getId(), startedTaskInfo.getId(), null, new Date(),
				// null, false, "initialized");

				loanInfoServices.saveClientLoanInfo(new LoanInfo(clientAccNo, loanAmount, person.getId(),
						startedTaskInfo.getId(), null, new Date(), null, false, LoanStatus.LEVEL_ZERO.name(),null));

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		model.addAttribute("greetings", "Loan request Accepted");
		return "loanForm";
	}

}
