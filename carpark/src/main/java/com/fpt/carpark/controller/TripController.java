package com.fpt.carpark.controller;

import com.fpt.carpark.exception.TripNotFoundException;
import com.fpt.carpark.model.Trip;
import com.fpt.carpark.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trip/")
public class TripController {
    @Autowired
    private TripService tripService;
    //SHOW list
    @GetMapping("/list/{page}/{offset}")
    private ResponseEntity<List<Trip>> getAllPagition(@PathVariable int page, @PathVariable int offset){
        return ResponseEntity.ok(tripService.getAll(page, offset));
    }
    //edit
    @PutMapping("update/{tripId}")
    public ResponseEntity<Trip> updateTrip(@Valid @RequestBody Trip trip, @PathVariable int tripId) throws TripNotFoundException {
        return ResponseEntity.ok(tripService.update(trip, tripId));
    }
    //add
    @PostMapping("add")
    public ResponseEntity<Trip> addTrip(@Valid @RequestBody Trip trip) throws TripNotFoundException {
        return new ResponseEntity<>(tripService.add(trip), HttpStatus.CREATED);
    }
    //deletebyId
    @DeleteMapping("delete/{tripId}")
    public ResponseEntity<String> deleteById(@PathVariable int tripId) throws TripNotFoundException {
        return ResponseEntity.ok(tripService.deleteById(tripId));
    }

    //delete by destination
    @DeleteMapping("deleteby/{destination}")
    public ResponseEntity<String> deleteByDestination(@PathVariable String destination) throws TripNotFoundException {
        return ResponseEntity.ok(tripService.deleteByDestination(destination));
    }
    //Search by destination
    @GetMapping("{destination}")
    public ResponseEntity<Trip> getByDestination(@PathVariable String destination) throws TripNotFoundException {
        return ResponseEntity.ok(tripService.getOne(destination));
    }
}
