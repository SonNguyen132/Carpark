package com.fpt.carpark.controller;

import com.fpt.carpark.exception.TicketNotFoundException;
import com.fpt.carpark.model.Ticket;
import com.fpt.carpark.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ticket/")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/list/{page}/{offset}")
    private ResponseEntity<List<Ticket>> getAllPagition(@PathVariable int page, @PathVariable int offset){
        return ResponseEntity.ok(ticketService.getAll(page, offset));
    }
    @GetMapping("search/{customerName}")
    public ResponseEntity<Ticket> getOneTicket(@PathVariable String customerName) throws TicketNotFoundException {
        return ResponseEntity.ok(ticketService.getOneTicket(customerName));
    }

    @PostMapping("add")
    public ResponseEntity<Ticket> addTicket(@RequestBody @Valid Ticket ticket) throws TicketNotFoundException {
        return new ResponseEntity<>(ticketService.add(ticket), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket, @PathVariable int ticketId) throws TicketNotFoundException {
        return ResponseEntity.ok(ticketService.update(ticket, ticketId));
    }

    @DeleteMapping("deleteby/{customerName}")
    public ResponseEntity<String> deleteByCustomerName(@PathVariable String customerName) throws TicketNotFoundException {
        return ResponseEntity.ok(ticketService.deleteByCustomerName(customerName));
    }

    @DeleteMapping("delete/{ticketId}")
    public ResponseEntity<String> deleteByTicketId(@PathVariable int ticketId) throws TicketNotFoundException {
        return ResponseEntity.ok(ticketService.deleteById(ticketId));
    }
}
