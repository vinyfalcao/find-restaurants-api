package com.restaurants.finder.restaurant.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.restaurants.finder.restaurant.controller.views.RestaurantViews;
import com.restaurants.finder.restaurant.model.Restaurant;
import com.restaurants.finder.restaurant.service.RestaurantService;

/**
 * 
 * @author Vinicius Falcão
 *
 */
@RestController
@RequestMapping("api/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@GetMapping("/byName/{name}")
	public CompletableFuture<List<Restaurant>> findRegistered(@PathVariable("name") String name) {
		return restaurantService.findRegisteredRestaurantsByName(name);
	}

	@JsonView(RestaurantViews.DefaultView.class)
	@GetMapping("/registereds/byName/{name}")
	public CompletableFuture<List<Restaurant>> findAvailableRestaurants(@PathVariable("name") String name) {
		return restaurantService.findAvailableRestaurants(name);

	}

}
