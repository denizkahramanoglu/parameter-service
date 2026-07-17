package com.example.parameter_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullLocationResponseDTO {
    private String countryName;
    private String cityName;
    private String districtName;
}
