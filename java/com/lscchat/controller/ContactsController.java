package com.lscchat.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lscchat.model.Campaign;
import com.lscchat.model.Contacts;
import com.lscchat.repository.CampaignRepository;
import com.lscchat.repository.ContactsRepository;

import jakarta.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://lsc-crm.in/", "https://f8bf-14-194-28-158.ngrok-free.app"}, allowCredentials = "true")
public class ContactsController {
	
	@Autowired
	private ContactsRepository contactsRepository;
	
	@Autowired
	private CampaignRepository campaignRepository;
	
//	@Autowired
//	private Campaign campaign;
	
	@PostMapping("lscchat/v1.0/addcontactmanual")
	Contacts newContact(@RequestBody Contacts newContact, HttpSession session) {
		 Long companyId = (Long) session.getAttribute("company_id");
	        Long depId = (Long) session.getAttribute("dep_id");
	        Long unitId = (Long) session.getAttribute("unit_id");
	        if(companyId!=null) {
	        	// Set session values to the new contact
		        newContact.setCompany_id(companyId);
		        newContact.setDep_id(depId);
		        newContact.setUnit_id(unitId);
		        return contactsRepository.save(newContact);
	        }
	        return null;
	}
	@GetMapping("lscchat/v1.0/contactdetails")
	List<Contacts> getAllUsers(HttpSession session){
		Long companyId = (Long) session.getAttribute("company_id");
        Long depId = (Long) session.getAttribute("dep_id");
        Long unitId = (Long) session.getAttribute("unit_id");
        return contactsRepository.findByIds(companyId, depId, unitId);
	}
	@PostMapping("lscchat/v1.0/addcontactExcel")
	  public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
	    try (InputStream inputStream = file.getInputStream()) {
	      Workbook workbook = WorkbookFactory.create(inputStream);
	      Sheet sheet = workbook.getSheetAt(0);
	      
	      Long companyId = (Long) session.getAttribute("company_id");
	        Long depId = (Long) session.getAttribute("dep_id");
	        Long unitId = (Long) session.getAttribute("unit_id");
	        if(companyId!=null) {
	        	List<Contacts> contacts = new ArrayList<>();
	  	      for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	  	    	  Row row = sheet.getRow(i);
	                // Extract data from cells and create Contacts objects
	                Contacts contact = new Contacts();
	                // Map column values to contact properties based on your Excel structure
	                // For example:
	             // Set session values to the new contact
	                contact.setCompany_id(companyId);
	                contact.setDep_id(depId);
	                contact.setUnit_id(unitId);
	                contact.setFullname(row.getCell(0).getStringCellValue());
	                DataFormatter dataFormatter = new DataFormatter();
	                contact.setMobile_no(dataFormatter.formatCellValue(row.getCell(1)));
	                contact.setEmail(row.getCell(2).getStringCellValue());
	                contact.setLocation(row.getCell(3).getStringCellValue());
	                contacts.add(contact);
	                contactsRepository.save(contact);
	  	      }
	  	      
	  	      // Save contacts to database using contactsRepository
	            
	        	
		        
	        }else {
	        	return ResponseEntity.ok("else");
	        }

	      
	      
	      

	      return ResponseEntity.ok("Data uploaded successfully");
	    } catch (Exception e) {
	      return ResponseEntity.badRequest().body("Error: " + e.getMessage());
	    }
	  }
	
	@PostMapping("lscchat/v1.0/addcontactExcel2")
	  public ResponseEntity<?> uploadFile2(@RequestParam("file") MultipartFile file, @RequestParam("campaign_name") String campaignName, @RequestParam("template") String template, HttpSession session) throws IOException {
		Long companyId = (Long) session.getAttribute("company_id");
        Long depId = (Long) session.getAttribute("dep_id");
        Long unitId = (Long) session.getAttribute("unit_id");
        Long userRole = (Long) session.getAttribute("userRole");
        Long campaignLimit = (Long) session.getAttribute("campaignLimit");
		//1-> Save data in Campaign
        Campaign campaign = new Campaign();
		campaign.setCampaign(campaignName);
		campaign.setTemplate(template);
		campaign.setCompany_id(companyId);
		campaign.setDep_id(depId);
		campaign.setUnit_id(unitId);
		Campaign saveCampaign = campaignRepository.save(campaign);
		Long campaignId = saveCampaign.getId();
		
		if(userRole == 1) {
			campaign.setStatus(2L);
		}else {
			campaign.setStatus(1L);
		}
		
	    try (InputStream inputStream = file.getInputStream()) {
	      Workbook workbook = WorkbookFactory.create(inputStream);
	      Sheet sheet = workbook.getSheetAt(0);
	   
	        if(companyId!=null) {
	        	List<Contacts> contacts = new ArrayList<>();
	  	      for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	  	    	  Row row = sheet.getRow(i);
	                // Extract data from cells and create Contacts objects
	                Contacts contact = new Contacts();
	                // Map column values to contact properties based on your Excel structure
	                // For example:
	             // Set session values to the new contact
	                contact.setCompany_id(companyId);
	                contact.setDep_id(depId);
	                contact.setUnit_id(unitId);
	                contact.setCampaignId(campaignId);
	                contact.setFullname(row.getCell(0).getStringCellValue());
	                DataFormatter dataFormatter = new DataFormatter();
	                contact.setMobile_no(dataFormatter.formatCellValue(row.getCell(1)));
	                contact.setEmail(row.getCell(2).getStringCellValue());
	                contact.setLocation(row.getCell(3).getStringCellValue());
	                contacts.add(contact);
//	                contactsRepository.save(contact);
	  	      }
	  	      // Save contacts to database using contactsRepository
	  	      if(contacts.size()<=campaignLimit) {
	  	    	contactsRepository.saveAll(contacts);
	  	      }else {
	  	    	// Customize your error response structure
	              return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                  .body(Map.of(
	                      "status", "failed",
	                      "error", Map.of(
	                          "code", "E001",
	                          "message", "maximum number of contacts allowed is "+campaignLimit
	                      )
	                  ));
	  	      }    
	        }else {
	        	return ResponseEntity.ok("else");
	        }

	      return ResponseEntity.ok("Data uploaded successfully");
	    } catch (Exception e) {
	      return ResponseEntity.badRequest().body("Error: " + e.getMessage());
	    }
	  }
	

}
