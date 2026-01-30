package com.snehil.module2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

// IMPORTANT: Use this specific import for Spring Boot 3+ / 4+
import org.h2.server.web.JakartaWebServlet;

@SpringBootApplication
public class Module2Application {

	public static void main(String[] args) {
		SpringApplication.run(Module2Application.class, args);
	}

	@Bean
	public ServletRegistrationBean<JakartaWebServlet> h2Servlet() {
		// We use JakartaWebServlet instead of the standard WebServlet
		ServletRegistrationBean<JakartaWebServlet> registrationBean = new ServletRegistrationBean<>(new JakartaWebServlet());
		registrationBean.addUrlMappings("/h2-console/*");
		return registrationBean;
	}
}