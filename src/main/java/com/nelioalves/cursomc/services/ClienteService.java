package com.nelioalves.cursomc.services;

import java.util.*;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import org.springframework.stereotype.Service;
import com.nelioalves.cursomc.dto.ClienteDTO2;
import com.nelioalves.cursomc.dto.ClienteDTO1;
import com.nelioalves.cursomc.domain.enums.Perfil;
import org.springframework.data.domain.PageRequest;
import com.nelioalves.cursomc.security.UserDetailsImpl;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.ClienteRepositoy;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.AuthorizationException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

/**
 * Classe de serviço com regras de negócio para Clientes
 * @author José Henrique
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepositoy clienteRepositoy;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Procura um Cliente por id
     * @param id id do Cliente a ser procurado
     * @throws ObjectNotFoundException
     * @throws AuthorizationException
     * @return Um Cliente
     */
    public Cliente find(Integer id) {
        UserDetailsImpl userDetailsImpl = UserService.getUserAuthenticated();
        if (userDetailsImpl == null || !userDetailsImpl.hasHole(Perfil.ADMIN)|| id.equals(userDetailsImpl.getId())) {
            throw new AuthorizationException("Acesso negado");
        }
        Optional<Cliente> optionalCliente = clienteRepositoy.findById(id);
        return optionalCliente.orElseThrow(
            () -> new ObjectNotFoundException(String.format("Objeto %d não encontrado! Tipo: %s", id, Cliente.class.getName())));
    }

    /**
     * Insere um Cliente
     * @param cliente Cliente a ser inserido
     * @return O Cliente inserido
     */
    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        Cliente clienteSaved = clienteRepositoy.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecoList());
        return clienteSaved;
    }

    /**
     * Atualiza um Cliente
     * @param cliente Cliente a ser inserido
     * @return O Cliente atualizado
     */
    public Cliente update(Cliente cliente) {
        find(cliente.getId());
        Cliente newCliente = clienteRepositoy.getOne(cliente.getId());
        updateCliente(newCliente, cliente);
        return clienteRepositoy.save(newCliente);
    }

    /**
     * Deleta um Cliente por id
     * @param id Id do Cliente a ser deletado
     * @throws DataIntegrityException
     */
    public void delete(Integer id) {
        find(id);
        try {
            clienteRepositoy.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível remover o cliente, ele possuí produtos!");
        }
    }

    /**
     * Procura todos os Clientes
     * @return Uma lista de Clientes encontrados
     */
    public List<Cliente> findAll() {
        return clienteRepositoy.findAll();
    }

    /**
     * Procura Clientes e os retornam em um Page
     * @param page Número da página
     * @param linesPerPage Linhas da página
     * @param direction Direção da página
     * @param orderBy Ordem da página
     * @return Um Page com Clientes
     */
    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepositoy.findAll(pageRequest);
    }

    /**
     * Conversão de ClienteDTO1 para Cliete
     * @param clienteDTO1 Um ClienteDTO1
     * @return Um Cliente
     */
    public Cliente toCliente(ClienteDTO1 clienteDTO1) {
        return new Cliente(
                clienteDTO1.getId(), clienteDTO1.getNome(), clienteDTO1.getEmail(), null, null, null);
    }

    /**
     * Conversão de ClienteDTO2 para Cliente
     * @param clienteDTO2 Um ClienteDTO2
     * @return Um Cliente
     */
    public Cliente toCliente(ClienteDTO2 clienteDTO2) {
        Cliente cliente = new Cliente(
            null, clienteDTO2.getNome(), clienteDTO2.getEmail(), clienteDTO2.getCpfOuCnpj(),
            TipoCliente.toEnum(clienteDTO2.getTipoCliente()), bCryptPasswordEncoder.encode(clienteDTO2.getSenha()));
        Cidade cidade = new Cidade(clienteDTO2.getCidadeId(), null, null);
        Endereco endereco = new Endereco(null, clienteDTO2.getLogradouro(), clienteDTO2.getNumero(),
            clienteDTO2.getComplemento(), clienteDTO2.getBairro(), clienteDTO2.getCep(), cliente, cidade);
        cliente.getEnderecoList().add(endereco);
        cliente.getTelefonesSet().add(clienteDTO2.getTelefone1());
        if (clienteDTO2.getTelefone2() != null)
            cliente.getTelefonesSet().add(clienteDTO2.getTelefone2());
        if (clienteDTO2.getTelefone3() != null)
            cliente.getTelefonesSet().add(clienteDTO2.getTelefone3());
        return cliente;
    }

    /**
     * Método para atualizar atributos nome e email de um novo Cliente
     * @param newCliente Cliente com atributos a serem atualizados
     * @param cliente Cliente com novos atributos
     */
    private void updateCliente(Cliente newCliente, Cliente cliente) {
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
    }
}
