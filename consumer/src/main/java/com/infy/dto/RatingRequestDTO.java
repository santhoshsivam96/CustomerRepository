 package com.infy.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

public class RatingRequestDTO {
	@NotNull(message = "UserId should not be null")
	@NotEmpty(message="UserId field should not be null")
	private String userId;
	@NotNull(message = "Please provide range in 1 to 5")
	@Range(min = 1, max = 5,message="Rating should be in the range of 1 to 5")
	private Integer rating;
	@NotNull(message = "Name should not be null")
	@Pattern(regexp = "[A-Za-z]+( [A-Za-z]+)*", message = "Name should contain only alphabets and space")
	private String name;
	
	public RatingRequestDTO() {
	}

	public RatingRequestDTO(String userId, Integer rating, String name) {
		super();
		this.userId = userId;
		this.rating = rating;
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RatingRequestDTO [userId=" + userId + ", rating=" + rating + ", name=" + name + "]";
	}


}
