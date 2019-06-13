package com.bankTransfer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@EnableScheduling 
@MapperScan("com.bankTransfer.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableAutoConfiguration(exclude = {
org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class BankTransferFrontApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(BankTransferFrontApplication.class, args);
	}
  
}
