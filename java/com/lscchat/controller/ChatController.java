package com.lscchat.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lscchat.model.ChatMessage;
import com.lscchat.model.ChatResponse;
import com.lscchat.model.CompanyMaster;
import com.lscchat.model.Contacts;
import com.lscchat.model.Message;
import com.lscchat.model.Status;
import com.lscchat.repository.CompanyMasterRepository;
import com.lscchat.repository.MessageRepository;
import com.lscchat.repository.StatusRepository;
import com.lscchat.service.ChatContact;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://lsc-crm.in/"}, allowCredentials = "true")
@RequestMapping(path="lscchat/v1.0")
public class ChatController {
	
	private Timestamp create_date = new Timestamp(System.currentTimeMillis());
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	private ChatContact chatContact;
	
	@Autowired
	StatusRepository statusRepository;
	
	//:-1> Web Socket method
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	//This is group message(Chat room)
	@MessageMapping("/message") // /app/message -> this sent by the CRM user & Customer
	@SendTo("/chatroom/public")
	public ChatMessage receiveMessage(@Payload ChatMessage message) {
		return message;
	}
	
	//This is private message(individual)
	@MessageMapping("/private-message")
	public ChatMessage recMessage(@Payload ChatMessage message) {
		System.out.println(message.toString());
		String cNumber = message.getSenderId();
		String uNumber = message.getReciverId();
		
		//Finding the company_id
		Long companyId;
		Long depId = 0L;
		Long unitId = 0L;
		
		Boolean comeFrom = true; // true:-> it will came from (lsc-crm) || false it will came from (WebHook)
		CompanyMaster companydetails = companyMasterRepository.findByMobileNumber(message.getSenderId());
		if (companydetails != null) {
			companyId = companydetails.getCompanyId();
		}else {
			CompanyMaster companydetails2 = companyMasterRepository.findByMobileNumber(message.getReciverId());
			if (companydetails2 != null) {
				comeFrom = false;
				companyId = companydetails2.getCompanyId();
			}else {
				companyId = 0L;
				
			}
		}
		
		Message newmessage = new Message();
		
		//Finding the  dep_id and unit_id
		Optional<Message> optinalmessage = Optional.of(messageRepository.findDepAndUnit(companyId, cNumber, uNumber));
		if(optinalmessage.isPresent()) {
			Message messages = optinalmessage.get();
			depId = messages.getDep_id();
			unitId = messages.getUnit_id();
			
			newmessage.setCompany_id(companyId);
			newmessage.setDep_id(depId);
			newmessage.setUnit_id(unitId);
			newmessage.setSend_id(message.getSenderId());
			newmessage.setReceive_id(message.getReciverId());
			newmessage.setMessage_id(message.getMessageId());
			newmessage.setMessage_type(message.getMessageType());
			newmessage.setMessage(message.getMessage());
			messageRepository.save(newmessage);
		} else {
			newmessage.setCompany_id(companyId);
			messageRepository.save(newmessage);
		}
		
		//Finding the company mobile number
		CompanyMaster companydetails3 = companyMasterRepository.findMobileNumber(companyId);
		String mobileNo = companydetails3.getMobileNo();
		
		//Finding Customer mobile number
//		String customerMobileNumber;
//		if(cNumber.equals(mobileNo)) {
//			customerMobileNumber = uNumber;
//		} else {
//			customerMobileNumber = cNumber;
//		}
				
		String contactId = companyId+""+depId+""+unitId;
		System.out.println(contactId);
		if(comeFrom) {
			//Message from CRM
			
//			System.out.println("if:-----------");
			simpMessagingTemplate.convertAndSendToUser(message.getSenderId(), "/private", message);
			simpMessagingTemplate.convertAndSendToUser(contactId, "/private", chatContact.findChatContactService(companyId, depId, unitId, mobileNo));
			return message;
		} else {
			//Message from web hook
			//INSERT sent message
			Status update = new Status();
			update.setSend(1L);
			update.setMessageId(message.getMessageId());
			update.setSend_date(create_date);
			statusRepository.save(update);
			message.setType(2L);
			
//			System.out.println("else:-----------");
			// /user/919626974940/private -> when CRM user want to receive message from 919626974940 they need to listen to this URL
			simpMessagingTemplate.convertAndSendToUser(message.getSenderId(), "/private", message);	

			simpMessagingTemplate.convertAndSendToUser(contactId, "/private", chatContact.findChatContactService(companyId, depId, unitId, mobileNo));
			return message;
		}
	}
	
	//This is private message(individual)
//		@MessageMapping("/private-message")
//		public ChatMessage recMessages(@Payload ChatMessage message) {
//			simpMessagingTemplate.convertAndSendToUser(message.getSenderId(), "/private", message);
//			
//			return message;
//			
//		}
	
	//:-2> 5 Second refresh method
	@PostMapping("/chatcontact")
	public List<Map<String, String>> chatcontact(HttpSession session) {
		Long companyId = (Long) session.getAttribute("company_id");
		Long depId = (Long) session.getAttribute("dep_id");
		Long unitId = (Long) session.getAttribute("unit_id");
		//Finding the company mobile number
		CompanyMaster companydetails = companyMasterRepository.findMobileNumber(companyId);
		String mobileNo = companydetails.getMobileNo();
		
		return chatContact.findChatContactService(companyId, depId, unitId, mobileNo);
	}
	
	@PostMapping("/chat")
	public List<ChatResponse> message(@RequestBody CompanyMaster userMobilNo, HttpSession session) {
		Long companyId = (Long) session.getAttribute("company_id");
		Long depId = (Long) session.getAttribute("dep_id");
		Long unitId = (Long) session.getAttribute("unit_id");
		String userMobile = userMobilNo.getMobileNo();
		CompanyMaster companydetails = companyMasterRepository.findMobileNumber(companyId);
		String companymobile = companydetails.getMobileNo();
		
		List<Message> message = messageRepository.findChat(companyId, depId, unitId, userMobile);
		
		List<ChatResponse> chatResponses = new ArrayList<>();
		
		if (message != null) {
		    
		    for (Message msg : message) {
		        
		    	ChatResponse chatResponse = new ChatResponse();
		    	
		    	chatResponse.setMessageId(msg.getId());
		    	//Check the type //1->you 2->User
		    	if(msg.getSend_id().equals(companymobile)) {
		    		chatResponse.setType((long) 1);
		    	}else {
		    		chatResponse.setType((long) 2);
		    	}
		    	chatResponse.setMessageType(msg.getMessage_type());
		    	chatResponse.setMessage(msg.getMessage());
		    	chatResponse.setDateTime(msg.getCreate_date());
		    	
		    	chatResponses.add(chatResponse);
		    }
		}
		return chatResponses;
	}
}
