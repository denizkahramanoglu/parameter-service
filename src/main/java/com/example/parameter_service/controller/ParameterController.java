package com.example.parameter_service.controller;

import com.example.parameter_service.dto.*;
import com.example.parameter_service.service.ParameterService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/parameters")
@RequiredArgsConstructor
public class ParameterController {

    private final ParameterService parameterService;

    // Feign Kullanımı
    @Operation(summary = "Ülke şehir ve ilçe adını paketleyip customer servise dönüyor.")
    @GetMapping("/locations/full-address/{districtId}")
    public ResponseEntity<FullLocationResponseDTO> getFullLocation(@PathVariable Long districtId) {
        return ResponseEntity.ok(parameterService.getFullLocationByDistrictId(districtId));
    }

    @Operation(summary = "Şehiri getirme")
    @GetMapping("/cities/{id}")
    public ResponseEntity<CityResponseDTO> getCity(@PathVariable Long id) {
        return ResponseEntity.ok(parameterService.getCityById(id));
    }
    @Operation(summary = "İlçe getirme")
    @GetMapping("/districts/{id}")
    public ResponseEntity<DistrictResponseDTO> getDistrict(@PathVariable Long id) {
        return ResponseEntity.ok(parameterService.getDistrictById(id));
    }
    @Operation(summary = "Ülkeye bağlı şehir ID si oluşturma")
    @PostMapping("/cities")
    public ResponseEntity<CityResponseDTO> createCity(@RequestBody CityRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(parameterService.createCity(request));
    }
    @Operation(summary = "Ülke ID si oluşturma")
    @PostMapping("/countries")
    public ResponseEntity<CountryResponseDTO> createCountry(@RequestBody CountryRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(parameterService.createCountry(request));
    }
    @Operation(summary = "İl ID sine göre ilçe oluşturma")
    @PostMapping("/districts")
    public ResponseEntity<DistrictResponseDTO> createDistrict(@RequestBody DistrictRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(parameterService.createDistrict(request));
    }
}