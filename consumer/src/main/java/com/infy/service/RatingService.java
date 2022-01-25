package com.infy.service;

import java.util.List;

import com.infy.dto.RatingRequestDTO;
import com.infy.dto.StoreDTO;
import com.infy.exception.StoreException;
import com.infy.exception.UserException;

public interface RatingService {
public String modifyRating(String userId, Integer rating, String storeId) throws UserException, StoreException;
public List<StoreDTO> findRatingByUserId(String userId) throws UserException, StoreException;
public List<RatingRequestDTO> findAllUsers() throws UserException;
public String addNewUserAndRatingToStore(RatingRequestDTO user,String storeId)throws UserException, StoreException;
public  String addRatingToStore(RatingRequestDTO user,String storeId) throws UserException, StoreException; 
}
