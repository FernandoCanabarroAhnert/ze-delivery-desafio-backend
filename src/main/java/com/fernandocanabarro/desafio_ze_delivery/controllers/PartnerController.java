package com.fernandocanabarro.desafio_ze_delivery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PartnerListDTO;
import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PartnerRequestDTO;
import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PartnerResponseDTO;
import com.fernandocanabarro.desafio_ze_delivery.services.PartnerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/partners")
public class PartnerController {

    @Autowired
    private PartnerService service;

    @GetMapping
    public ResponseEntity<Page<PartnerListDTO>> findAll(){
        return null;
    }

    @PostMapping
    public ResponseEntity<PartnerResponseDTO> insert(@RequestBody @Valid PartnerRequestDTO dto){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartnerResponseDTO> findById(@PathVariable String id){
        return null;
    }

    @GetMapping("/geo")
    public ResponseEntity<PartnerResponseDTO> searchByCoordinatesWithinCoverageArea(
        @RequestParam(name = "longitude") String longitude, @RequestParam(name = "latitude") String latitude
    ){
        return null;
    }
}
