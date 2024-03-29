package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * Classe de serviço para geração de data de vencimento de um boleto
 * @author José Henrique
 */
@Service
public class BoletoService {

    /**
     * Retorna um PagamentoComBoleto com data de vencimento
     * @param pagamentoComBoleto Um PagamentoComBoleto
     * @param instantOfOrder Data de vencimento
     */
    public void generateDueDate(PagamentoComBoleto pagamentoComBoleto, Date instantOfOrder) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instantOfOrder);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        pagamentoComBoleto.setDataPagamento(calendar.getTime());
    }
}
