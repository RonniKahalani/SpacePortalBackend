package com.example.demojpa1.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class PlanetDto implements Serializable {
    private Long id;
    private String name;
    private double mass;
    private double diameter;
    private int density;
    private double gravity;
    private double hoursPerDay;
    private double distanceFromSun;
    private int meanTemp;
    private int moons;
    private String notes;
    private String imageUrl;
    private String videoUrl;
    private String thumbnailUrl;
    @JsonManagedReference
    private List<PlanetTypeDto> types;
}
