package com.example.demojpa1.dto;

import com.example.demojpa1.reservation.model.Reservation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class CustomerDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
}
