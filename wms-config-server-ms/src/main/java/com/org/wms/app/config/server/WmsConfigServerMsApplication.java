package com.org.wms.app.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Arijit Bairagya
 * mail : ArijitBairagya@gmail.com
 *
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class WmsConfigServerMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WmsConfigServerMsApplication.class, args);
	}
}
