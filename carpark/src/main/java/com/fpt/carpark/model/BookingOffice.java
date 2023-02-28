package com.fpt.carpark.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "bookingoffice")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "officeId")
    private int officeId;
    @Column(name = "endContractDealine")
    @NotNull(message = "endContractDealine shouldn't be null")
    private LocalDate endContractDealine;
    @Column(name = "officeName")
    @NotNull(message = "officeName shouldn't be null")
    @Pattern(regexp = "^[a-zA-Z ]{2,20}$", message = "Invalid officeName")
    private String officeName;
    @Column(name = "officePhone")
    @Pattern(regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$", message = "Invalid phone")
    private String officePhone;
    @Column(name = "officePlace")
    @NotNull(message = "officePlace shouldn't be null")
    @Size(min = 2 , message = "length least is 2")
    @Pattern(regexp = "^[a-zA-Z ]{2,20}$", message = "Invalid officePlace")
    private String officePlace;
    @Column(name = "officePrice")
    @NotNull(message = "officePrice shouldn't be null")
    @Min(value = 0, message = "least is 0")
    private int officePrice;
    @Column(name = "startContractDealine")
    @NotNull(message = "startContractDealine shouldn't be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startContractDealine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tripId")
    @Valid
    @JsonBackReference(value = "booking")
    private Trip trip;
}
