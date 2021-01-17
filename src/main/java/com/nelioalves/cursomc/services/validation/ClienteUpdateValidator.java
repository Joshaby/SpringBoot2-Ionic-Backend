package com.nelioalves.cursomc.services.validation;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO1;
import com.nelioalves.cursomc.repositories.ClienteRepositoy;
import com.nelioalves.cursomc.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO1> {

    @Autowired
    private ClienteRepositoy clienteRepositoy;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public void initialize(ClienteUpdate constraintAnnotation) {

    }
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
