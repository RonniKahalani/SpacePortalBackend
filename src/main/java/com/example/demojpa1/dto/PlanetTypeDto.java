package com.example.demojpa1.dto;

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

    @JsonBackReference
    private List<PlanetDto> planets;
}
