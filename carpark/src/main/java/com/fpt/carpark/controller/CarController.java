package com.fpt.carpark.controller;

import com.fpt.carpark.exception.CarNotFoundException;
import com.fpt.carpark.model.Car;
import com.fpt.carpark.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/car/")
public class CarController {

    @Autowired
    private CarService carService;
    //add
    @PostMapping("add")
    public ResponseEntity<Car> addCar(@RequestBody @Valid Car car) throws CarNotFoundException{
        return new ResponseEntity<>(carService.addCar(car), HttpStatus.CREATED);
    }
    //edit
    @PutMapping("update/{licensePlate}")
    public ResponseEntity<Car> updateCar(@RequestBody @Valid Car car, @PathVariable String licensePlate) throws CarNotFoundException {
        return ResponseEntity.ok(carService.update(car, licensePlate));
    }
    //show list
    @GetMapping("/list/{page}/{offset}")
    private ResponseEntity<List<Car>> getAllPagition(@PathVariable int page, @PathVariable int offset){
        return ResponseEntity.ok(carService.getAll(page, offset));
    }

    //delete By licensePlate
    @DeleteMapping("delete/{licensePlate}")
    public ResponseEntity<String> deleteByLicensePlate(@PathVariable String licensePlate) throws CarNotFoundException {
        return ResponseEntity.ok(carService.deleteByLicensePlate(licensePlate));
    }
    //Search By licensePlate
    @GetMapping("{licensePlate}")
    public ResponseEntity<Car> getByLicensePlate(@PathVariable String licensePlate) throws CarNotFoundException {
        return ResponseEntity.ok(carService.findByLicensePlate(licensePlate));
    }
}
