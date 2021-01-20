package com.nelioalves.cursomc.services.validation;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.dto.ClienteDTO2;
import com.nelioalves.cursomc.repositories.ClienteRepositoy;
import com.nelioalves.cursomc.resources.exceptions.FieldMessage;
import com.nelioalves.cursomc.services.utils.ServicesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteValidator, ClienteDTO2> {

    @Autowired
    private ClienteRepositoy clienteRepositoy;

    @Override
    public void initialize(ClienteValidator ann) {

    }
    @Override
    public boolean isValid(ClienteDTO2 clienteDTO2, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        if (clienteDTO2.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getTipo())
            && !ServicesUtils.isCPF(clienteDTO2.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido!"));
        }
        if (clienteDTO2.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getTipo())
            && !ServicesUtils.isCNPJ(clienteDTO2.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido!"));
        }
        Cliente cliente = clienteRepositoy.findByEmail(clienteDTO2.getEmail());
        if (cliente != null)
            list.add(new FieldMessage("email", "Email já existe!"));
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                .addPropertyNode(e.getField()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
