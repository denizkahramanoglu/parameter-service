package com.example.parameter_service.repository;

import com.example.parameter_service.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {
    List<DistrictEntity> findByCityId(Long cityId);
}
