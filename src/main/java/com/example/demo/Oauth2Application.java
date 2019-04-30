package com.example.demo;

import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
public class Oauth2Application {

	@Autowired
	private ClientRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(Oauth2Application.class, args);
	}

	//This method will be used to check if the user has a valid token to access the resource
	@RequestMapping("/validateUser")
	public Principal user(Principal user) {
		return user;
	}


	@Bean
	public CommandLineRunner init(){
		return (arg) -> {
			clientRepository.save(Client.builder().clientId("javainuse-client").secret("javainuse-secret").build());
			clientRepository.save(Client.builder().clientId("client22").secret("secret22").build());

		};
	}
}
