package com.fernandocanabarro.desafio_ze_delivery.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PointDTO {

    @NotNull(message = "Campo Requerido")
    private Double longitude;
    @NotNull(message = "Campo Requerido")
    private Double latitude;
}
