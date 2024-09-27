package com.fernandocanabarro.desafio_ze_delivery.domain.dtos;

import java.util.List;

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
