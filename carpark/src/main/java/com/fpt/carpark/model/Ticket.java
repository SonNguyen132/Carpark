package com.fpt.carpark.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Time;

@Entity
@Table(name = "ticket")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketId")
    private  int ticketId;
    @Column(name = "bookingTime")
    @NotNull(message = "bookingTime shouldn't be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss")
    private Time bookingTime;
    @Column(name = "customerName")
    @NotNull(message = "customerName shouldn't be null")
    @Size(min = 2, message = "customerName is greater or equal 2")
    @Pattern(regexp = "^[a-zA-Z ]{2,20}$", message = "Invalid Name")
    private String customerName;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "licensePlate")
    @JsonBackReference(value = "ticket")
    private Car car;

    @Valid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tripId")
    @JsonBackReference(value = "trip")
    private Trip trip;

}
