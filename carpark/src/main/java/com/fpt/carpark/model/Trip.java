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
import javax.validation.constraints.Pattern;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trip")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tripId")
    private int tripId;
    @Column(name = "bookedTicketNumber")
    @NotNull(message = "bookedTicketNumber shouldn't be null")
    @Min(value = 0)
    private int bookedTicketNumber;
    @Column(name = "carType")
    @NotNull(message = "carType shouldn't be null")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{2,20}$", message = "Invalid carType")
    private String carType;
    @Column(name = "departureDate")
    @NotNull(message = "departureDate shouldn't be null")
    private LocalDate departureDate;
    @Column(name = "departureTime")
    @NotNull(message = "departureTime shouldn't be null")
    private Time departureTime;
    @Column(name = "destination")
    @NotNull(message = "destination shouldn't be null")
    @Pattern(regexp = "^[a-zA-Z ]{2,20}$", message = "Invalid destination")
    private String destination;
    @Column(name = "driver")
    @NotNull(message = "driver shouldn't be null")
    @Pattern(regexp = "^[a-zA-Z ]{2,20}$", message = "Invalid driver")
    private String driver;
    @Column(name = "maximumOnlineTicketNumber")
    @NotNull(message = "maximumOnlineTicketNumber shouldn't be null")
    @Min(value = 0)
    private int maximumOnlineTicketNumber;

    @Valid
    @OneToMany(mappedBy = "trip", cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "trip")
    private List<Ticket> tickets = new ArrayList<>();
    @Valid
    @OneToMany(mappedBy = "trip", cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "booking")
    private List<BookingOffice> bookingOffices = new ArrayList<>();

}
