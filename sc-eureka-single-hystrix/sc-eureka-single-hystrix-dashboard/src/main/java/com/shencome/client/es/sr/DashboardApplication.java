package com.shencome.client.es.sr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

// 加上@EnableHystrixDashboard注解，启用 Hystrix Dashboard 功能
@EnableHystrixDashboard
@EnableCircuitBreaker
@SpringBootApplication
public class DashboardApplication {

    // http://localhost:8003/hystrix
	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
	}
}