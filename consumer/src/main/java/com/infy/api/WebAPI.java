package com.infy.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.RatingRequestDTO;
import com.infy.dto.StoreDTO;
import com.infy.exception.StoreException;
import com.infy.exception.UserException;
import com.infy.service.RatingService;
import com.infy.service.StoreService;
import com.infy.strngconstant.APIPaths;


@RestController
@RequestMapping //value inside request mapping has been removed and did respective modifications in the APIPaths class.
@Validated
public class WebAPI {
	@Autowired 
	RatingService ratingService;
	@Autowired
	StoreService storeService;
	@Autowired 
	private Environment environment;
	@GetMapping(value=APIPaths.GET_RATING)
	public ResponseEntity<List<StoreDTO>> getRating(@PathVariable String userId) throws UserException, StoreException {
		List<StoreDTO> ratings=ratingService.findRatingByUserId(userId);
		return new ResponseEntity<>(ratings,HttpStatus.OK);
		
	}
	@GetMapping(value=APIPaths.ALL_STORES)
	public ResponseEntity<List<StoreDTO>> getRatingforStoreId() throws UserException, StoreException {
		List<StoreDTO>list=storeService.findAllStores();
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	@GetMapping(value=APIPaths.ALL_USERS)
	public ResponseEntity<List<RatingRequestDTO>> getAllUsers() throws UserException {
		List<RatingRequestDTO>list=ratingService.findAllUsers();
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
		
	@PutMapping(value=APIPaths.PUT_RATING)
	public ResponseEntity<String> putRating(@PathVariable String userId ,@PathVariable Integer rating,@PathVariable String storeId) throws UserException, StoreException {
		String result=ratingService.modifyRating(userId, rating, storeId);
		String message=environment.getProperty("API.UPDATE_SUCCESS")+" "+result;
		return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping(APIPaths.RATE_STORE1)
	public ResponseEntity<String> postUserAndRating(@Valid @RequestBody RatingRequestDTO userDTO,@PathVariable String storeId) throws UserException, StoreException{
		String result=ratingService.addNewUserAndRatingToStore(userDTO,storeId);
		String message=environment.getProperty("API.INSERT_SUCCESS")+" "+result;
		return new ResponseEntity<>(message,HttpStatus.OK);
		
	}
	@PostMapping(APIPaths.RATE_STORE2)
	public ResponseEntity<String> postRatingToStore(@Valid @RequestBody RatingRequestDTO userDTO,@PathVariable String storeId) throws UserException, StoreException{
		String result=ratingService.addRatingToStore(userDTO, storeId);
		String message=environment.getProperty("API.INSERT_SUCCESS")+" "+result;
		return new ResponseEntity<>(message,HttpStatus.OK);
		
	}
	/*
	 * @PostMapping(APIPaths.ADD_STORE) public ResponseEntity<String>
	 * postNewStore(@Valid @RequestBody StoreDTO storeDTO) throws UserException{
	 * String result=storeService.addStore(storeDTO); String
	 * message=environment.getProperty("API.INSERT_SUCCESS")+" "+result; return new
	 * ResponseEntity<>(message,HttpStatus.OK);
	 * 
	 * }
	 */
	

}
