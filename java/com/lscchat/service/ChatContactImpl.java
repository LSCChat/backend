package com.lscchat.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lscchat.model.Contacts;
import com.lscchat.repository.ContactsRepository;
import com.lscchat.repository.MessageRepository;

@Service
public class ChatContactImpl implements ChatContact{
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ContactsRepository contactsRepository;
	
	@Override
	public List<Map<String, String>> findChatContactService(Long companyId, Long depId, Long unitId, String mobileNo) {
		
		List<List<String>> contactList = messageRepository.findChatContact(companyId, depId, unitId, mobileNo);
		
		List<Map<String, String>> list = new ArrayList();
		
		for (List<String> contact : contactList) {
			System.out.println(contact);
			Map<String, String> map = new HashMap<>();
			
			String mobile = contact.get(0);
			Long notRead = 0L; 
			String lastMessage = "0";
			String lastDateTime = contact.get(1);
			String name = "";
			
			//select value for notready, last message and last message date time
			List<Object[]> latestMessages = messageRepository.findByNotRead(mobile, companyId, depId, unitId);
//			System.out.println(latestMessages.toString());

			System.out.println(latestMessages.size());
			for (Object[] row : latestMessages) {
			    notRead = (Long) row[1];
			    lastMessage = (String) row[3];
			    Timestamp timestamp = (Timestamp) row[2];
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    lastDateTime = sdf.format(new Date(timestamp.getTime()));
			    name = (String) row[4];
			}
			if(name.equals("")) {
				Contacts nameContact = contactsRepository.findName(mobile, companyId, depId, unitId);
				name = nameContact.getFullname();
			}
			map.put("name", name);
			map.put("mobileNumber", mobile);
			map.put("notRead", String.valueOf(notRead));
			map.put("lastMessage", lastMessage);
			map.put("lastDateTime", lastDateTime);
			list.add(map);
		}
//		System.out.print(list.toString());
		return list;
	}
	
}
