package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id) {
        clienteDTO.setId(id);
        Cliente cliente = clienteService.update(clienteService.toCliete(clienteDTO));
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        return ResponseEntity.ok().body(
                clienteService.findAll().stream().map(ClienteDTO::new).collect(Collectors.toList()));
    }
    @RequestMapping(value ="/pages", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPages,
        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {

        return ResponseEntity.ok().body(
                clienteService.findPage(page, linesPerPages, direction, orderBy).map(ClienteDTO::new));
    }
}
