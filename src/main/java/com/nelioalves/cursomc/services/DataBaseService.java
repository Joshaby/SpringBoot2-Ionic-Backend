package com.nelioalves.cursomc.services;

import java.util.Arrays;
import java.util.Collections;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.nelioalves.cursomc.domain.*;
import com.nelioalves.cursomc.repositories.*;
import org.springframework.stereotype.Service;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Classe de serviço para iniciar massa de dados da aplicação
 * @author José Henrique
 */
@Service
public class DataBaseService {

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

    /**
     * Método que irá instânciar as classes de domínio e adiciona-las ao banco
     * @throws ParseException
     */
    public void initializeDataBase() throws ParseException {
        Categoria categoria1 = new Categoria(null, "Informática");
        Categoria categoria2 = new Categoria(null, "Escritório");
        Categoria categoria3 = new Categoria(null, "Cama, mesa e banho");
        Categoria categoria4 = new Categoria(null, "Eletrônicos");
        Categoria categoria5 = new Categoria(null, "Jardinagem");
        Categoria categoria6 = new Categoria(null, "Decoração");
        Categoria categoria7 = new Categoria(null, "Perfumaria");

        Produto produto1 =  new Produto(null, "Computador", 2000.00);
        Produto produto2 =  new Produto(null, "Impressora", 800.00);
        Produto produto3 =  new Produto(null, "Mouse", 80.00);
        Produto produto4 =  new Produto(null, "Mesa de escritório", 300.00);
        Produto produto5 =  new Produto(null, "Toalha", 50.00);
        Produto produto6 =  new Produto(null, "Colcha", 200.00);
        Produto produto7 =  new Produto(null, "TV True Color", 1200.00);
        Produto produto8 =  new Produto(null, "Roçadeira", 80.00);
        Produto produto9 =  new Produto(null, "Abajour", 100.00);
        Produto produto10 =  new Produto(null, "Pendente", 180.00);
        Produto produto11 =  new Produto(null, "Shampoo", 90.00);

        categoria1.getProdutosList().addAll(Arrays.asList(produto1, produto2, produto3));
        categoria2.getProdutosList().addAll(Arrays.asList(produto2, produto4));
        categoria3.getProdutosList().addAll(Arrays.asList(produto5, produto6));
        categoria4.getProdutosList().addAll(Arrays.asList(produto1, produto2, produto3, produto7));
        categoria5.getProdutosList().addAll(Arrays.asList(produto8));
        categoria6.getProdutosList().addAll(Arrays.asList(produto9, produto10));
        categoria7.getProdutosList().addAll(Arrays.asList(produto11));

        produto1.getCategoriaList().addAll(Arrays.asList(categoria1, categoria4));
        produto2.getCategoriaList().addAll(Arrays.asList(categoria1, categoria2, categoria4));
        produto3.getCategoriaList().addAll(Arrays.asList(categoria1, categoria4));
        produto4.getCategoriaList().addAll(Arrays.asList(categoria2));
        produto5.getCategoriaList().addAll(Arrays.asList(categoria3));
        produto6.getCategoriaList().addAll(Arrays.asList(categoria3));
        produto7.getCategoriaList().addAll(Arrays.asList(categoria4));
        produto8.getCategoriaList().addAll(Arrays.asList(categoria5));
        produto9.getCategoriaList().addAll(Arrays.asList(categoria6));
        produto10.getCategoriaList().addAll(Arrays.asList(categoria6));
        produto11.getCategoriaList().addAll(Arrays.asList(categoria7));

        categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2, categoria3, categoria4, categoria5, categoria6, categoria7));
        produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4, produto5, produto6, produto7, produto8, produto9, produto10, produto11));

        Estado estado1 = new Estado(null, "Minas Gerais");
        Estado estado2 = new Estado(null, "São Paulo");

        Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
        Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
        Cidade cidade3 = new Cidade(null, "Campinas", estado2);

        estado1.getCidadeList().addAll(Arrays.asList(cidade1));
        estado2.getCidadeList().addAll(Arrays.asList(cidade2, cidade3));

        estadoRepository.saveAll(Arrays.asList(estado1, estado2));
        cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

        Cliente cliente1 = new Cliente(null, "Maria Silva", "josehenriquebrito55@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
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
