package com.fpt.carpark.controller;

import com.fpt.carpark.exception.ParkingLotNotFoundException;
import com.fpt.carpark.model.ParkingLot;
import com.fpt.carpark.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/parking/")
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;

    //add
    @PostMapping("add")
    public ResponseEntity<ParkingLot> addParkingLot(@RequestBody @Valid ParkingLot parkingLot) throws ParkingLotNotFoundException {
        ParkingLot parkingLot1 = parkingLotService.add(parkingLot);
        return new ResponseEntity<>(parkingLot1, HttpStatus.CREATED);
    }

    //show list
    @GetMapping("/list/{page}/{offset}")
    private ResponseEntity<List<ParkingLot>> getAllPagition(@PathVariable int page, @PathVariable int offset){
        return ResponseEntity.ok(parkingLotService.getAll(page, offset));
    }
    //edit by id
    @PutMapping("update/{id}")
    public ResponseEntity<ParkingLot> update(@RequestBody @Valid ParkingLot parkingLot, @PathVariable int id) throws ParkingLotNotFoundException {
        return ResponseEntity.ok(parkingLotService.update(parkingLot,id));
    }
    //delete by id
    @DeleteMapping("deletebyid/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) throws ParkingLotNotFoundException {
        return ResponseEntity.ok(parkingLotService.deleteById(id));
    }
    //delete by name
    @DeleteMapping("delete/{parkName}")
    public ResponseEntity<String> deleteById(@PathVariable String parkName) throws ParkingLotNotFoundException {
        return ResponseEntity.ok(parkingLotService.deleteByName(parkName));
    }
    //getOne By name
    @GetMapping("{parkName}")
    public ResponseEntity<ParkingLot> getByParkName(@PathVariable String parkName) throws ParkingLotNotFoundException {
        return ResponseEntity.ok(parkingLotService.getOneByParkName(parkName));
    }
}
