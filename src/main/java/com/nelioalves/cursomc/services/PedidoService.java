package com.nelioalves.cursomc.services;

import java.util.Date;
import java.util.Optional;
import com.nelioalves.cursomc.domain.Pedido;
import org.springframework.stereotype.Service;
import com.nelioalves.cursomc.domain.ItemPedido;
import com.nelioalves.cursomc.domain.PagamentoComBoleto;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.nelioalves.cursomc.repositories.PagamentoRepository;
import org.springframework.transaction.annotation.Transactional;
import com.nelioalves.cursomc.repositories.ItemPedidoRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

import javax.naming.AuthenticationException;

/**
 * Classe de serviço com regras de negócios de Pedidos
 * @author José Henrique
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private BoletoService boletoService;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EmailService emailService;

    /**
     * Procura um Pedido por id
     * @param id Id do Pedido a ser procurado
     * @throws ObjectNotFoundException
     * @return Um Pedido
     */
    public Pedido find(Integer id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        return pedidoOptional.orElseThrow(
            () -> new ObjectNotFoundException(String.format("Objeto %d não encontrado! Tipo: %s", id, Pedido.class.getName())));
    }

    /**
     * Insere um Pedido
     * Além do Pedido ser salvo, será salvo um Pagamento e um ItemPedido e um email será enviado com o Pedido para o
     * Cliente
     * @param pedido Pedido a ser inserido
     * @return O Pedido inserido
     */
    @Transactional
    public Pedido insert(Pedido pedido) throws AuthenticationException {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
        pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PEDENTE);
        pedido.getPagamento().setPedido(pedido);
        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagamentoComBoleto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.generateDueDate(pagamentoComBoleto, pedido.getInstante());
        }
        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());
        for (ItemPedido itemPedido : pedido.getItemPedidoSet()) {
            itemPedido.setDesconto(0.0);
            itemPedido.setProduto(produtoService.find(itemPedido.getProduto().getId()));
            itemPedido.setPreco(itemPedido.getProduto().getPreco());
            itemPedido.setPedido(pedido);
        }
        itemPedidoRepository.saveAll(pedido.getItemPedidoSet());
//        emailService.sendOrderConfirmationEmail(pedido);
        emailService.sendOrderConfirmationHTMLEmail(pedido);
        return pedido;
    }
}
