package com.bankTransfer.demo;

import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;

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
			data.setUrl("jdbc:oracle:thin:@10.1.16.3:1521:orcl");
			data.setUsername("fangjia");
			data.setPassword("112233");
			
			return data;
		}
		
		 @Bean  
		    public DefaultKaptcha getDefaultKaptcha(){  
		        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();  
		        Properties properties = new Properties();  
		        properties.setProperty("kaptcha.border", "yes");  
		        properties.setProperty("kaptcha.border.color", "105,179,90");  
		        properties.setProperty("kaptcha.textproducer.font.color", "blue");  
		        properties.setProperty("kaptcha.image.width", "110");  
		        properties.setProperty("kaptcha.image.height", "40");  
		        properties.setProperty("kaptcha.textproducer.font.size", "30");  
		        properties.setProperty("kaptcha.session.key", "code");  
		        properties.setProperty("kaptcha.textproducer.char.length", "4");  
		        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");  
		        properties.setProperty(" kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.FishEyeGimpy"); 
		        
		       
		        com.google.code.kaptcha.util.Config config = new com.google.code.kaptcha.util.Config(properties);  
		        defaultKaptcha.setConfig(config);  
		        return defaultKaptcha;  
		    }  
		
		
}
