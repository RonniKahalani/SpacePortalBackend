package com.example.demojpa1.dto;

import com.example.demojpa1.planet.model.Planet;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class PlanetTypeDto implements Serializable {
    private Long id;
    private String name;
}
