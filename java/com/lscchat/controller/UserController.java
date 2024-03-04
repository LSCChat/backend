package com.lscchat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lscchat.model.SessionValues;
import com.lscchat.model.User;
import com.lscchat.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://lsc-crm.in/", "https://f8bf-14-194-28-158.ngrok-free.app"}, allowCredentials = "true")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("lscchat/v1.0/user")
	User newUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}
	
	@GetMapping("lscchat/v1.0/userdetails")
	List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@PostMapping("lscchat/v1.0/login")
	SessionValues login(@RequestBody User loginRequest, HttpSession session) {
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		User user = userRepository.findByEmail(email);
		if(user != null && user.getPassword().equals(password) && user.getFlag() == 0) {
			session.setAttribute("userId", user.getId());
			session.setAttribute("fullname", user.getFullname());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("company_id", user.getCompany_id());
			session.setAttribute("dep_id", user.getDep_id());
			session.setAttribute("unit_id", user.getUnit_id());
			session.setAttribute("userRole", user.getUserRole());
			session.setAttribute("campaignLimit", user.getCampaignLimit());
			
			Long s_user_id = (Long) session.getAttribute("userId");
			String s_fullname = (String) session.getAttribute("fullname");
			String s_email = (String) session.getAttribute("email");
			Long s_company_id = (Long) session.getAttribute("company_id");
			Long s_dep_id = (Long) session.getAttribute("dep_id");
			Long s_unit_id = (Long) session.getAttribute("unit_id");
			Long S_user_role = (Long) session.getAttribute("userRole");
			Long S_campaign_limit = (Long) session.getAttribute("campaignLimit");
			
			return new SessionValues(s_user_id, s_fullname, s_email, s_company_id, s_dep_id, s_unit_id, S_user_role, S_campaign_limit);
		}
		return new SessionValues();
	}
	
	@PostMapping("lscchat/v1.0/logout")
	String logout(HttpSession session) {
		if (session != null) {
            session.invalidate(); // Invalidate the session
        }
		return "success";
	}
}
