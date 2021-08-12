package com.nelioalves.cursomc.services.validation;

import java.util.List;
import java.util.ArrayList;
import javax.validation.ConstraintValidator;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO2;
import javax.validation.ConstraintValidatorContext;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.services.utils.ServicesUtils;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.nelioalves.cursomc.resources.exceptions.FieldMessage;

/**
 * Classe de validação de um ClienteDTO2 - Inserção de Clientes
 * @author José Henrique
 */
public class ClienteInsertValidator implements ConstraintValidator<ClienteValidator, ClienteDTO2> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteValidator ann) {

    }

    /**
     * Método para validar os atributos de um ClienteDTO2
     * @param clienteDTO2 ClienteDTO2 a ser validado
     * @param constraintValidatorContext objeto onde seram guardados os erros da validação
     * @return Um boolean
     */
    @Override
    public boolean isValid(ClienteDTO2 clienteDTO2, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> list = new ArrayList<>();
        if (clienteDTO2.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getTipo())
            && !ServicesUtils.isCPF(clienteDTO2.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido!"));
        }
        if (clienteDTO2.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getTipo())
            && !ServicesUtils.isCNPJ(clienteDTO2.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido!"));
        }
        Cliente cliente = clienteRepository.findByEmail(clienteDTO2.getEmail());
        if (cliente != null)
            list.add(new FieldMessage("email", "Email já existe!"));
        for (FieldMessage e : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage())
                .addPropertyNode(e.getField()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
