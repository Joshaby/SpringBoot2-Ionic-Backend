package com.nelioalves.cursomc.services.validation;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import javax.validation.ConstraintValidator;
import com.nelioalves.cursomc.domain.Cliente;
import javax.servlet.http.HttpServletRequest;
import com.nelioalves.cursomc.dto.ClienteDTO1;
import javax.validation.ConstraintValidatorContext;
import org.springframework.web.servlet.HandlerMapping;
import com.nelioalves.cursomc.repositories.ClienteRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import com.nelioalves.cursomc.resources.exceptions.FieldMessage;

/**
 * Classe de validação do ClienteDTO1 - Atualização de Clientes
 * @author José Henrique
 */
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO1> {

    @Autowired
    private ClienteRepositoy clienteRepositoy;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public void initialize(ClienteUpdate constraintAnnotation) {

    }

    /**
     * Método para validar os atributos de um ClienteDTO1
     * @param clienteDTO1 ClienteDTO1 a ser validado
     * @param constraintValidatorContext objeto onde seram guardados os erros da validação
     * @return Um boolean
     */
    @Override
    public boolean isValid(ClienteDTO1 clienteDTO1, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> fieldMessageList = new ArrayList<>();
        Map<String, String> uriVariables = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer id = Integer.parseInt(uriVariables.get("id"));
        Cliente cliente = clienteRepositoy.findByEmail(clienteDTO1.getEmail());
        if (cliente != null && ! cliente.getId().equals(id)) {
            fieldMessageList.add(new FieldMessage("email", "Email já existe!"));
        }
        for (FieldMessage fieldMessage : fieldMessageList) {
            constraintValidatorContext.getDefaultConstraintMessageTemplate();
            constraintValidatorContext.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
                .addPropertyNode(fieldMessage.getField()).addConstraintViolation();
        }
        return fieldMessageList.isEmpty();
    }
}
