package com.shencome.base.server.registerCenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// java -jar sc-eureka-cluster-registerCenter-0.0.1-SNAPSHOT.jar --spring.profiles.active=server1
// java -jar sc-eureka-cluster-registerCenter-0.0.1-SNAPSHOT.jar --spring.profiles.active=server2
// java -jar sc-eureka-cluster-registerCenter-0.0.1-SNAPSHOT.jar --spring.profiles.active=server3
@EnableEurekaServer
@SpringBootApplication
public class RegisterCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterCenterApplication.class, args);
	}
}
