package com.fernandocanabarro.desafio_ze_delivery.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PartnerListDTO;
import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PartnerRequestDTO;
import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PartnerResponseDTO;
import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PointDTO;
import com.fernandocanabarro.desafio_ze_delivery.domain.entities.Partner;

import com.fernandocanabarro.desafio_ze_delivery.repositories.PartnerRepository;
import com.fernandocanabarro.desafio_ze_delivery.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PartnerServiceTests {

    @InjectMocks
    private PartnerService service;
    @Mock
    private PartnerRepository repository;

    private Partner partner;
    private PartnerRequestDTO dto;
    private String existingId,nonExistingId;
    
    @BeforeEach
    public void setup() throws Exception{
        existingId = "1";
        nonExistingId = "2";

        GeoJsonPoint point = new GeoJsonPoint(new Point(1, 1));
        GeoJsonPolygon polygon = new GeoJsonPolygon(List.of(point));
        List<GeoJsonPolygon> polygons = new ArrayList<>(Arrays.asList(polygon));

        Double[] points = {1.0,1.0};
        List<Double[]> innerList = new ArrayList<>();
        innerList.add(points);
        List<List<Double[]>> middleList = new ArrayList<>();
        middleList.add(innerList);
        List<List<List<Double[]>>> coverageArea = new ArrayList<>();
        coverageArea.add(middleList);

        partner = new Partner(existingId, "name", "owner", "document", 
            new GeoJsonMultiPolygon(polygons), new GeoJsonPoint(point));
        dto = new PartnerRequestDTO("name", "owner", "document", 
            coverageArea, new PointDTO(1.0, 1.0));
    }

    @Test
    public void findAllShouldReturnPageOfPartnerListDTO(){
        when(repository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(partner)));

        Page<PartnerListDTO> response = service.findAll();
        
        assertThat(response).isNotEmpty();
        assertThat(response.getContent().get(0).getTradingName()).isEqualTo("name");
        assertThat(response.getContent().get(0).getOwnerName()).isEqualTo("owner");
        assertThat(response.getContent().get(0).getDocument()).isEqualTo("document");
    }

    @Test
    public void insertShouldReturnPartnerResponseDTO(){
        when(repository.save(any(Partner.class))).thenReturn(partner);

        PartnerResponseDTO response = service.insert(dto);

        assertThat(response).isNotNull();
        assertThat(response.getTradingName()).isEqualTo("name");
        assertThat(response.getOwnerName()).isEqualTo("owner");
        assertThat(response.getDocument()).isEqualTo("document");
    }

    @Test
    public void findByIdShouldReturnPartnerResponseDTOWhenIdExists(){
        when(repository.findById(existingId)).thenReturn(Optional.of(partner));

        PartnerResponseDTO response = service.findById(existingId);

        assertThat(response).isNotNull();
        assertThat(response.getTradingName()).isEqualTo("name");
        assertThat(response.getOwnerName()).isEqualTo("owner");
        assertThat(response.getDocument()).isEqualTo("document");
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist(){
        when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(nonExistingId)).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void searchByCoordinatesWithinCoverageAreaShouldReturnPartnerResponseDTOWhenIdExists(){
        when(repository.searchByCoordinatesWithinACoverageArea(1, 1)).thenReturn(Optional.of(partner));

        PartnerResponseDTO response = service.searchByCoordinatesWithinCoverageArea("1","1");

        assertThat(response).isNotNull();
        assertThat(response.getTradingName()).isEqualTo("name");
        assertThat(response.getOwnerName()).isEqualTo("owner");
        assertThat(response.getDocument()).isEqualTo("document");
    }

    @Test
    public void searchByCoordinatesWithinCoverageAreaShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist(){
        when(repository.searchByCoordinatesWithinACoverageArea(1, 1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.searchByCoordinatesWithinCoverageArea("1","1")).isInstanceOf(ResourceNotFoundException.class);
    }
}
