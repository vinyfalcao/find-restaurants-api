package com.restaurants.finder.restaurant.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.restaurants.finder.restaurant.controller.views.RestaurantViews;

import lombok.Data;

@Entity
@Table(name = "restaurant")
@Data
public class Restaurant {

	@Id
	private Long id;
	@JsonView(RestaurantViews.DefaultView.class)
	private String name;
	@JsonView(RestaurantViews.DefaultView.class)
	private float rating;	
	@JsonView(RestaurantViews.DefaultView.class)
	private String googleId;
}
