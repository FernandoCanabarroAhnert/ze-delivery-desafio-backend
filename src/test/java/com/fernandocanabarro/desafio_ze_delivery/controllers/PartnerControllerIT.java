package com.fernandocanabarro.desafio_ze_delivery.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import com.fernandocanabarro.desafio_ze_delivery.domain.entities.Partner;

import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PartnerRequestDTO;
import com.fernandocanabarro.desafio_ze_delivery.domain.dtos.PointDTO;
import java.util.List;
import java.util.ArrayList;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PartnerControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MongoTemplate mongoTemplate;

    private List<String> documentsIds = new ArrayList<>();
    private String existingId,nonExistingId;
    private String latitude,longitude;
    private PartnerRequestDTO dto;

    @BeforeEach
    public void setup() throws Exception{
        existingId = "66f741fe0a11277fa4eb17bb";
        nonExistingId = "fhajhaj";
        latitude = "-25.54249";
        longitude = "-49.30925";

        Double[] points = {1.0,1.0};
        List<Double[]> innerList = new ArrayList<>();
        innerList.add(points);
        List<List<Double[]>> middleList = new ArrayList<>();
        middleList.add(innerList);
        List<List<List<Double[]>>> coverageArea = new ArrayList<>();
        coverageArea.add(middleList);
        dto = new PartnerRequestDTO("name", "owner", "document", 
            coverageArea, new PointDTO(1.0, 1.0));
    }

    @AfterEach
    public void cleanUp(){
        for (String id : documentsIds){
            mongoTemplate.remove(new Query(Criteria.where("_id").is(id)),Partner.class);
        }
    }

    @Test
    public void findAllShouldReturnHttpStatus200() throws Exception{
        mockMvc.perform(get("/partners")
            .accept(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].tradingName").value("Ze Repoe"))
            .andExpect(jsonPath("$.content[0].ownerName").value("Eduardo Pipoca"))
            .andExpect(jsonPath("$.content[0].document").value("15.562.297/0001-56"));
    }

    @Test
    public void findByIdShouldReturnHttpStatus200WhenIdExists() throws Exception{
        mockMvc.perform(get("/partners/{id}",existingId)
            .accept(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.tradingName").value("Ze Repoe"))
            .andExpect(jsonPath("$.ownerName").value("Eduardo Pipoca"))
            .andExpect(jsonPath("$.document").value("15.562.297/0001-56"));
    }

    @Test
    public void findByIdShouldReturnHttpStatus404WhenIdDoesNotExist() throws Exception{
        mockMvc.perform(get("/partners/{id}",nonExistingId)
            .accept(APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void searchByCoordinatesWithinCoverageAreaShouldReturnHttpStatus200WhenIdExists() throws Exception{
        mockMvc.perform(get("/partners/geo?longitude={long}&latitude={lat}",longitude,latitude)
            .accept(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.tradingName").value("Ze Repoe"))
            .andExpect(jsonPath("$.ownerName").value("Eduardo Pipoca"))
            .andExpect(jsonPath("$.document").value("15.562.297/0001-56"));
    }

    @Test
    public void searchByCoordinatesWithinCoverageAreaShouldReturnHttpStatus404WhenIdDoesNotExist() throws Exception{
        mockMvc.perform(get("/partners/geo?longitude={long}&latitude={lat}","12","1")
            .accept(APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void insertShouldReturnHttpStatus201WhenDataIsValid() throws Exception{
        ResultActions result = mockMvc.perform(post("/partners")
            .content(objectMapper.writeValueAsString(dto))
            .contentType(APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.tradingName").value("name"))
            .andExpect(jsonPath("$.ownerName").value("owner"))
            .andExpect(jsonPath("$.document").value("document"));
        String responseContent = result.andReturn().getResponse().getContentAsString();
        String documentId = JsonPath.parse(responseContent).read("$.id");
        documentsIds.add(documentId);
    }

    @Test
    public void insertShouldReturnHttpStatus422WhenTradingNameIsBlank() throws Exception{
        dto.setTradingName("");
        mockMvc.perform(post("/partners")
            .content(objectMapper.writeValueAsString(dto))
            .contentType(APPLICATION_JSON))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(jsonPath("$.errors[0].fieldName").value("tradingName"))
            .andExpect(jsonPath("$.errors[0].message").value("Campo Requerido"));
    }

    
    @Test
    public void insertShouldReturnHttpStatus422WhenOwnerNameIsBlank() throws Exception{
        dto.setOwnerName("");
        mockMvc.perform(post("/partners")
            .content(objectMapper.writeValueAsString(dto))
            .contentType(APPLICATION_JSON))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(jsonPath("$.errors[0].fieldName").value("ownerName"))
            .andExpect(jsonPath("$.errors[0].message").value("Campo Requerido"));
    }

    
    @Test
    public void insertShouldReturnHttpStatus422WhenDocumentIsBlank() throws Exception{
        dto.setDocument("");
        mockMvc.perform(post("/partners")
            .content(objectMapper.writeValueAsString(dto))
            .contentType(APPLICATION_JSON))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(jsonPath("$.errors[0].fieldName").value("document"))
            .andExpect(jsonPath("$.errors[0].message").value("Campo Requerido"));
    }

    @Test
    public void insertShouldReturnHttpStatus422WhenDocumentAlreadyExists() throws Exception{
        dto.setDocument("15.562.297/0001-56");
        mockMvc.perform(post("/partners")
            .content(objectMapper.writeValueAsString(dto))
            .contentType(APPLICATION_JSON))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(jsonPath("$.errors[0].fieldName").value("document"))
            .andExpect(jsonPath("$.errors[0].message").value("Já existe um Parceiro com esse documento"));
    }

    @Test
    public void insertShouldReturnHttpStatus422WhenCoverageAreaIsBlank() throws Exception{
        dto.setCoverageArea(new ArrayList<>());
        mockMvc.perform(post("/partners")
            .content(objectMapper.writeValueAsString(dto))
            .contentType(APPLICATION_JSON))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(jsonPath("$.errors[0].fieldName").value("coverageArea"))
            .andExpect(jsonPath("$.errors[0].message").value("Campo Requerido"));
    }
}
