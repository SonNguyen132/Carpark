package com.fpt.carpark.repository;

import com.fpt.carpark.model.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, String> {
    @Query(value = "select * from car where license_plate = ?", nativeQuery = true)
    Car findByLicensePlate(String licensePlate);
    @Query(value = "select * from car where license_plate = ?", nativeQuery = true)
    List<Car> getByLicensePlate(String licensePlate);
}
