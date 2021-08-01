package com.nelioalves.cursomc.resources;

import java.net.URI;
import com.nelioalves.cursomc.domain.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nelioalves.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/** Classe REST API para endpoints /pedidos
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
}
