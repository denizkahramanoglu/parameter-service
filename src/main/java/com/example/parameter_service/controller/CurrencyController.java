package com.example.parameter_service.controller;

import com.example.parameter_service.dto.CurrencyDTO;
import com.example.parameter_service.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<List<CurrencyDTO>> getAll() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }

    @GetMapping("/{code}")
    public ResponseEntity<CurrencyDTO> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(currencyService.getCurrencyByCode(code));
    }
}