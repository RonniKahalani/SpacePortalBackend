package com.example.demojpa1.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class CustomerDto implements Serializable {
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

   @JsonBackReference
    private List<ReservationDto> reservations;

}
