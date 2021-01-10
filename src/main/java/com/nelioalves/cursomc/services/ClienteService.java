package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repositories.ClienteRepositoy;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
