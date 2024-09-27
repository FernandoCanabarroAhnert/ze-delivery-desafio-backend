package com.fernandocanabarro.desafio_ze_delivery.controllers;

import java.net.URI;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<PartnerResponseDTO> insert(@RequestBody @Valid PartnerRequestDTO dto){
        PartnerResponseDTO response = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartnerResponseDTO> findById(@PathVariable String id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/geo")
    public ResponseEntity<PartnerResponseDTO> searchByCoordinatesWithinCoverageArea(
        @RequestParam(name = "longitude") String longitude, @RequestParam(name = "latitude") String latitude
    ){
        return ResponseEntity.ok(service.searchByCoordinatesWithinCoverageArea(longitude,latitude));
    }
}
