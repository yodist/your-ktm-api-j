package com.yodist.yourktm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.yodist.yourktm.config.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class YourktmApplication {

	public static void main(String[] args) {
		SpringApplication.run(YourktmApplication.class, args);
	}

}
