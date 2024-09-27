package com.fernandocanabarro.desafio_ze_delivery.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.stereotype.Service;

import com.fernandocanabarro.desafio_ze_delivery.controllers.PartnerController;
import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PartnerListDTO;
import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PartnerRequestDTO;
import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PartnerResponseDTO;
import com.fernandocanabarro.desafio_ze_delivery.domain.entities.Partner;
import com.fernandocanabarro.desafio_ze_delivery.repositories.PartnerRepository;
import com.fernandocanabarro.desafio_ze_delivery.services.exceptions.ResourceNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository repository;

    public Page<PartnerListDTO> findAll() {
        return repository.findAll(PageRequest.of(0, 10)).map(x -> new PartnerListDTO(x)
            .add(linkTo(methodOn(PartnerController.class).findById(x.getId())).withRel("Consultar Parceiro por Id")));
    }

    public PartnerResponseDTO insert(PartnerRequestDTO dto) {
        Partner obj = toEntity(dto);
        return new PartnerResponseDTO(repository.save(obj));
    }

    private Partner toEntity(PartnerRequestDTO dto) {
        Partner partner = new Partner();
        partner.setTradingName(dto.getTradingName());
        partner.setOwnerName(dto.getOwnerName());
        partner.setDocument(dto.getDocument());
        List<GeoJsonPolygon> list = new ArrayList<>();
        for (List<List<Double[]>> obj : dto.getCoverageArea()) {
            for (List<Double[]> x : obj) {
                list.add(new GeoJsonPolygon(x.stream().map(point -> new Point(point[0], point[1])).toList()));
            }
        }
        partner.setCoverageArea(new GeoJsonMultiPolygon(list));
        partner.setAddress(
                new GeoJsonPoint(new Point(dto.getAddress().getLongitude(), dto.getAddress().getLatitude())));
        return partner;
    }

    public PartnerResponseDTO findById(String id) {
        Partner obj = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id));
        return new PartnerResponseDTO(obj);
    }

    public PartnerResponseDTO searchByCoordinatesWithinCoverageArea(String longitude, String latitude) {
        return new PartnerResponseDTO(
                repository.searchByCoordinatesWithinACoverageArea(Double.parseDouble(longitude),Double.parseDouble(latitude))
                .orElseThrow(() -> new ResourceNotFoundException(longitude, latitude)));
    }
}
