package com.example.parameter_service.service;

import com.example.parameter_service.dto.CurrencyDTO;
import com.example.parameter_service.entity.CurrencyEntity;
import com.example.parameter_service.exception.BusinessException;
import com.example.parameter_service.mapper.CurrencyMapper;
import com.example.parameter_service.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;


        public List<CurrencyDTO> getAllCurrencies() {
            List<CurrencyEntity> entities = currencyRepository.findAll();
            return currencyMapper.toDTOList(entities);
        }

        public CurrencyDTO getCurrencyByCode(String code) {
            return currencyRepository.findById(code)
                    .map(currencyMapper::toDTO)
                    .orElseThrow(() -> new BusinessException("Kayıtlı para birimi bulunamadı. Kod: " + code, HttpStatus.NOT_FOUND));
        }
    }