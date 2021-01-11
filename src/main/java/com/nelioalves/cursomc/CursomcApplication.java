package com.nelioalves.cursomc;

import com.nelioalves.cursomc.domain.*;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collector;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepositoy clienteRepositoy;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

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

		produto1.getCategoriaList().addAll(Arrays.asList(categoria1));
		produto2.getCategoriaList().addAll(Arrays.asList(categoria1, categoria2));
		produto3.getCategoriaList().addAll(Arrays.asList(categoria1));

		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));

		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");

		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);

		estado1.getCidadeList().addAll(Arrays.asList(cidade1));
		estado2.getCidadeList().addAll(Arrays.asList(cidade2, cidade3));

		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

		Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Avenidas Matos", "105", "Sala 800", "Centro", "38777012", cliente1, cidade2);

		cliente1.getEnderecoList().addAll(Arrays.asList(endereco1, endereco2));

		clienteRepositoy.save(cliente1);
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido pedido1 = new Pedido(null, simpleDateFormat.parse("30/09/2017 10:32"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, simpleDateFormat.parse("10/10/2017 19:35"), cliente1, endereco2);

		cliente1.getPedidoList().addAll(Arrays.asList(pedido1, pedido2));

		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento1);
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PEDENTE, pedido2, simpleDateFormat.parse("20/10/2017 00:00"), null);
		pedido2.setPagamento(pagamento2);

		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));

		ItemPedido itemPedido1 = new ItemPedido(produto1, pedido1, 0.00, 1, 2000.00);
		produto1.getItemPedidoSet().addAll(Collections.singletonList(itemPedido1));
		ItemPedido itemPedido2 = new ItemPedido(produto3, pedido1, 0.00, 2, 80.00);
		produto3.getItemPedidoSet().addAll(Collections.singletonList(itemPedido2));
		ItemPedido itemPedido3 = new ItemPedido(produto2, pedido2, 100.00, 1, 800.00);
		produto2.getItemPedidoSet().addAll(Collections.singletonList(itemPedido3));

		pedido1.getItemPedidoSet().addAll(Arrays.asList(itemPedido1, itemPedido2));
		pedido2.getItemPedidoSet().addAll(Collections.singletonList(itemPedido3));

		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));
	}
}
