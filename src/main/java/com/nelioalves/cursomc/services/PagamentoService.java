package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Pagamento;
import com.nelioalves.cursomc.repositories.PagamentoRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe de serviço com regras de negócio para Pagamentos
 * @author José Henrique
 */
@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    /**
     * Procura um Pagamento por id
     * @param id Id do Pagamento a ser procurado
     * @throws ObjectNotFoundException
     * @return O Pagamento encontrado
     */
    public Pagamento find(Integer id) {
        Optional<Pagamento> optionalPagamento = pagamentoRepository.findById(id);
        return optionalPagamento.orElseThrow(
            () -> new ObjectNotFoundException(String.format("Objeto %d não encontrado! Tipo: %s", id, Pagamento.class.getName())));
    }
}
