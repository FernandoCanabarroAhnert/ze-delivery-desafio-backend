package com.fernandocanabarro.desafio_ze_delivery.domain.dtos;

import org.springframework.hateoas.RepresentationModel;

import com.fernandocanabarro.desafio_ze_delivery.domain.entities.Partner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartnerListDTO extends RepresentationModel<PartnerListDTO>{

    private String id;
    private String tradingName;
    private String ownerName;
    private String document;

    public PartnerListDTO(Partner entity){
        id = entity.getId();
        tradingName = entity.getTradingName();
        ownerName = entity.getOwnerName();
        document = entity.getDocument();
    }
}
