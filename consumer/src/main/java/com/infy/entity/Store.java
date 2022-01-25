package com.infy.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name = "storetable")
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id")
	private String storeId;
	private String storeName;
	private String description;
	private Integer openingHours;
	private Double overallRating;
	private Long totalRating;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="sto_id")
	private List<RatingRequest>ratingList;
	
	
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
	public List<RatingRequest> getRatingList() {
		return ratingList;
	}
	public void setRatingList(List<RatingRequest> ratingList) {
		this.ratingList = ratingList;
	}
	@Override
	public int hashCode() {
		return Objects.hash(description, openingHours, overallRating, ratingList, storeId, storeName, totalRating);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		return Objects.equals(description, other.description) && Objects.equals(openingHours, other.openingHours)
				&& Objects.equals(overallRating, other.overallRating) && Objects.equals(ratingList, other.ratingList)
				&& Objects.equals(storeId, other.storeId) && Objects.equals(storeName, other.storeName)
				&& Objects.equals(totalRating, other.totalRating);
	}
	


}
