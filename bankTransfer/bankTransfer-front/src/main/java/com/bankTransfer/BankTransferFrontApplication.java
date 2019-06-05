package com.bankTransfer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
@MapperScan("com.bankTransfer.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BankTransferFrontApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(BankTransferFrontApplication.class, args);
	}
  
}
