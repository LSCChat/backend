package com.lscchat.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lscchat.exception.UserNotFoundException;
import com.lscchat.model.Status;
import com.lscchat.repository.StatusRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://lsc-crm.in/", "https://f8bf-14-194-28-158.ngrok-free.app"}, allowCredentials = "true")
public class StatusController {
	private Timestamp create_date = new Timestamp(System.currentTimeMillis());
	
	@Autowired
	StatusRepository statusRepository;
	
	@PostMapping("lscchat/v1.0/send")
	Status send(@RequestBody Status update) {
//		Long companyId = (Long) session.getAttribute("company_id");
//        Long depId = (Long) session.getAttribute("dep_id");
//        Long unitId = (Long) session.getAttribute("unit_id");
//        if(companyId!=null) {
        	// Set session values to the new contact
//        	update.setCompany_id(companyId);
//        	update.setDep_id(depId);
//        	update.setUnit_id(unitId);
        	update.setSend_date(create_date);
	        return statusRepository.save(update);
//        }
	}
	
	@PostMapping("lscchat/v1.0/failed")
	Status faild(@RequestBody Status update) {
		System.out.println("calling");
		update.setFailed_date(create_date);
		return statusRepository.save(update);
	}
//	@PostMapping("lscchat/v1.0/delivered/")
//	public Status updateStatus(@RequestBody Status update) {
//	    System.out.println("calling delivered");
//	    String messageId = update.getMessageId();
//	    Optional<Status> optionalStatus = Optional.of(statusRepository.findByMessageId(messageId));
//
//	    if (optionalStatus.isPresent()) {
//	        Status status = optionalStatus.get();
//
//	        // Update the fields of the existing status object
//	        status.setDelivered(update.getDelivered()); // Assuming isDelivered() is a boolean field
//	        status.setDelivered_date(create_date); // Assuming delivered_date is of type LocalDateTime
//
//	        // Save the updated status back to the repository
//	        return statusRepository.save(status);
//	    } else {
//	        throw new UserNotFoundException(messageId);
//	    }
//	}

//	@PostMapping("lscchat/v1.0/delivered/")
//	Status updateStatus(@RequestBody Status update) {
//		System.out.println("calling deliverd");
//		String messageId = update.getMessageId();
//	    Optional<Status> optionalStatus = Optional.of(statusRepository.findByMessageId(messageId));
//
//	    if (optionalStatus.isPresent()) {
//	        Status status = optionalStatus.get();
//
//	        // Update the fields of the existing status object
//	        status.setDelivered(update.getDelivered());
//	        status.setDelivered_date(create_date); 
//
//	        // Save the updated status back to the repository
//	        return statusRepository.save(status);
//	    } else {
//	        throw new UserNotFoundException(messageId);
//	    }
//	}
	
//	@PostMapping("lscchat/v1.0/send")
//	Status send(@RequestBody Status update, HttpSession session) {
//		Long companyId = (Long) session.getAttribute("company_id");
//        Long depId = (Long) session.getAttribute("dep_id");
//        Long unitId = (Long) session.getAttribute("unit_id");
//        if(companyId!=null) {
//        	// Set session values to the new contact
//        	update.setCompany_id(companyId);
//        	update.setDep_id(depId);
//        	update.setUnit_id(unitId);
//        	update.setSend_date(create_date);
//	        return statusRepository.save(update);
//        }
//        return null;
//	}
	
//	@PostMapping("lscchat/v1.0/delivered")
//	ResponseEntity<?> delivered(@RequestBody Status update, HttpSession session) {
//		try {
//		Long companyId = (Long) session.getAttribute("company_id");
//        Long depId = (Long) session.getAttribute("dep_id");
//        Long unitId = (Long) session.getAttribute("unit_id");
//        if(companyId!=null) {
//        	// Set session values to the new contact
//        	final Long delivered = update.getDelivered();
//        	final String message_id = update.getMessage_id();
//	        statusRepository.updateDelivered(companyId, depId, unitId, message_id, delivered, create_date);
//        }
//        return ResponseEntity.ok("Update successful");
//    } catch (Exception e) {
//        return ResponseEntity.status(500).body("Error updating status");
//    }
//	}
	
//	@PutMapping("lscchat/v1.0/delivered/{message_id}")
//	Status updateStatus(@RequestBody Status update, @PathVariable String message_id) {
//		return statusRepository.findByMessageId(message_id)
//				.map(status ->{
//					status.setDelivered(update.getDelivered());
//					status.setDelivered_date(create_date);
//					return statusRepository.save(status);
//				}).orElseThrow(()-> UserNotFoundExpection(message_id));
//	}
	
//	@PutMapping("lscchat/v1.0/delivered/{message_id}")
//	Status updateStatus(@RequestBody Status update, @PathVariable String message_id) {
//	    return statusRepository.findByMessageId(message_id).map(status -> {
//	                // Update the fields of the existing status object
//	                status.setDelivered(update.getDelivered());
//	                status.setDelivered_date(create_date); // Assuming create_date is defined elsewhere
//
//	                // Save the updated status back to the repository
//	                return statusRepository.save(status);
//	            })
//	            .orElseThrow(() -> new UserNotFoundException(message_id));
//	}
	
	
	
//	------------------------------------------original-------------------------------------
	
	@PutMapping("lscchat/v1.0/delivered/{message_id}")
	Status updateStatus(@RequestBody Status update, @PathVariable String message_id) {
	    Optional<Status> optionalStatus = Optional.of(statusRepository.findByMessageId(message_id));

	    if (optionalStatus.isPresent()) {
	        Status status = optionalStatus.get();

	        // Update the fields of the existing status object
	        status.setDelivered(update.getDelivered());
	        status.setDelivered_date(create_date); // Assuming create_date is defined elsewhere

	        // Save the updated status back to the repository
	        return statusRepository.save(status);
	    } else {
	        throw new UserNotFoundException(message_id);
	    }
	}
	
	@PutMapping("lscchat/v1.0/readed/{message_id}")
	Status updateStatus2(@RequestBody Status update, @PathVariable String message_id) {
	    Optional<Status> optionalStatus = Optional.of(statusRepository.findByMessageId(message_id));

	    if (optionalStatus.isPresent()) {
	        Status status = optionalStatus.get();

	        // Update the fields of the existing status object
	        status.setReaded(update.getReaded());
	        status.setReaded_date(create_date);

	        // Save the updated status back to the repository
	        return statusRepository.save(status);
	    } else {
	        throw new UserNotFoundException(message_id);
	    }
	}
	
	@PutMapping("lscchat/v1.0/readedall/{mobileNo}")
	String updateStatusall(@RequestBody Status update, @PathVariable String mobileNo, HttpSession session) {
		Long companyId = (Long) session.getAttribute("company_id");
        Long depId = (Long) session.getAttribute("dep_id");
        Long unitId = (Long) session.getAttribute("unit_id");
	    statusRepository.updateStatusForMobile(mobileNo, create_date, companyId, depId, unitId);
	    return "updated";
	}
}
