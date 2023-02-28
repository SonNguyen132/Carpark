package com.fpt.carpark.controller;

import com.fpt.carpark.exception.BookingOfficeException;
import com.fpt.carpark.model.BookingOffice;
import com.fpt.carpark.service.BookingOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/booking/")
public class BookingOfficeController {

    @Autowired
    private BookingOfficeService bookingOfficeService;

    @PostMapping("add")
    public ResponseEntity<BookingOffice> addBookingOffice(@RequestBody @Valid BookingOffice bookingOffice){
        BookingOffice bookingOffice1 = bookingOfficeService.addBookingOffice(bookingOffice);
        return new ResponseEntity<>(bookingOffice1, HttpStatus.CREATED);
    }

    @GetMapping("list/{page}/{offset}")
    public ResponseEntity<List<BookingOffice>> getAll(@PathVariable int page, @PathVariable int offset) throws BookingOfficeException {
        return ResponseEntity.ok(bookingOfficeService.getAll(page, offset));
    }

    //Get by Name
    @GetMapping("{officeName}")
    public ResponseEntity<BookingOffice> getByOfficeName(@PathVariable String officeName) throws BookingOfficeException {
        return ResponseEntity.ok(bookingOfficeService.getOneOfficeName(officeName));
    }
}
