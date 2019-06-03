package com.bankTransfer.demo;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	/*
	 * @Value("${driver-class-name}") String drivername;
	 * 
	 * @Value("${url}") String url;
	 * 
	 * @Value("${username}") String username;
	 * 
	 * @Value("${password}") String password;
	 */
//		@Bean
//		public BasicDataSource dataSource() {
//			BasicDataSource data=new BasicDataSource();
//			data.setUrl("jdbc:mysql://127.0.0.1:3306/p2p?useUnicode=true&characterEncoding=UTF-8");
//			data.setUsername("root");
//			data.setPassword("123");
//			data.setDriverClassName("com.mysql.jdbc.Driver");
//			return data;
//		}
		
		@Bean
	
		public BasicDataSource dataSource() {
			
			BasicDataSource data = new BasicDataSource();
			data.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			data.setUrl("jdbc:oracle:thin:@10.1.5.156:1521:orcl");
			data.setUsername("fangjia");
			data.setPassword("112233");
			
			return data;
		}
		
		
		
		
}
