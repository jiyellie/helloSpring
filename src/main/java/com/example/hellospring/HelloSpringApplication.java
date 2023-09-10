package com.example.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //하위 패키를 다 찾아서 빈으로 등록을 하는데 아무렇게나 만들어서 어노테이션을 만든다고 해서 다 빈으로 등록되지 않는다.
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
