package com.lux.ge.facade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
   
@ComponentScan("com.lux.ge.facade.model")
@SpringBootApplication
public class FacadeApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FacadeApplication.class);
    }
    
	public static void main(String[] args) {
		SpringApplication.run(FacadeApplication.class, args);
	}

}
