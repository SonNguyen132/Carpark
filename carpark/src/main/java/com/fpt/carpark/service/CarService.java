package com.fpt.carpark.service;

import com.fpt.carpark.exception.CarNotFoundException;
import com.fpt.carpark.model.Car;
import com.fpt.carpark.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;
    //add
     public Car addCar(Car car) throws CarNotFoundException {
         List<Car> cars = carRepository.getByLicensePlate(car.getLicensePlate());
         if (cars.size() > 0){
             throw new CarNotFoundException("Duplicated LicensePlate");
         }
        return carRepository.save(car);
    }
    //edit
     public Car update(Car car, String licensePlate) throws CarNotFoundException {
        if (car != null) {
            Car car1 = carRepository.findById(licensePlate).orElseThrow(() -> new CarNotFoundException("Not found car with licensePlate = " + licensePlate));
            car1.setCarColor(car.getCarColor());
            car1.setCarType(car.getCarType());
            car1.setCompany(car.getCompany());
            car1.setTickets(car.getTickets());
            car1.setTickets(car.getTickets());
            car1.setParkingLot(car.getParkingLot());
            return carRepository.save(car1);
        }else throw new CarNotFoundException("Not matches");
     }
     //delete
     public String deleteByLicensePlate(String licensePlate) throws CarNotFoundException {
        Car car = carRepository.findByLicensePlate(licensePlate);
        if (car != null){
            carRepository.delete(car);
            return "Delete Success";
        }else {
            throw new CarNotFoundException("Not found Car with licensePlate = " + licensePlate);
        }
     }

     //list
     public List<Car> getAll(int page, int offset){
         Page<Car> carPage = carRepository.findAll(PageRequest.of(page, offset));
         List<Car> list = new ArrayList<>();
         if (carPage != null && carPage.hasContent()){
             list = carPage.getContent();
             return list;
         }
        return null;
     }

     //search by LicensePlate
    public Car findByLicensePlate(String licensePlate) throws CarNotFoundException {
         Car car = carRepository.findByLicensePlate(licensePlate);
         if (car != null){
             return car;
         }else {
             throw new CarNotFoundException("Not Found Car with licensePlate = " + licensePlate);
         }
    }
}
