package com.infy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dto.RatingRequestDTO;
import com.infy.dto.StoreDTO;
import com.infy.entity.RatingRequest;
import com.infy.entity.Store;
import com.infy.exception.StoreException;
import com.infy.exception.UserException;
import com.infy.repository.RatingRepository;
import com.infy.repository.StoreRepository;

@Service(value = "ratingService")
@Transactional
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository repo;
	@Autowired
	private StoreServiceImpl service; 
	
	@Override
	public String modifyRating(String userId, Integer rating, String storeId) throws UserException, StoreException {
		Optional<RatingRequest> opt = Optional.of(repo.findByUserId(userId));
		RatingRequest returned = opt.orElseThrow(() -> new UserException("Service.USER_NOT_FOUND"));
		returned.setRating(rating);
		String out=service.updateRatingToStore(returned, userId, storeId);
		return out;
	}

	@Override
	public List<StoreDTO> findRatingByUserId(String userId) throws UserException, StoreException {
		RatingRequest returned = repo.findByUserId(userId);
		if (returned == null) {
			throw new UserException("Service.USER_NOT_FOUND");
		}
		if (returned.getRating() == (null)) {
			throw new UserException("Service.RATING_NOT_FOUND");
		}
		List<StoreDTO>result=service.findAllRatedStoresWithUserID(userId);
		return result;
	}

	@Override
	public List<RatingRequestDTO> findAllUsers() throws UserException {
		List<RatingRequestDTO> list = new ArrayList<>();
		List<RatingRequest>opt = (List<RatingRequest>) repo.findAll();
		if(opt.isEmpty()) throw new UserException("Service.NO_DATA_FOUND");
		while (opt.iterator().hasNext()) {
			RatingRequest found = opt.iterator().next();
			RatingRequestDTO userDTO = new RatingRequestDTO(found.getName(), found.getRating(), found.getUserId());
				list.add(userDTO);
			}
		
		return list;

	}

	@Override
	public  String addNewUserAndRatingToStore(RatingRequestDTO user,String storeId) throws UserException, StoreException {
		RatingRequest opt = repo.findByUserId(user.getUserId());
		if (opt != null) {
			throw new UserException("Service.USER_ID_IS_ALREADY_EXISTS");
		}
		RatingRequest request= service.addNewUserToStore(user, storeId);
		repo.save(request);
		return request.getUserId();
	}
	public  String addRatingToStore(RatingRequestDTO user,String storeId) throws UserException, StoreException {
		RatingRequest opt = repo.findByUserId(user.getUserId());
		if (opt == null) {
			throw new UserException("Service.USER_ID_NOT_FOUND");
		}
		RatingRequest request= service.addNewUserToStore(user, storeId);
		repo.save(request);
		return request.getUserId();
	}

}
