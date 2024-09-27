package com.fernandocanabarro.desafio_ze_delivery.domain.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "partners")
public class Partner {

    @Id
    private String id;
    private String tradingName;
    private String ownerName;
    private String document;
    @GeoSpatialIndexed
    @Field("coverageArea")
    private GeoJsonMultiPolygon coverageArea;
    @GeoSpatialIndexed
    @Field("address")
    private GeoJsonPoint address;
}
