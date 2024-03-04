package com.lscchat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lscchat.model.CompanyMaster;
import com.lscchat.repository.CompanyMasterRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://lsc-crm.in/", "https://f8bf-14-194-28-158.ngrok-free.app"}, allowCredentials = "true")
@RequestMapping(path="lscchat/v1.0")
public class CompanyMasterController {
	
	@Autowired
	private CompanyMasterRepository companyMasterRepository;
	
	
	@PostMapping("/companymaster")
	public CompanyMaster newcompanymaster(@RequestBody CompanyMaster companydetails) {
		return companyMasterRepository.save(companydetails);
	}
}
