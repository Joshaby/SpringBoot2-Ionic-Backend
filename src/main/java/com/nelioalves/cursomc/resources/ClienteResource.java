package com.nelioalves.cursomc.resources;

import java.net.URI;
import java.util.List;
import javax.naming.AuthenticationException;
import javax.validation.Valid;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO1;
import com.nelioalves.cursomc.dto.ClienteDTO2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nelioalves.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Classe REST API para endpoints /clientes
 * @author José Henrique
 */
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    /**
     * Procura um Cliente por id
     * @param id Id do Cliente a ser procurado
     * @return Um JSON de resposta(ResponseEntity) com o Cliente no corpo e com status HTTP ok(200)
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id) throws AuthenticationException {
        Cliente cliente = clienteService.find(id);
        return ResponseEntity.ok().body(cliente);
    }

    /**
     * Insere um Cliente
     * @param clienteDTO2 Cliente a ser inserido
     * @return Um JSON de resposta(ResponseEntity) com corpo vazio e com URL do Cliente inserido no header
     * Usa o status HTTP created(201)
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO2 clienteDTO2) {
        Cliente cliente = clienteService.toCliente(clienteDTO2);
        clienteService.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Atualiza um Cliente
     * @param clienteDTO1 Atualização em forma de Cliente
     * @param id Id do Cliente que será atualizado
     * @return Um JSON de resposta sem corpo e com status HTTP No Contente(204)
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO1 clienteDTO1, @PathVariable Integer id) {
        clienteDTO1.setId(id);
        Cliente cliente = clienteService.update(clienteService.toCliente(clienteDTO1));
        return ResponseEntity.noContent().build();
    }

    /**
     * Deleta um Cliente
     * @param id Id do Cliente a ser deletado
     * @return Um JSON de resposta sem corpo e com status HTTP No Contente(204)
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Procura todos os Clientes
     * @return Um JSON de resposta com os Clientes no corpo e com status HTTP ok(200)
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO1>> findAll() {
        return ResponseEntity.ok().body(
            clienteService.findAll().stream().map(ClienteDTO1::new).collect(Collectors.toList()));
    }

    /**
     * Procura Clientes e os retornam em um página(Page)
     * @param page Número da página - valor padrão: "0"
     * @param linesPerPages Quantidade de linhas da página - valor padrão: "24"
     * @param direction Direção da página - valor padrão: "ASC"
     * @param orderBy Ordem da página - valor padrão: "id"
     * @return Um JSON como resposta(ResponseEntity) com a página no corpo e com status HTTP ok(200)
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value ="/pages", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO1>> findPage(
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPages,
        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
        return ResponseEntity.ok().body(
            clienteService.findPage(page, linesPerPages, direction, orderBy).map(ClienteDTO1::new));
    }
}
