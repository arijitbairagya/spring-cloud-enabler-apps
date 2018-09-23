package com.org.wms.app.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Arijit Bairagya
 * mail : ArijitBairagya@gmail.com
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class WmsOauthMsApplication { 

	public static void main(String[] args) {
		SpringApplication.run(WmsOauthMsApplication.class, args);
	}
}
