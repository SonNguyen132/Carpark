package com.fpt.carpark.service;

import com.fpt.carpark.exception.BookingOfficeException;
import com.fpt.carpark.model.BookingOffice;
import com.fpt.carpark.repository.BookingOfficerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingOfficeService {
    @Autowired
    private BookingOfficerepository bookingOfficerepository; //k nen comment directly field
    //Add
    public BookingOffice addBookingOffice(BookingOffice bookingOffice) {
//        List<BookingOffice> bookingOffices = bookingOfficerepository.findByOfficeName(bookingOffice.getOfficeName());
//        if (bookingOffices.size() > 0){
//            throw new BookingOfficeException("Duplicate OfficeName");
//        }
            return bookingOfficerepository.save(bookingOffice);
    }
    //Xem list
    public List<BookingOffice> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BookingOffice> pageBookingOffices = bookingOfficerepository.findAll(pageable);
        List<BookingOffice> bookingOffices = new ArrayList<>();
        if (pageBookingOffices != null && pageBookingOffices.hasContent() ){
            bookingOffices = pageBookingOffices.getContent();
            return bookingOffices;
        }else {
            return  null;
        }

    }
    //Search By Name
    public BookingOffice getOneOfficeName(String officeName) throws BookingOfficeException {
        BookingOffice bookingOffice = bookingOfficerepository.getByOfficeName(officeName);
        if (bookingOffice != null){
            return bookingOffice;
        }else {
            throw  new BookingOfficeException("Not Matches");
        }
    }
}
