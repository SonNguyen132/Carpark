package com.fpt.carpark.service;

import com.fpt.carpark.exception.TicketNotFoundException;
import com.fpt.carpark.model.Ticket;
import com.fpt.carpark.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    //add
    public Ticket add(Ticket ticket) throws TicketNotFoundException {
        List<Ticket> tickets = ticketRepository.getAllCustomerName(ticket.getCustomerName());
        if (tickets.size() > 0){
            throw new TicketNotFoundException("Duplicate CustomerName");
        }
        return ticketRepository.save(ticket);
    }

    //edit
    public Ticket update(Ticket ticket, int ticketId) throws TicketNotFoundException {
        if (ticket != null){
            Ticket ticket1 = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Not found ticket with ticketId = " + ticketId));
            ticket1.setBookingTime(ticket.getBookingTime());
            ticket1.setCustomerName(ticket.getCustomerName());
            return ticketRepository.save(ticket1);
        }else {
            throw new TicketNotFoundException("Not found ticket");
        }
    }

    public String deleteByCustomerName(String customerName) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findByCustomerName(customerName);
        if (ticket != null){
            ticketRepository.delete(ticket);
            return "Delete Success";
        }else {
            throw new TicketNotFoundException("Not found ticket with customerName" + customerName);
        }
    }

    //delete
    public String deleteById(int ticketId) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Not found ticket with ticketId = " + ticketId));
        ticketRepository.delete(ticket);
        return "Delete Success";
    }

    //list
    public List<Ticket> getAll(int page, int size){
        Page<Ticket> ticketPage = ticketRepository.findAll(PageRequest.of(page, size));
        List<Ticket> list = new ArrayList<>();
        if (ticketPage != null && ticketPage.hasContent()){
            list = ticketPage.getContent();
            return list;
        }
        return null;
    }
    //SEARCH by customer
    public Ticket getOneTicket(String customerName) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findByCustomerName(customerName);
        if (ticket != null){
            return ticket;
        }else {
            throw new TicketNotFoundException("Not Found Ticket with CustomerName: " + customerName );
        }
    }
}
