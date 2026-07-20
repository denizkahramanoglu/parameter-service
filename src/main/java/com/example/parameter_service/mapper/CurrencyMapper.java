package com.example.parameter_service.mapper;
import com.example.parameter_service.dto.CurrencyDTO;
import com.example.parameter_service.entity.CurrencyEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
    CurrencyDTO toDTO(CurrencyEntity entity);
    List<CurrencyDTO> toDTOList(List<CurrencyEntity> entities);
}