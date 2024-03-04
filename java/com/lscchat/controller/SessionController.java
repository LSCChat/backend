package com.lscchat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lscchat.model.SessionValues;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://lsc-crm.in/", "https://f8bf-14-194-28-158.ngrok-free.app"}, allowCredentials = "true")
public class SessionController {
	@Autowired
	SessionValues sessionValues;
	
	@PostMapping("lscchat/v1.0/sessionvalues")
	SessionValues getSession(HttpSession session) {
		Long S_user_id = (Long) session.getAttribute("userId");
		String s_fullname = (String) session.getAttribute("fullname");
		String s_email = (String) session.getAttribute("email");
		Long s_company_id = (Long) session.getAttribute("company_id");
		Long s_dep_id = (Long) session.getAttribute("dep_id");
		Long s_unit_id = (Long) session.getAttribute("unit_id");
		Long S_user_role = (Long) session.getAttribute("userRole");
		Long S_campaign_limit = (Long) session.getAttribute("campaignLimit");
		
		return new SessionValues(S_user_id, s_fullname, s_email, s_company_id, s_dep_id, s_unit_id, S_user_role, S_campaign_limit);
	}
}
