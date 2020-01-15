package com.ly.oa.common.config;

import com.ly.oa.common.orika.OrikaBeanMapper;
import com.ly.oa.common.page.PageConvertMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ly
 */
@Configuration
public class OrikaConfiguration {
	@Bean
	public OrikaBeanMapper beanMapper() {
		return new OrikaBeanMapper();
	}

	@Bean
	public PageConvertMapper pageConvertMapper() {
		return new PageConvertMapper(beanMapper());
	}
}
