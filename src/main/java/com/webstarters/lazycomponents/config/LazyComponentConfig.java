package com.webstarters.lazycomponents.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.webstarters.lazycomponents.dao.ComponentDetailsDao;
import com.webstarters.lazycomponents.dao.impl.ComponentDetailsDaoImpl;
import com.webstarters.lazycomponents.services.ComponentDetailsService;
import com.webstarters.lazycomponents.services.impl.ComponentDetailsServiceImpl;

@Configuration
public class LazyComponentConfig {

	@Bean
	@ConditionalOnMissingBean
	public ComponentDetailsService componentDetailsService() {
		return new ComponentDetailsServiceImpl();
	}
	
	@Bean
	@ConditionalOnMissingBean
	public ComponentDetailsDao componentDetailsDao() {
		return new ComponentDetailsDaoImpl();
	}
	
}
