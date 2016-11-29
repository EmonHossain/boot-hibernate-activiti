package com.csit.project.users;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class RegController {
	
	@Autowired
	private UserService userSerevice;
	
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public String showRegPage(){
		return "registration";
	}
	
	@RequestMapping(value="/signup" , method = RequestMethod.POST)
	public String getEntry(Model model ,@Valid UserEntity userEntity,BindingResult result){
		
		if(!result.hasErrors()){
			
			userSerevice.doRegistration(userEntity);
			
		}else{
			return "registration";
		}
		
		return "home";
	}

}
