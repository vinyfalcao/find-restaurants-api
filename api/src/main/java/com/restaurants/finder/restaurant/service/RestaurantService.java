package com.restaurants.finder.restaurant.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.restaurants.finder.restaurant.model.Restaurant;

public interface RestaurantService {

	CompletableFuture<List<Restaurant>> findRegisteredRestaurantsByName(String name);

	CompletableFuture<List<Restaurant>> findAvailableRestaurants(String name);

}
