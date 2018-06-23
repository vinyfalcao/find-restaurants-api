package com.restaurants.finder;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.restaurants.finder.common.concurrency.ContextAwarePoolExecutor;

/**
 * 
 * @author Vinicius Falcão
 *
 */
@SpringBootApplication
@EnableCaching
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Executor asyncExecutor() {
		return ContextAwarePoolExecutor.getInstance();
	}
}
