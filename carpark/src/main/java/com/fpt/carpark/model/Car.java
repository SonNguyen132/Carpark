package com.fpt.carpark.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "car")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @Column(name = "licensePlate")
    @NotNull(message = "licensePlate shouldn't be null")
    @Pattern(regexp = "^[0-9]{2}[A-Z]{1}-+[0-9]{4,5}$" , message = "Not valid")
    private String licensePlate;
    @Column(name = "carColor")
    @NotNull(message = "carColor shouldn't be null")
    @Pattern(regexp = "^[a-zA-Z ]{2,20}$", message = "Invalid carColor")
    private String carColor;
    @Column(name = "carType")
    @NotNull(message = "carType shouldn't be null")
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9 ]{2,20}$", message = "Invalid carType")
    private String carType;
    @Column(name = "company")
    @NotNull(message = "company shouldn't be null")
    @Pattern(regexp = "^[a-zA-Z ]{2,20}$", message = "Invalid company")
    private String company;

    @OneToMany(mappedBy = "car" , cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "ticket")
    private List<Ticket> tickets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parkId")
    @JsonBackReference(value = "parking")
    private ParkingLot parkingLot;

}
