package com.lcwd.hotel.services;

import com.lcwd.hotel.entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);

    List<Hotel> getAll();

    Hotel get(String id);
}
