package com.fpt.carpark.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parkinglot")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parkId")
    private int parkId;
    @NotNull(message = "park area shouldn't be null")
    @Column(name = "parkArea")
    @Min(value = 0)
    private int parkArea;
    @Column(name = "parkName")
    @NotNull(message = "park area shouldn't be null")
    private String parkName;
    @Column(name = "parkPlace")
    @NotNull(message = "park area shouldn't be null")
    private String parkPlace;
    @Column(name = "parkPrice")
    @NotNull(message = "park area shouldn't be null")
    @Min(value = 0, message = "parkPrice can not be less than 0")
    private int parkPrice;
    @Column(name = "parkStatus")
    @NotNull(message = "park area shouldn't be null")
    private String parkStatus;

    @Valid
    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "parking")
    private List<Car> cars = new ArrayList<>();
}
