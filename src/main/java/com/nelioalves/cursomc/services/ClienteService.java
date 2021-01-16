package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.repositories.ClienteRepositoy;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepositoy clienteRepositoy;

    public Cliente find(Integer id) {
        Optional<Cliente> optionalCliente = clienteRepositoy.findById(id);
        return optionalCliente.orElseThrow(
                () -> new ObjectNotFoundException(String.format("Objeto %d não encontrado! Tipo: %s", id, Cliente.class.getName())));
    }
    public Cliente update(Cliente cliente) {
        find(cliente.getId());
        Cliente newCliente = clienteRepositoy.getOne(cliente.getId());
        updateCliente(newCliente, cliente);
        return clienteRepositoy.save(newCliente);
    }
    public void delete(Integer id) {
        find(id);
        try {
            clienteRepositoy.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível remover o cliente, ele possí relações com entidades!");
        }
    }
    public List<Cliente> findAll() {
        return clienteRepositoy.findAll();
    }
    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepositoy.findAll(pageRequest);
    }
    public Cliente toCliete(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
    }
    private void updateCliente(Cliente newCliente, Cliente cliente) {
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
    }
}
