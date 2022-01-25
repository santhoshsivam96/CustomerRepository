package com.infy.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usertable")
public class RatingRequest {
@Id
@Column(name="user_id")
private String userId;
private Integer rating;
private String name;

public RatingRequest() {}
public RatingRequest(String userId, Integer rating, String name) {
	super();
	this.userId = userId;
	this.rating = rating;
	this.name = name;
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
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
@Override
public int hashCode() {
	return Objects.hash(name, rating, userId);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	RatingRequest other = (RatingRequest) obj;
	return Objects.equals(name, other.name) && Objects.equals(rating, other.rating)
			&& Objects.equals(userId, other.userId);
}



}
