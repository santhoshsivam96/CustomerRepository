package com.infy.dto;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

public class StoreDTO {
	private String storeId;
	@NotNull(message = "Name should not be null")
	@Pattern(regexp = "[A-Za-z]+( [A-Za-z]+)*", message = "Name should contain only alphabets and space")
	private String storeName;
	@NotNull(message = "Desctiption shouldn't be null")
	private String description;
	@NotNull(message = "Please provide Openning hrs")
	@Range(min = 1, max = 24,message="Hrs is out of range")
	private Integer openingHours;
	@NotNull(message="Please provide a value in the range of 0 and 5")
	@Range(min=0,max=5,message="Rating should be in the range of 0 and 5")
	private Double overallRating;
	@NotNull(message="Please provide a value")
	private Long totalRating;
	private List<RatingRequestDTO>ratingList;
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getOpeningHours() {
		return openingHours;
	}
	public void setOpeningHours(Integer openingHours) {
		this.openingHours = openingHours;
	}
	public Double getOverallRating() {
		return overallRating;
	}
	public void setOverallRating(Double overallRating) {
		this.overallRating = overallRating;
	}
	public Long getTotalRating() {
		return totalRating;
	}
	public void setTotalRating(Long totalRating) {
		this.totalRating = totalRating;
	}
	public List<RatingRequestDTO> getRatingList() {
		return ratingList;
	}
	public void setRatingList(List<RatingRequestDTO> ratingList) {
		this.ratingList = ratingList;
	}
	@Override
	public String toString() {
		return "StoreDTO [storeId=" + storeId + ", storeName=" + storeName + ", description=" + description
				+ ", openingHours=" + openingHours + ", overallRating=" + overallRating + ", totalRating=" + totalRating
				+ ", ratingList=" + ratingList + "]";
	}

	

	
}
