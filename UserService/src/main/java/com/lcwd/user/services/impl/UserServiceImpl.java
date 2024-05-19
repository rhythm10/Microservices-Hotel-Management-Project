package com.lcwd.user.services.impl;

import com.lcwd.user.Exceptions.ResourceNotFoundException;
import com.lcwd.user.entities.Hotel;
import com.lcwd.user.entities.Rating;
import com.lcwd.user.entities.User;
import com.lcwd.user.external.services.HotelService;
import com.lcwd.user.repositories.UserRepository;
import com.lcwd.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> allUsers =  userRepository.findAll();
        for(User user : allUsers) {
            Rating[] ratingOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + user.getUserId(), Rating[].class);
            List<Rating> ratings =  Arrays.stream(ratingOfUser).toList();

            //Fetch Hotel Details for each rating
            List<Rating> ratingList = ratings.stream().map(rating -> {
                /*
                * Using RestTemplate
                *
                * ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
                * */
                Hotel hotel = hotelService.getHotel(rating.getHotelId());
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());

            user.setRatings(ratingList);
        }
        return allUsers;
    }

    @Override
    public User getUser(String userId) {
        //Get User
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User ID is not found on Server : " + userId));

        //Fetch Rating of the above User
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        List<Rating> ratings =  Arrays.stream(ratingOfUser).toList();

        //Fetch Hotel Details for each rating
        List<Rating> ratingList = ratings.stream().map(rating -> {
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }

    @Override
    public User deleteUser(String userId) {
        User user = getUser(userId);
        userRepository.deleteById(userId);
        return user;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
