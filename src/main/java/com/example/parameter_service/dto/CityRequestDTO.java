package com.example.parameter_service.dto;

import lombok.Data;

@Data
public class CityRequestDTO {
    private String name;
    private Long countryId;
}
