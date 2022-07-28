package com.bank.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.demo.model.User;
import com.bank.demo.repository.HomeRepository;


@Service
public class HomeService {
	
	@Autowired
	HomeRepository repo;
	
	public boolean isValid(String uname, String pwd) {
		System.out.println("from page: "+uname+":"+pwd);
		List<User> users = repo.findAll();
		boolean valid = false;
		for(User usr:users) {
			System.out.println(usr.getUserName()+":"+usr.getPassword());
			if(uname.equals(usr.getUserName()) && pwd.equals(usr.getPassword())){
				valid = true;
			}
			
		}
		
		return valid;
		
	}
	
	
	public int findBalance(String uname) {
		int findBalanceByUserName = repo.findBalanceByUserName(uname);
		return findBalanceByUserName;
	}
	
	public void updateBalance(int balance, String uname) {
		repo.save(balance,uname);
	}

}
