package com.infy.service;


import java.util.List;

import com.infy.dto.RatingRequestDTO;
import com.infy.dto.StoreDTO;
import com.infy.entity.RatingRequest;
import com.infy.exception.StoreException;

public interface StoreService {
	public String addStore(StoreDTO store) throws StoreException;
	public List<StoreDTO>findAllStores()throws StoreException;
	public String updateRatingToStore(RatingRequest request, String userId,String storeId)throws StoreException;
	public RatingRequest addNewUserToStore(RatingRequestDTO rate,String storeId) throws StoreException;
	public List<StoreDTO> findAllRatedStoresWithUserID(String userId)throws StoreException;
}
