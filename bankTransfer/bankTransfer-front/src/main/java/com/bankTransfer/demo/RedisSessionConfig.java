//package com.bankTransfer.demo;
//
//import org.springframework.cache.CacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//
//@Configuration
//
//@EnableRedisHttpSession  (maxInactiveIntervalInSeconds = 1800)
//public class RedisSessionConfig {
//
//	
//	
//	 @Bean
//	public CacheManager cacheManager(RedisConnectionFactory factory) {
//	 RedisCacheManager cacheManager = RedisCacheManager.create(factory);
//	 	return cacheManager;	
//	 }
//	
//}
