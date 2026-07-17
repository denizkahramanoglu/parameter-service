package com.example.parameter_service.mapper;

import com.example.parameter_service.dto.*;
import com.example.parameter_service.entity.CityEntity;
import com.example.parameter_service.entity.CountryEntity;
import com.example.parameter_service.entity.DistrictEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParameterMapper {

    CountryResponseDTO toCountryDto(CountryEntity countryEntity);
    CityResponseDTO toCityDto(CityEntity cityEntity);
    DistrictResponseDTO toDistrictDto(DistrictEntity districtEntity);

    // Request DTO -> Entity Dönüşümleri (ID'ler yoksayılır)
    @Mapping(target = "id", ignore = true)
    CountryEntity toCountryEntity(CountryRequestDTO request);

    @Mapping(target = "id", ignore = true)
    CityEntity toCityEntity(CityRequestDTO request);

    @Mapping(target = "id", ignore = true)
    DistrictEntity toDistrictEntity(DistrictRequestDTO request);

    // Liste Dönüşümleri (Frontend listelemeleri için şart)
    List<CountryResponseDTO> toCountryDtoList(List<CountryEntity> countryEntities);
    List<CityResponseDTO> toCityDtoList(List<CityEntity> cityEntities);
    List<DistrictResponseDTO> toDistrictDtoList(List<DistrictEntity> districtEntities);
}
