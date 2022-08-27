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
    private Double mass;
    private Double diameter;
    private Integer density;
    private Double gravity;
    private Double hoursPerDay;
    private Double distanceFromSun;
    private Integer meanTemp;
    private Integer moons;
    private String notes;
    private String imageUrl;
    private String videoUrl;
    private String thumbnailUrl;
    //@JsonManagedReference
    private List<PlanetTypeDto> types;
}
