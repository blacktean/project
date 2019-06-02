package com.bankTransfer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
@MapperScan("com.bankTransfer.mapper")
@ComponentScan(basePackages ={"com.bankTransfer"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BankTransferFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankTransferFrontApplication.class, args);
	}

}
