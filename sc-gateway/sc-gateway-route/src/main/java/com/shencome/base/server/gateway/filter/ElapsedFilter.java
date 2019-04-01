package com.shencome.base.server.gateway.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * 统计服务执行时间
 * @author 16040985
 *
 */
public class ElapsedFilter implements GatewayFilter, Ordered {

	private static final Log log = LogFactory.getLog(ElapsedFilter.class);
	
	private static final String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";
	
	/**
	 * 统计服务执行时间
	 * @param exchange
	 * @param chain
	 * @return
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		exchange.getAttributes().put(ELAPSED_TIME_BEGIN, System.currentTimeMillis());
		return chain.filter(exchange).then(
				Mono.fromRunnable(() -> {
					Long startTime = exchange.getAttribute(ELAPSED_TIME_BEGIN);
					if (startTime != null) {
						Long elapsedTime = System.currentTimeMillis() - startTime;
						log.info(exchange.getRequest().getURI().getRawPath() + ":" + elapsedTime + "ms");
					}
				}));
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}
	
}
