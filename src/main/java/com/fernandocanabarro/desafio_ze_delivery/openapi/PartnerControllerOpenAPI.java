package com.fernandocanabarro.desafio_ze_delivery.openapi;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PartnerListDTO;
import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PartnerRequestDTO;
import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PartnerResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

public interface PartnerControllerOpenAPI {

    @Operation(
        description = "Consultar Todos os Parceiros",
        summary = "Endpoint responsável por receber a requisição de Consultar Todos os Parceiros",
        responses = {
            @ApiResponse(description = "Consulta Realizada", responseCode = "200"),
        }
    )
    ResponseEntity<Page<PartnerListDTO>> findAll();

    @Operation(
    description = "Criar um Parceiro",
    summary = "Endpoint responsável por receber a requisição de Criar um Parceiro",
    responses = {
        @ApiResponse(description = "Parceiro Criado", responseCode = "201"),
        @ApiResponse(description = "Algum Campo do Parceiro está Inválido ou o Documento já existe", responseCode = "422")
   		}
	)
    ResponseEntity<PartnerResponseDTO> insert(@RequestBody @Valid PartnerRequestDTO dto);

    @Operation(
    description = "Consultar Parceiro por Id",
    summary = "Endpoint responsável por receber a requisição de Consultar Parceiro por Id",
    responses = {
        @ApiResponse(description = "Consulta Realizada", responseCode = "200"),
        @ApiResponse(description = "Parceiro Não Encontrado", responseCode = "404"),
   		}
	)
    ResponseEntity<PartnerResponseDTO> findById(@PathVariable String id);

    @Operation(
    description = "Consultar Parceiro por Coordenadas",
    summary = "Endpoint responsável por receber a requisição de Consultar Parceiro por Coordenadas",
    responses = {
         @ApiResponse(description = "Consulta Realizada", responseCode = "200"),
         @ApiResponse(description = "Nenhum Parceiro Encontrado dentro da Área de Cobertura", responseCode = "404")
   		}
	)
    ResponseEntity<PartnerResponseDTO> searchByCoordinatesWithinCoverageArea(
        @RequestParam(name = "longitude") String longitude, @RequestParam(name = "latitude") String latitude
    );

}
