package com.example.parameter_service.service;

import com.example.parameter_service.dto.*;
import com.example.parameter_service.entity.CityEntity;
import com.example.parameter_service.entity.CountryEntity;
import com.example.parameter_service.entity.DistrictEntity;
import com.example.parameter_service.exception.BusinessException;
import com.example.parameter_service.mapper.ParameterMapper;
import com.example.parameter_service.repository.CityRepository;
import com.example.parameter_service.repository.CountryRepository;
import com.example.parameter_service.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParameterService {

    private static final Logger logger = LoggerFactory.getLogger(ParameterService.class);

    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final ParameterMapper parameterMapper;
    private final CountryRepository countryRepository;

    public CityResponseDTO getCityById(Long id) {
        CityEntity city = cityRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Şehir bulunamadı! Geçersiz ID: " + id, HttpStatus.NOT_FOUND));

        return parameterMapper.toCityDto(city);
    }

    public DistrictResponseDTO getDistrictById(Long id) {
        DistrictEntity district = districtRepository.findById(id)
                .orElseThrow(() -> new BusinessException("İlçe bulunamadı! Geçersiz ID: " + id, HttpStatus.NOT_FOUND));

        return parameterMapper.toDistrictDto(district);
    }

    public CountryResponseDTO createCountry(CountryRequestDTO request) {
        CountryEntity entity = parameterMapper.toCountryEntity(request);
        CountryEntity savedEntity = countryRepository.save(entity);
        logger.info("Yeni ülke kaydedildi. Ülke ID: {}", savedEntity.getId());
        return parameterMapper.toCountryDto(savedEntity);
    }

    public CityResponseDTO createCity(CityRequestDTO request) {
        CityEntity entity = parameterMapper.toCityEntity(request);
        CityEntity savedEntity = cityRepository.save(entity);
        logger.info("Yeni şehir kaydedildi. Şehir ID: {}", savedEntity.getId());
        return parameterMapper.toCityDto(savedEntity);
    }

    public DistrictResponseDTO createDistrict(DistrictRequestDTO request) {
        DistrictEntity entity = parameterMapper.toDistrictEntity(request);
        DistrictEntity savedEntity = districtRepository.save(entity);
        logger.info("Yeni ilçe kaydedildi. İlçe ID: {}", savedEntity.getId());
        return parameterMapper.toDistrictDto(savedEntity);
    }

    public FullLocationResponseDTO getFullLocationByDistrictId(Long districtId) {

        DistrictEntity district = districtRepository.findById(districtId)
                .orElseThrow(() -> new BusinessException("İlçe bulunamadı! Geçersiz İlçe ID: " + districtId, HttpStatus.NOT_FOUND));

        CityEntity city = cityRepository.findById(district.getCityId())
                .orElseThrow(() -> new BusinessException(
                        "İlçe bulundu ancak bağlı olduğu şehir bulunamadı! Hatalı Şehir ID: " + district.getCityId(),
                        HttpStatus.NOT_FOUND
                ));

        CountryEntity country = countryRepository.findById(city.getCountryId())
                .orElseThrow(() -> new BusinessException(
                        "Şehir bulundu ancak bağlı olduğu ülke bulunamadı! Hatalı Ülke ID: " + city.getCountryId(),
                        HttpStatus.NOT_FOUND
                ));

        return FullLocationResponseDTO.builder()
                .countryName(country.getName())
                .cityName(city.getName())
                .districtName(district.getName())
                .build();
    }
}