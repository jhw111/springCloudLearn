package com.shencome.base.server.gateway.filter;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import reactor.core.publisher.Mono;

/**
 * 限流过滤器
 * @author 16040985
 *
 */
public class RateLimitFilter implements GlobalFilter, Ordered {

	private static final Log log = LogFactory.getLog(RateLimitFilter.class);
	
	/**
	 * 令牌桶最大容量
	 */
	int capacity;

	/**
	 * 每次token补充量
	 */
	int refillTokens;

	/**
	 * 补充token的时间间隔
	 */
	Duration refillDuration;

	private static final Map<String, Bucket> CACHE = new ConcurrentHashMap<>();

	/**
	 * 创建一个令牌桶
	 * @return
	 */
	private Bucket createBucket() {
		capacity = 10;
		refillTokens = 2;
		refillDuration = Duration.ofSeconds(1);
		Refill refill = Refill.of(refillTokens, refillDuration);
		Bandwidth limit = Bandwidth.classic(capacity, refill);
		return Bucket4j.builder().addLimit(limit).build();
	}
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
		Bucket bucket = CACHE.computeIfAbsent(ip, k->createBucket());
		
		log.info("IP: " + ip + ",令牌桶可用令牌数: " + bucket.getAvailableTokens());
		if(bucket.tryConsume(1)){
			return chain.filter(exchange);
		}
		
		exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
		return exchange.getResponse().setComplete();
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}
}
