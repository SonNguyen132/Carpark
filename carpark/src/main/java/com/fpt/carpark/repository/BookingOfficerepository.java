package com.fpt.carpark.repository;

import com.fpt.carpark.model.BookingOffice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingOfficerepository extends PagingAndSortingRepository<BookingOffice, Integer> {
    @Query(value = "select * from bookingoffice where office_Name = ?", nativeQuery = true)
    List<BookingOffice> findByOfficeName(String officeName);
    @Query(value = "select * from bookingoffice where office_Name = ?", nativeQuery = true)
    BookingOffice getByOfficeName(String officeName);

}
