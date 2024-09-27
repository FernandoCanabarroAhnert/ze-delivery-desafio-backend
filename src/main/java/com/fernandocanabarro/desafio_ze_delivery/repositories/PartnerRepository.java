package com.fernandocanabarro.desafio_ze_delivery.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fernandocanabarro.desafio_ze_delivery.domain.entities.Partner;
import java.util.Optional;

@Repository
public interface PartnerRepository extends MongoRepository<Partner,String>{

    @Query("{ coverageArea: { $geoIntersects: { $geometry: { type: 'Point', coordinates: [ ?0, ?1] }  } } }")
    Optional<Partner> searchByCoordinatesWithinACoverageArea(double longitude,double latitude);

    Optional<Partner> findByDocument(String document);
}
