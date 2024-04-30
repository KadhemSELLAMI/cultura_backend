package com.culturascope.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.culturascope.api.service.FilesStorageService;

import jakarta.annotation.Resource;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

	@Resource
  	FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.init();
	}

}
