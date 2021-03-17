package com.cooperl.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
public class HelloApplication {

	public static void main(String[] args) {
		ReactorDebugAgent.init();
		SpringApplication.run(HelloApplication.class, args);
	}

}
