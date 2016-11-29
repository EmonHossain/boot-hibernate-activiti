package com.csit.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EntryPointController {

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String showLandingPage(){
		return "index";
	}
	
	@RequestMapping("/home")
	public String showHome(){
		return "home";
	}
	
}
