package com.nelioalves.cursomc;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria categoria1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");

		Produto produto1 =  new Produto(null, "Computador", 2000.00);
		Produto produto2 =  new Produto(null, "Impressora", 800.00);
		Produto produto3 =  new Produto(null, "Mouse", 80.00);

		categoria1.getProdutosList().addAll(Arrays.asList(produto1, produto2, produto3));
		categoria2.getProdutosList().addAll(Arrays.asList(produto2));

		produto1.getCategoriaLst().addAll(Arrays.asList(categoria1));
		produto2.getCategoriaLst().addAll(Arrays.asList(categoria1, categoria2));
		produto3.getCategoriaLst().addAll(Arrays.asList(categoria1));

		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
	}
}
