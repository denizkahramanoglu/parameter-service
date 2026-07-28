package com.example.parameter_service.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "district")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistrictEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "city_id", nullable = false)
    private Long cityId;
}