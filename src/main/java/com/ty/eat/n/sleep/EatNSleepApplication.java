package com.ty.eat.n.sleep;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailSender;

import com.ty.eat.n.sleep.service.MailService;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class EatNSleepApplication {

	public static void main(String[] args) {
		SpringApplication.run(EatNSleepApplication.class, args);
	}
	
	@Bean
	public Docket getDocket()
	{
		Contact cnt= new Contact("saqib", "www.Pgapp.com", "info@");
				List<VendorExtension>  extensions=new ArrayList<VendorExtension>();
				
				ApiInfo ap1=new ApiInfo("Pg API srvice", "service for pg", "snp1.0.8", "www.pgapp.com", cnt, "license", "www.pgapp.com",extensions);
				
				
				return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ty"))
				.build()
				.apiInfo(ap1)
				.useDefaultResponseMessages(false)
				;
				
				
	}
	
}
