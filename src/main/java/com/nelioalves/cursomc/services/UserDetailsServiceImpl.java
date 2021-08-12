package com.nelioalves.cursomc.services;

import java.util.Objects;
import com.nelioalves.cursomc.domain.Cliente;
import org.springframework.stereotype.Service;
import com.nelioalves.cursomc.security.UserDetailsImpl;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Classe de serviço com regras de negócio para UserDetails
 * @author José Henrique
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Cria um UserDetails a partir de um email de um Cliente
     * @param email Email do Cliente
     * @throws UsernameNotFoundException
     * @return Um UserDetails(UserDetailsImpl)
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (Objects.isNull(cliente)) {
            throw new UsernameNotFoundException(String.format("Usuário com id %d inválido!", cliente.getId()));
        }
        return new UserDetailsImpl(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfilSet());
    }
}
