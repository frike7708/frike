package com.zhongan.dwe;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zhongan.dwe.activiti.modeler.JsonpCallbackFilter;

@SpringBootApplication
@ComponentScan({"org.activiti.rest.diagram", "com.zhongan.dwe"})
@ImportResource("classpath*:/META-INF/spring/*.xml")
@EnableAutoConfiguration(exclude = {
		org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
		org.activiti.spring.boot.SecurityAutoConfiguration.class,
		org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration.class
})
public class DweAdminApplication extends WebMvcConfigurerAdapter {

    protected final static Logger logger = LoggerFactory.getLogger(DweAdminApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DweAdminApplication.class, args);
        logger.info("DweAdminApplication is sussess!");
    }
    
	@Bean
	public JsonpCallbackFilter filter(){
		return new JsonpCallbackFilter();
	}
	
}
