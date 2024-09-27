package com.fernandocanabarro.desafio_ze_delivery.domain.dtos;

import java.util.List;

import com.fernandocanabarro.desafio_ze_delivery.services.validations.PartnerRequestDTOValid;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PartnerRequestDTOValid
public class PartnerRequestDTO {

    @NotBlank(message = "Campo Requerido")
    private String tradingName;
    @NotBlank(message = "Campo Requerido")
    private String ownerName;
    @NotBlank(message = "Campo Requerido")
    private String document;
    @NotEmpty(message = "Campo Requerido")
    private List<List<List<Double[]>>> coverageArea;
    private PointDTO address;

   
}
