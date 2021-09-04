package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Classe REST API para endpoints /pedidos
 *
 * @author José Henrique
 */
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    /**
     * Procura um Pedido por um id
     * @param id id do Pedido a ser procurado
     * @return Um JSON de resposta(ResponseEntity) com o Pedido no corpo com status HTTP ok(200)
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pedido> find(@PathVariable Integer id) {
        Pedido pedido = pedidoService.find(id);
        return ResponseEntity.ok().body(pedido);
    }

    /**
     * Insere um Pedido
     * @param pedido Pedido a ser inserido
     * @return Um JSON de resposta(ResponseEntity) com corpo vazio mais com header contendo a URL do Pedido inserido!
     * Usa o status HTTP created(201)
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Pedido pedido) {
        pedidoService.insert(pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Procura pedidos e os retornam em uma página(Page)
     * @param page Número da página - valor padrão: "0"
     * @param linesPerPage Quantidade de linhas da página - valor padrão: "24"
     * @param direction Direção da página - valor padrão: "DESC"
     * @param orderBy Ordem da página - valor padrão: "instante"
     * @return Um JSON como resposta(ResponseEntity) com a página no corpo e com status HTTP ok(200)
     */
    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    public ResponseEntity<Page<Pedido>> finPage(
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
        @RequestParam(value = "direction", defaultValue = "DESC") String direction,
        @RequestParam(value = "orderBy", defaultValue = "instante") String orderBy) {

        return ResponseEntity.ok().body(pedidoService.findPage(page, linesPerPage, direction, orderBy));
    }
}
