package com.fernandocanabarro.desafio_ze_delivery.services.validations;

import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PartnerRequestDTO;
import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.exceptions.FieldMessage;
import com.fernandocanabarro.desafio_ze_delivery.domain.entities.Partner;
import com.fernandocanabarro.desafio_ze_delivery.repositories.PartnerRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;

public class PartnerRequestDTOValidator implements ConstraintValidator<PartnerRequestDTOValid,PartnerRequestDTO>{

    @Autowired
    private PartnerRepository repository;

    @Override
    public void initialize(PartnerRequestDTOValid ann){}

    @Override
    public boolean isValid(PartnerRequestDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> errors = new ArrayList<>();

        Optional<Partner> partner = repository.findByDocument(dto.getDocument());
        if (partner.isPresent()) {
            errors.add(new FieldMessage("document", "Já existe um Parceiro com esse documento"));
        }

        for (FieldMessage f : errors){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(f.getMessage())
                .addPropertyNode(f.getFieldName())
                .addConstraintViolation();
        }
        
        return errors.isEmpty();
    }

}
