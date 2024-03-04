package com.lscchat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.ServletContext;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://lsc-crm.in/"}, allowCredentials = "true")
public class VersionFinder {
	

	    @Autowired
	    private ServletContext servletContext;

	    @GetMapping("/info")
	    public String getInfo() {
	        String tomcatVersion = servletContext.getServerInfo();
	        return "Tomcat Version: " + tomcatVersion;
	    }


}
