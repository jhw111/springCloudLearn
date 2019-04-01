/**
 * 
 */
package com.shencome.base.server.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shencome.base.server.gateway.filter.RateLimitFilter;
import com.shencome.base.server.gateway.filter.TokenFilter;

/**
 * @author 16040985
 *
 */
@Configuration
public class GatewayConfig {

	/**
	 * 根据令牌鉴权过滤器
	 * @return
	 */
	@Bean
	public TokenFilter tokenFilter(){
	    return new TokenFilter();
	}

	/**
	 * 根据令牌桶算法的限流过滤器
	 * @return
	 */
	@Bean
	public RateLimitFilter rateLimitFilter(){
	    return new RateLimitFilter();
	}
	
}