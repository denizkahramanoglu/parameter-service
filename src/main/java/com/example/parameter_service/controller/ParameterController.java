package com.example.parameter_service.controller;

import com.example.parameter_service.dto.*;
import com.example.parameter_service.service.ParameterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/parameters")
@RequiredArgsConstructor
public class ParameterController {

    private final ParameterService parameterService;

    @GetMapping("/cities/{id}")
    public ResponseEntity<CityResponseDTO> getCity(@PathVariable Long id) {
        return ResponseEntity.ok(parameterService.getCityById(id));
    }

    @GetMapping("/districts/{id}")
    public ResponseEntity<DistrictResponseDTO> getDistrict(@PathVariable Long id) {
        return ResponseEntity.ok(parameterService.getDistrictById(id));
    }

    @PostMapping("/cities")
    public ResponseEntity<CityResponseDTO> createCity(@RequestBody CityRequestDTO request) {
        // Yeni kaynak (Şehir) yaratıldığı için 201 CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(parameterService.createCity(request));
    }

    @PostMapping("/countries")
    public ResponseEntity<CountryResponseDTO> createCountry(@RequestBody CountryRequestDTO request) {
        // Yeni kaynak (Ülke) yaratıldığı için 201 CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(parameterService.createCountry(request));
    }

    @PostMapping("/districts")
    public ResponseEntity<DistrictResponseDTO> createDistrict(@RequestBody DistrictRequestDTO request) {
        // Yeni kaynak (İlçe) yaratıldığı için 201 CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(parameterService.createDistrict(request));
    }

    // Feign Kullanımı
    @GetMapping("/locations/full-address/{districtId}")
    public ResponseEntity<FullLocationResponseDTO> getFullLocation(@PathVariable Long districtId) {
        return ResponseEntity.ok(parameterService.getFullLocationByDistrictId(districtId));
    }
}