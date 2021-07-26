package com.nelioalves.cursomc.resources;

import java.net.URI;
import java.util.List;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {
        Cliente cliente = clienteService.find(id);
        return ResponseEntity.ok().body(cliente);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO2 clienteDTO2) {
        Cliente cliente = clienteService.toCliente(clienteDTO2);
        clienteService.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO1 clienteDTO1, @PathVariable Integer id) {
        clienteDTO1.setId(id);
        Cliente cliente = clienteService.update(clienteService.toCliente(clienteDTO1));
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO1>> findAll() {
        return ResponseEntity.ok().body(
            clienteService.findAll().stream().map(ClienteDTO1::new).collect(Collectors.toList()));
    }
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
