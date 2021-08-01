package com.nelioalves.cursomc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal e com main do projeto
 * @author José Henrique
 */
@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
