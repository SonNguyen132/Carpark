package com.fpt.carpark.service;

import com.fpt.carpark.exception.TripNotFoundException;
import com.fpt.carpark.model.Trip;
import com.fpt.carpark.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;
    //add
    public Trip add(Trip trip) throws TripNotFoundException {
//        List<Trip> trips = tripRepository.getByDestination(trip.getDestination());
//        if (trips.size() > 0){
//            throw new TripNotFoundException("Duplicate destination");
//        }
        if (trip.getBookedTicketNumber() > trip.getMaximumOnlineTicketNumber()){
            throw new TripNotFoundException("getBookedTicketNumber must be less or equal than getMaximumOnlineTicketNumber");
        }
        return tripRepository.save(trip);
    }
    //edit
    public Trip update(Trip trip, int tripId) throws TripNotFoundException {
        if (trip != null){
            Trip trip1 = tripRepository.findById(tripId).orElseThrow(() -> new TripNotFoundException("Not found trip with tripId = " + tripId));
                trip1.setBookedTicketNumber(trip.getBookedTicketNumber());
                trip1.setDestination(trip.getDestination());
                trip1.setDriver(trip.getDriver());
                trip1.setCarType(trip.getCarType());
                trip1.setDepartureDate(trip.getDepartureDate());
                trip1.setDepartureTime(trip.getDepartureTime());
                trip1.setMaximumOnlineTicketNumber(trip.getMaximumOnlineTicketNumber());
                if (trip1.getBookedTicketNumber() > trip1.getMaximumOnlineTicketNumber()){
                    throw new TripNotFoundException("getBookedTicketNumber must be less or equal than getMaximumOnlineTicketNumber");
                }
            return tripRepository.save(trip1);
        }
        throw new TripNotFoundException("Not found trip with tripId = " + tripId);
    }

    public String deleteById(int tripId) throws TripNotFoundException {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new TripNotFoundException("Not found trip with tripId = " + tripId));
            tripRepository.delete(trip);
            return "Delete Success";
    }
    //show list
    public List<Trip> getAll(int page, int offset){
        Page<Trip> tripPage = tripRepository.findAll(PageRequest.of(page, offset));
        List<Trip> list = new ArrayList<>();
        if (tripPage != null && tripPage.hasContent()){
            list = tripPage.getContent();
            return list;
        }
        return null;
    }
    //deleteByDestination
    public String deleteByDestination(String destination) throws TripNotFoundException {
        Trip trip = tripRepository.findByDestination(destination);
        if (trip != null){
            tripRepository.delete(trip);
            return "Delete Success";
        }
        throw new TripNotFoundException("Not found trip with destination = " + destination);
    }

    //get One By destination
    public Trip getOne(String destination) throws TripNotFoundException {
        Trip trip = tripRepository.findByDestination(destination);
        if (trip != null){
            return trip;
        }
        throw new TripNotFoundException("Not found trip destination: " + destination);
    }
}
