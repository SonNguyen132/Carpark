package com.fpt.carpark.repository;

import com.fpt.carpark.model.ParkingLot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingLotRepository extends PagingAndSortingRepository<ParkingLot, Integer> {
    @Query(value = "select * from parkinglot where park_Name = ?", nativeQuery = true)
    List<ParkingLot> findByParkName(String parkName);
    @Query(value = "select * from parkinglot where park_Name = ?", nativeQuery = true)
    ParkingLot findByOneParkName(String parkName);
}
