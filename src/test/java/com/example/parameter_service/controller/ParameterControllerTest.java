package com.example.parameter_service.controller;


import com.example.parameter_service.dto.*;
import com.example.parameter_service.service.ParameterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParameterControllerTest {

    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private ParameterController parameterController;

    @Test
    @DisplayName("Geçerli ilçe ID'si ile tam adres istendiğinde 200 OK ve adres verisi dönmeli")
    void getFullLocation_shouldReturn200AndLocationData_whenDistrictIdIsValid() {
        FullLocationResponseDTO mockResponse = new FullLocationResponseDTO();

        when(parameterService.getFullLocationByDistrictId(1L))
                .thenReturn(mockResponse);

        ResponseEntity<FullLocationResponseDTO> response = parameterController.getFullLocation(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(parameterService).getFullLocationByDistrictId(1L);
    }

    @Test
    @DisplayName("Geçerli şehir ID'si ile şehir istendiğinde 200 OK ve şehir verisi dönmeli")
    void getCity_shouldReturn200AndCityData_whenIdIsValid() {
        CityResponseDTO mockResponse = new CityResponseDTO();

        when(parameterService.getCityById(1L))
                .thenReturn(mockResponse);

        ResponseEntity<CityResponseDTO> response = parameterController.getCity(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(parameterService).getCityById(1L);
    }

    @Test
    @DisplayName("Geçerli ilçe ID'si ile ilçe istendiğinde 200 OK ve ilçe verisi dönmeli")
    void getDistrict_shouldReturn200AndDistrictData_whenIdIsValid() {
        DistrictResponseDTO mockResponse = new DistrictResponseDTO();

        when(parameterService.getDistrictById(1L))
                .thenReturn(mockResponse);

        ResponseEntity<DistrictResponseDTO> response = parameterController.getDistrict(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(parameterService).getDistrictById(1L);
    }

    @Test
    @DisplayName("Geçerli verilerle şehir oluşturulmak istendiğinde 201 Created ve şehir verisi dönmeli")
    void createCity_shouldReturn201AndCityData_whenRequestIsValid() {
        CityRequestDTO request = new CityRequestDTO();
        CityResponseDTO mockResponse = new CityResponseDTO();

        when(parameterService.createCity(request))
                .thenReturn(mockResponse);

        ResponseEntity<CityResponseDTO> response = parameterController.createCity(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(parameterService).createCity(request);
    }

    @Test
    @DisplayName("Geçerli verilerle ülke oluşturulmak istendiğinde 201 Created ve ülke verisi dönmeli")
    void createCountry_shouldReturn201AndCountryData_whenRequestIsValid() {
        CountryRequestDTO request = new CountryRequestDTO();
        CountryResponseDTO mockResponse = new CountryResponseDTO();

        when(parameterService.createCountry(request))
                .thenReturn(mockResponse);

        ResponseEntity<CountryResponseDTO> response = parameterController.createCountry(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(parameterService).createCountry(request);
    }

    @Test
    @DisplayName("Geçerli verilerle ilçe oluşturulmak istendiğinde 201 Created ve ilçe verisi dönmeli")
    void createDistrict_shouldReturn201AndDistrictData_whenRequestIsValid() {
        DistrictRequestDTO request = new DistrictRequestDTO();
        DistrictResponseDTO mockResponse = new DistrictResponseDTO();

        when(parameterService.createDistrict(request))
                .thenReturn(mockResponse);

        ResponseEntity<DistrictResponseDTO> response = parameterController.createDistrict(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(parameterService).createDistrict(request);
    }
}