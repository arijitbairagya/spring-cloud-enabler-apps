package com.org.wms.app.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author Arijit Bairagya
 * mail : ArijitBairagya@gmail.com
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableResourceServer
public class WmsUserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(WmsUserManagementApplication.class, args);
	}
}
