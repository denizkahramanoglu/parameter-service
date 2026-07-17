package com.example.parameter_service.service;

import com.example.parameter_service.dto.*;
import com.example.parameter_service.entity.CityEntity;
import com.example.parameter_service.entity.CountryEntity;
import com.example.parameter_service.entity.DistrictEntity;
import com.example.parameter_service.mapper.ParameterMapper;
import com.example.parameter_service.repository.CityRepository;
import com.example.parameter_service.repository.CountryRepository;
import com.example.parameter_service.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParameterService {

    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final ParameterMapper parameterMapper;
    private final CountryRepository countryRepository;

    public CityResponseDTO getCityById(Long id) {
        CityEntity city = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Şehir bulunamadı!"));

        return parameterMapper.toCityDto(city);
    }

    public DistrictResponseDTO getDistrictById(Long id) {
        DistrictEntity district = districtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("İlçe bulunamadı!"));

        return parameterMapper.toDistrictDto(district);
    }
    public CityResponseDTO saveCity(CityRequestDTO request) {
        CityEntity city = new CityEntity();
        city.setName(request.getName());
        city.setCountryId(request.getCountryId());
        cityRepository.save(city);
        return parameterMapper.toCityDto(city);
    }

    public CountryResponseDTO createCountry(CountryRequestDTO request) {
        CountryEntity entity = parameterMapper.toCountryEntity(request);
        CountryEntity savedEntity = countryRepository.save(entity);
        return parameterMapper.toCountryDto(savedEntity);
    }

    public CityResponseDTO createCity(CityRequestDTO request) {
        CityEntity entity = parameterMapper.toCityEntity(request);
        CityEntity savedEntity = cityRepository.save(entity);
        return parameterMapper.toCityDto(savedEntity);
    }

    public DistrictResponseDTO createDistrict(DistrictRequestDTO request) {
        DistrictEntity entity = parameterMapper.toDistrictEntity(request);
        DistrictEntity savedEntity = districtRepository.save(entity);
        return parameterMapper.toDistrictDto(savedEntity);
    }
    //feign kullanimi
    public FullLocationResponseDTO getFullLocationByDistrictId(Long districtId) {
        // 1. İlçeyi bul
        DistrictEntity district = districtRepository.findById(districtId)
                .orElseThrow(() -> new RuntimeException("İlçe bulunamadı!"));

        // 2. ilcenin icindeki city_id ile sehri bul
        CityEntity city = cityRepository.findById(district.getCityId())
                .orElseThrow(() -> new RuntimeException("Şehir bulunamadı!"));

        // 3. Şehrin içindeki country_id ile ülkeyi bul
        CountryEntity country = countryRepository.findById(city.getCountryId())
                .orElseThrow(() -> new RuntimeException("Ülke bulunamadı!"));

        // 4. Hepsini tek bir pakete koy ve yolla
        return FullLocationResponseDTO.builder()
                .countryName(country.getName())
                .cityName(city.getName())
                .districtName(district.getName())
                .build();
    }
}