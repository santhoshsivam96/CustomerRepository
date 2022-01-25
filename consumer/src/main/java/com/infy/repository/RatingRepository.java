package com.infy.repository;

import org.springframework.data.repository.CrudRepository;
import com.infy.entity.RatingRequest;

public interface RatingRepository extends CrudRepository<RatingRequest, String> {
	public RatingRequest findByUserId(String userId);
	public void deleteByUserId(String userId);
}
