package com.fpt.carpark.repository;

import com.fpt.carpark.model.Trip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends PagingAndSortingRepository<Trip, Integer> {
    @Query(value = "select * from trip where destination = ?", nativeQuery = true)
    Trip findByDestination(String destination);
    @Query(value = "select * from trip where destination = ?", nativeQuery = true)
    List<Trip> getByDestination(String destination);

}
