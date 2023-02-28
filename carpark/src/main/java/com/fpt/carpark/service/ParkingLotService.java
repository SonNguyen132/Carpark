package com.fpt.carpark.service;

import com.fpt.carpark.exception.ParkingLotNotFoundException;
import com.fpt.carpark.model.ParkingLot;
import com.fpt.carpark.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingLotService {
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    //add
    public ParkingLot add(ParkingLot parkingLot) {
//        List<ParkingLot> parkingLots = parkingLotRepository.findByParkName(parkingLot.getParkName());
//        if (parkingLots.size() > 0){
//            throw new ParkingLotNotFoundException("Duplicated ParkName");
//        }
        return parkingLotRepository.save(parkingLot);
    }
    //Show list
    public List<ParkingLot> getAll(int page, int offset){
        Page<ParkingLot> parkingLotPage = parkingLotRepository.findAll(PageRequest.of(page, offset));
        List<ParkingLot> list = new ArrayList<>();
        if (parkingLotPage != null && parkingLotPage.hasContent()){
            list = parkingLotPage.getContent();
            return list;
        }
        return null;
    }
    //edit
    public ParkingLot update(ParkingLot parkingLot, int parkId) throws ParkingLotNotFoundException {
        if (parkingLot != null){
           ParkingLot parkingLot1 = parkingLotRepository.findById(parkId).orElseThrow(() -> new ParkingLotNotFoundException("Not found ticket with parkId = " + parkId));
               parkingLot1.setParkArea(parkingLot.getParkArea());
               parkingLot1.setParkName(parkingLot.getParkName());
               parkingLot1.setParkPlace(parkingLot.getParkPlace());
               parkingLot1.setParkPrice(parkingLot.getParkPrice());
               parkingLot1.setParkStatus(parkingLot.getParkStatus());
           return parkingLotRepository.save(parkingLot1);
        }
        throw new ParkingLotNotFoundException("ParkingLot not found");
    }

    //Xoa theo id
    public String deleteById(int parkId) throws ParkingLotNotFoundException{
        ParkingLot parkingLot = parkingLotRepository.findById(parkId).orElseThrow(() -> new ParkingLotNotFoundException("Not found ticket with parkId = " + parkId));
            parkingLotRepository.delete(parkingLot);
            return "Delete Success";
    }
    //delete by name
    public String deleteByName(String parkName) throws ParkingLotNotFoundException {
        ParkingLot parkingLot = parkingLotRepository.findByOneParkName(parkName);
        if (parkingLot != null){
            parkingLotRepository.delete(parkingLot);
            return "Delete Success";
        }else
            throw new ParkingLotNotFoundException("Not Found Parking = " + parkName);
    }

    //Get One By parkName
    public ParkingLot getOneByParkName(String parkName) throws ParkingLotNotFoundException {
        ParkingLot parkingLot = parkingLotRepository.findByOneParkName(parkName);
        if (parkingLot != null){
            return parkingLot;
        }else
            throw new ParkingLotNotFoundException("Not Found Parking " + parkName);
    }
}
