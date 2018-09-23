package com.org.wms.app.service.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Arijit Bairagya
 * mail : ArijitBairagya@gmail.com
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class WmsServiceRegistryMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WmsServiceRegistryMsApplication.class, args);
	}
}
