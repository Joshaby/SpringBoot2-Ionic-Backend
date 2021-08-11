package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.Pedido;
import org.springframework.data.domain.Page;
import com.nelioalves.cursomc.domain.Cliente;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interface de repositório para Pedidos
 * @author José Henrique
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    /**
     * Procura Pedidos por um Clinte
     * Pedidos são retornandos em uma página(Page)
     * @param cliente Cliente a ser usado para procurar seus Pedidos
     * @param pageRequest Página(Page) com suas informações de requisição
     * @return Uma página(Page) com os Pedidos do Cliente
     */
    @Transactional(readOnly = true)
    Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
}
