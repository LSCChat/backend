package com.lscchat.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class SendList {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		
		private Long id;
		private Long company_id;
		private Long dep_id;
		private Long unit_id;
		private String message_type;
		private String message;
		private Timestamp create_date;
		private Long flag;
		
		public SendList() {
	        this.create_date = new Timestamp(System.currentTimeMillis()); // Sets the timestamp to the current time
	        this.flag = (long) 0;
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getCompany_id() {
			return company_id;
		}

		public void setCompany_id(Long company_id) {
			this.company_id = company_id;
		}

		public Long getDep_id() {
			return dep_id;
		}

		public void setDep_id(Long dep_id) {
			this.dep_id = dep_id;
		}

		public Long getUnit_id() {
			return unit_id;
		}

		public void setUnit_id(Long unit_id) {
			this.unit_id = unit_id;
		}

		public String getMessage_type() {
			return message_type;
		}

		public void setMessage_type(String message_type) {
			this.message_type = message_type;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Timestamp getCreate_date() {
			return create_date;
		}

		public void setCreate_date(Timestamp create_date) {
			this.create_date = create_date;
		}

		public Long getFlag() {
			return flag;
		}

		public void setFlag(Long flag) {
			this.flag = flag;
		}
		
}
