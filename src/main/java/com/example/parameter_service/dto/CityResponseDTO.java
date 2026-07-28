package com.example.parameter_service.dto;

import lombok.Data;

@Data
public class CityResponseDTO {
    private Long id;
    private String name;
    private Long countryId;
}