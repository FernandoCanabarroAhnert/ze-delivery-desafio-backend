package com.fernandocanabarro.desafio_ze_delivery.domain.dtos;

import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import com.fernandocanabarro.desafio_ze_delivery.domain.entities.Partner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartnerResponseDTO {

    private String id;
    private String tradingName;
    private String ownerName;
    private String document;
    private GeoJsonMultiPolygon coverageArea;
    private GeoJsonPoint address;

    public PartnerResponseDTO(Partner entity) {
        id = entity.getId();
        tradingName = entity.getTradingName();
        ownerName = entity.getOwnerName();
        document = entity.getDocument();
        coverageArea = entity.getCoverageArea();
        address = entity.getAddress();
    }
}
