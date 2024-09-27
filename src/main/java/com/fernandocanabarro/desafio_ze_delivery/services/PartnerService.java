package com.fernandocanabarro.desafio_ze_delivery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PartnerListDTO;
import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PartnerRequestDTO;
import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PartnerResponseDTO;
import com.fernandocanabarro.desafio_ze_delivery.domain.entities.Partner;
import com.fernandocanabarro.desafio_ze_delivery.repositories.PartnerRepository;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository repository;

    public Page<PartnerListDTO> findAll() {
        return null;
    }

    public PartnerResponseDTO insert(PartnerRequestDTO dto) {
        return null;
    }

    private Partner toEntity(PartnerRequestDTO dto) {
        return null;
    }

    public PartnerResponseDTO findById(String id) {
        return null;
    }

    public PartnerResponseDTO searchByCoordinatesWithinCoverageArea(String longitude, String latitude) {
        return null;
    }
}
