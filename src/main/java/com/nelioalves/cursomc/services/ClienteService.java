package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.dto.ClienteDTO1;
import com.nelioalves.cursomc.dto.ClienteDTO2;
import com.nelioalves.cursomc.repositories.ClienteRepositoy;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepositoy clienteRepositoy;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> optionalCliente = clienteRepositoy.findById(id);
        return optionalCliente.orElseThrow(
            () -> new ObjectNotFoundException(String.format("Objeto %d não encontrado! Tipo: %s", id, Cliente.class.getName())));
    }
    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        Cliente clienteSaved = clienteRepositoy.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecoList());
        return clienteSaved;
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
            throw new DataIntegrityException("Não é possível remover o cliente, ele possuí produtos!");
        }
    }
    public List<Cliente> findAll() {
        return clienteRepositoy.findAll();
    }
    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepositoy.findAll(pageRequest);
    }
    public Cliente toCliente(ClienteDTO1 clienteDTO1) {
        return new Cliente(clienteDTO1.getId(), clienteDTO1.getNome(), clienteDTO1.getEmail(), null, null);
    }
    public Cliente toCliente(ClienteDTO2 clienteDTO2) {
        Cliente cliente = new Cliente(
            null, clienteDTO2.getNome(), clienteDTO2.getEmail(), clienteDTO2.getCpfOuCnpj(),
            TipoCliente.toEnum(clienteDTO2.getTipoCliente()));
        Cidade cidade = new Cidade(clienteDTO2.getCidadeId(), null, null);
        Endereco endereco = new Endereco(null, clienteDTO2.getLogradouro(), clienteDTO2.getNumero(),
            clienteDTO2.getComplemento(), clienteDTO2.getBairro(), clienteDTO2.getCep(), cliente, cidade);
        cliente.getEnderecoList().add(endereco);
        cliente.getTelefones().add(clienteDTO2.getTelefone1());
        if (clienteDTO2.getTelefone2() != null)
            cliente.getTelefones().add(clienteDTO2.getTelefone2());
        if (clienteDTO2.getTelefone3() != null)
            cliente.getTelefones().add(clienteDTO2.getTelefone3());
        return cliente;
    }
    private void updateCliente(Cliente newCliente, Cliente cliente) {
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
    }
}
