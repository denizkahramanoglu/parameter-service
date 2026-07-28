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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParameterServiceTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private DistrictRepository districtRepository;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private ParameterMapper parameterMapper;

    @InjectMocks
    private ParameterService parameterService;

    @Test
    void getCityById_ShouldReturnCity() {

        Long id = 1L;

        CityEntity city = new CityEntity();
        city.setId(id);

        CityResponseDTO response = new CityResponseDTO();
        response.setId(id);

        when(cityRepository.findById(id)).thenReturn(Optional.of(city));
        when(parameterMapper.toCityDto(city)).thenReturn(response);

        CityResponseDTO result = parameterService.getCityById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());

        verify(cityRepository).findById(id);
        verify(parameterMapper).toCityDto(city);
    }

    @Test
    void getCityById_ShouldThrowBusinessException_WhenCityNotFound() {

        Long id = 1L;

        when(cityRepository.findById(id)).thenReturn(Optional.empty());

        BusinessException ex = assertThrows(
                BusinessException.class,
                () -> parameterService.getCityById(id));

        assertEquals("Şehir bulunamadı! Geçersiz ID: 1", ex.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());

        verify(cityRepository).findById(id);
        verifyNoInteractions(parameterMapper);
    }

    @Test
    void getDistrictById_ShouldReturnDistrict() {

        Long id = 1L;

        DistrictEntity district = new DistrictEntity();
        district.setId(id);

        DistrictResponseDTO response = new DistrictResponseDTO();
        response.setId(id);

        when(districtRepository.findById(id)).thenReturn(Optional.of(district));
        when(parameterMapper.toDistrictDto(district)).thenReturn(response);

        DistrictResponseDTO result = parameterService.getDistrictById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());

        verify(districtRepository).findById(id);
        verify(parameterMapper).toDistrictDto(district);
    }

    @Test
    void getDistrictById_ShouldThrowBusinessException_WhenDistrictNotFound() {

        Long id = 1L;

        when(districtRepository.findById(id)).thenReturn(Optional.empty());

        BusinessException ex = assertThrows(
                BusinessException.class,
                () -> parameterService.getDistrictById(id));

        assertEquals("İlçe bulunamadı! Geçersiz ID: 1", ex.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());

        verify(districtRepository).findById(id);
        verifyNoInteractions(parameterMapper);
    }

    @Test
    void createCountry_ShouldSaveCountry() {

        CountryRequestDTO request = new CountryRequestDTO();

        CountryEntity entity = new CountryEntity();
        CountryEntity saved = new CountryEntity();
        saved.setId(1L);

        CountryResponseDTO response = new CountryResponseDTO();
        response.setId(1L);

        when(parameterMapper.toCountryEntity(request)).thenReturn(entity);
        when(countryRepository.save(entity)).thenReturn(saved);
        when(parameterMapper.toCountryDto(saved)).thenReturn(response);

        CountryResponseDTO result = parameterService.createCountry(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(parameterMapper).toCountryEntity(request);
        verify(countryRepository).save(entity);
        verify(parameterMapper).toCountryDto(saved);
    }

    @Test
    void createCity_ShouldSaveCity() {

        CityRequestDTO request = new CityRequestDTO();

        CityEntity entity = new CityEntity();
        CityEntity saved = new CityEntity();
        saved.setId(1L);

        CityResponseDTO response = new CityResponseDTO();
        response.setId(1L);

        when(parameterMapper.toCityEntity(request)).thenReturn(entity);
        when(cityRepository.save(entity)).thenReturn(saved);
        when(parameterMapper.toCityDto(saved)).thenReturn(response);

        CityResponseDTO result = parameterService.createCity(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(parameterMapper).toCityEntity(request);
        verify(cityRepository).save(entity);
        verify(parameterMapper).toCityDto(saved);
    }

    @Test
    void createDistrict_ShouldSaveDistrict() {

        DistrictRequestDTO request = new DistrictRequestDTO();

        DistrictEntity entity = new DistrictEntity();
        DistrictEntity saved = new DistrictEntity();
        saved.setId(1L);

        DistrictResponseDTO response = new DistrictResponseDTO();
        response.setId(1L);

        when(parameterMapper.toDistrictEntity(request)).thenReturn(entity);
        when(districtRepository.save(entity)).thenReturn(saved);
        when(parameterMapper.toDistrictDto(saved)).thenReturn(response);

        DistrictResponseDTO result = parameterService.createDistrict(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(parameterMapper).toDistrictEntity(request);
        verify(districtRepository).save(entity);
        verify(parameterMapper).toDistrictDto(saved);
    }

    @Test
    void getFullLocationByDistrictId_ShouldReturnFullLocation() {

        DistrictEntity district = new DistrictEntity();
        district.setId(1L);
        district.setName("Kadıköy");
        district.setCityId(10L);

        CityEntity city = new CityEntity();
        city.setId(10L);
        city.setName("İstanbul");
        city.setCountryId(20L);

        CountryEntity country = new CountryEntity();
        country.setId(20L);
        country.setName("Türkiye");

        when(districtRepository.findById(1L)).thenReturn(Optional.of(district));
        when(cityRepository.findById(10L)).thenReturn(Optional.of(city));
        when(countryRepository.findById(20L)).thenReturn(Optional.of(country));

        FullLocationResponseDTO result =
                parameterService.getFullLocationByDistrictId(1L);

        assertEquals("Türkiye", result.getCountryName());
        assertEquals("İstanbul", result.getCityName());
        assertEquals("Kadıköy", result.getDistrictName());

        verify(districtRepository).findById(1L);
        verify(cityRepository).findById(10L);
        verify(countryRepository).findById(20L);
    }

    @Test
    void getFullLocationByDistrictId_ShouldThrow_WhenDistrictNotFound() {

        when(districtRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                BusinessException.class,
                () -> parameterService.getFullLocationByDistrictId(1L));

        verify(cityRepository, never()).findById(anyLong());
        verify(countryRepository, never()).findById(anyLong());
    }

    @Test
    void getFullLocationByDistrictId_ShouldThrow_WhenCityNotFound() {

        DistrictEntity district = new DistrictEntity();
        district.setCityId(10L);

        when(districtRepository.findById(1L)).thenReturn(Optional.of(district));
        when(cityRepository.findById(10L)).thenReturn(Optional.empty());

        assertThrows(
                BusinessException.class,
                () -> parameterService.getFullLocationByDistrictId(1L));

        verify(countryRepository, never()).findById(anyLong());
    }

    @Test
    void getFullLocationByDistrictId_ShouldThrow_WhenCountryNotFound() {

        DistrictEntity district = new DistrictEntity();
        district.setCityId(10L);

        CityEntity city = new CityEntity();
        city.setCountryId(20L);

        when(districtRepository.findById(1L)).thenReturn(Optional.of(district));
        when(cityRepository.findById(10L)).thenReturn(Optional.of(city));
        when(countryRepository.findById(20L)).thenReturn(Optional.empty());

        assertThrows(
                BusinessException.class,
                () -> parameterService.getFullLocationByDistrictId(1L));
    }
}