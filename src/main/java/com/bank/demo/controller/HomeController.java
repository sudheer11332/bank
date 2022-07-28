package com.bank.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bank.demo.service.HomeService;




@Controller

public class HomeController {
	
	@Autowired
	private HomeService service; // will autowire service instance
	
	@RequestMapping("/getlogin")
	public String getLogin() {
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest req, HttpSession session) {
				
		String uName = req.getParameter("uname"); // this will fetch the uname from jsp page
		String pwd = req.getParameter("pwd");
		
		session.setAttribute("uname", uName);
		boolean valid = service.isValid(uName, pwd);// call the service method
		if(valid)
			return "main";
		else {
			return "failure";
		}
	}
	
	
	@PostMapping("/dep")
	@ResponseBody
	public String deposit(HttpServletRequest req, HttpSession session) {

		String uname = session.getAttribute("uname").toString();
		int findBalance = service.findBalance(uname);
		Integer deposit = Integer.parseInt(req.getParameter("dep"));

		int newBalance = findBalance + deposit;

		service.updateBalance(newBalance, uname);

		return "Amount Deposited Successful";
	}
	
	@PostMapping("/withdrawl")
	@ResponseBody
	public String withdrawl(HttpServletRequest req, HttpSession session) {

		String uname = session.getAttribute("uname").toString();
		int findBalance = service.findBalance(uname);
		Integer withdrawl = Integer.parseInt(req.getParameter("wit"));

		int newBalance = findBalance -withdrawl;
		
		if(newBalance < 0)
			return "Please enter valid withdrawl amount, your current balance can be negative";
		
		
		service.updateBalance(newBalance, uname);

		return "Withdrawl Successful";
	}
	 
	@PostMapping("/check")
	@ResponseBody
	public String checkBalance(HttpServletRequest req, HttpSession session) {

		String uname = session.getAttribute("uname").toString();
		int currentBalance = service.findBalance(uname);
		
		

		return "Your Withdrawable Balance: "+currentBalance;
	}
}
