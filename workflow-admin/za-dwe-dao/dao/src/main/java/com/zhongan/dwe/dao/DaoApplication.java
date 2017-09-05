package com.zhongan.dwe.dao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.mybatis.spring.annotation.MapperScan;

import com.zhongan.dsf.register.support.RegisterConsul;

@SpringBootApplication
@EnableTransactionManagement
@ImportResource("classpath*:META-INF/spring/app-*.xml")
@ComponentScan(basePackages={"com.zhongan"})
@MapperScan("com.zhongan.dwe.dao.repository")
@ComponentScan(basePackages = { "com.zhongan" }, basePackageClasses = { RegisterConsul.class })
public class DaoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DaoApplication.class, args);
	}
	
}
