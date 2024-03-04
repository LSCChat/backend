package com.lscchat.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lscchat.model.CompanyMaster;
import com.lscchat.model.Message;
import com.lscchat.repository.CompanyMasterRepository;
import com.lscchat.repository.MessageRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://lsc-crm.in/", "https://f8bf-14-194-28-158.ngrok-free.app"}, allowCredentials = "true")
public class MessageController {
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private CompanyMasterRepository companyMasterRepository;
	
	@PostMapping("lscchat/v1.0/message")
	Message newMessage(@RequestBody Message newMessage, HttpSession session) {
		 Long companyId = (Long) session.getAttribute("company_id");
	        Long depId = (Long) session.getAttribute("dep_id");
	        Long unitId = (Long) session.getAttribute("unit_id");
	        if(companyId!=null) {
//	        	 Set session values to the new contact
	        	
	        	newMessage.setCompany_id(companyId);
	        	newMessage.setDep_id(depId);
	        	newMessage.setUnit_id(unitId);
		        return messageRepository.save(newMessage);
	        }
	        return null;
	}
	
	@PostMapping("lscchat/v1.0/webhookmeessage")
	Message newwebhookmeessage(@RequestBody Message newmessage) {
		String cNumber = newmessage.getReceive_id();
		String uNumber = newmessage.getSend_id();
		
		//Finding the company_id
		CompanyMaster companydetails = companyMasterRepository.findByMobileNumber(cNumber);
		Long companyId = companydetails.getCompanyId();
		
		//Finding the  dep_id and unit_id
		Optional<Message> optinalmessage = Optional.of(messageRepository.findDepAndUnit(companyId, cNumber, uNumber));
		if(optinalmessage.isPresent()) {
			Message message = optinalmessage.get();
			Long depId = message.getDep_id();
			Long unitId = message.getUnit_id();
			
			newmessage.setCompany_id(companyId);
			newmessage.setDep_id(depId);
			newmessage.setUnit_id(unitId);
			return messageRepository.save(newmessage);
		} else {
			newmessage.setCompany_id(companyId);
			return messageRepository.save(newmessage);
		}
	}
}
