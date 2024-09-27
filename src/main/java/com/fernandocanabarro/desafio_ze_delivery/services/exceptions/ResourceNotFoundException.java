package com.fernandocanabarro.desafio_ze_delivery.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String id){
        super("Parceiro não Encontrado! Id = " + id);
    }

    public ResourceNotFoundException(String longitude,String latitude){
        super("Nenhum Parceiro encontrado dentro desta área de cobertura! Longitude = " + longitude + " | Latitude = " + latitude);
    }
}
