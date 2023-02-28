package com.fpt.carpark.repository;

import com.fpt.carpark.model.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends PagingAndSortingRepository<Ticket, Integer> {
    @Query(value = "select * from ticket where customer_Name = ?", nativeQuery = true)
    Ticket findByCustomerName(String customerName);
    @Query(value = "select * from ticket where customer_Name = ?", nativeQuery = true)
    List<Ticket> getAllCustomerName(String customerName);
}
