package com.zyp.springvueserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringVueServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringVueServerApplication.class, args);
	}

}
