package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositoy extends JpaRepository<Cliente, Integer> {

    Cliente findByEmail(String email);
}
