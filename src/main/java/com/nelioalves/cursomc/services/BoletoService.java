package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void generateDueDate(PagamentoComBoleto pagamentoComBoleto, Date instantOfOrder) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instantOfOrder);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        pagamentoComBoleto.setDataPagamento(calendar.getTime());
    }
}
