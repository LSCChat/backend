package com.lscchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LscchatApplication {

	public static void main(String[] args) {
		SpringApplication.run(LscchatApplication.class, args);
	}

}

//package com.lscchat;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//
//@SpringBootApplication
//public class LscchatApplication extends SpringBootServletInitializer {
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(LscchatApplication.class);
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(LscchatApplication.class, args);
//    }
//}


