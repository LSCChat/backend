package com.lscchat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lscchat.model.CustomSendListResponse;
import com.lscchat.model.SendList;
import com.lscchat.repository.MessageRepository;
import com.lscchat.repository.SendListRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://lsc-crm.in/", "https://f8bf-14-194-28-158.ngrok-free.app"}, allowCredentials = "true")
public class SendListController {
	
	@Autowired
	private SendListRepository sendListRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@PostMapping("lscchat/v1.0/addsendlist")
	SendList newSendList(@RequestBody SendList newSendList, HttpSession session) {
		System.out.println("calling");
		 Long companyId = (Long) session.getAttribute("company_id");
	        Long depId = (Long) session.getAttribute("dep_id");
	        Long unitId = (Long) session.getAttribute("unit_id");
	        if(companyId!=null) {
//	        	 Set session values to the new contact
	        	
	        	newSendList.setCompany_id(companyId);
	        	newSendList.setDep_id(depId);
	        	newSendList.setUnit_id(unitId);
		        return sendListRepository.save(newSendList);
	        }
	        return null;
	}
	
	@GetMapping("lscchat/v1.0/getsendlist")
	List<CustomSendListResponse> getAllUsers(HttpSession session){
		Long companyId = (Long) session.getAttribute("company_id");
        Long depId = (Long) session.getAttribute("dep_id");
        Long unitId = (Long) session.getAttribute("unit_id");
        List<SendList> sendLists = sendListRepository.findByIds(companyId, depId, unitId);
        
        List<CustomSendListResponse> customResponses = new ArrayList<>();
        
        for (SendList sendList : sendLists) {
            CustomSendListResponse customResponse = new CustomSendListResponse();
            customResponse.setTemplateName(sendList.getMessage());
            customResponse.setDate(sendList.getCreate_date());
            customResponse.setStatus(0);

            // Fetch the associated message count based on sendListId
            Long sendListId = sendList.getId();
            Long total = messageRepository.countByTotalListId(sendListId);
            Long send = messageRepository.countBySendListId(sendListId);
            Long delivered = messageRepository.countByDeliveredListId(sendListId);
            Long read = messageRepository.countByReadListId(sendListId);
            Long reply = 0L;
            Long notSend = messageRepository.countByFailedListId(sendListId);

            customResponse.setTotal(total.toString());
            customResponse.setSend(send.toString());
            customResponse.setDelivered(delivered.toString());
            customResponse.setRead(read.toString());
            customResponse.setReply(reply.toString());
            customResponse.setNotSend(notSend.toString());

            customResponses.add(customResponse);
        }

        return customResponses;
	}
	
	
//	@GetMapping("lscchat/v1.0/getsendlist")
//	List<SendList> getAllUsers(HttpSession session){
//		Long companyId = (Long) session.getAttribute("company_id");
//        Long depId = (Long) session.getAttribute("dep_id");
//        Long unitId = (Long) session.getAttribute("unit_id");
//        return sendListRepository.findByIds(companyId, depId, unitId);
//	}
}