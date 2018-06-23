package com.restaurants.finder.restaurant.service.imp;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurants.finder.restaurant.client.RestaurantServiceClient;
import com.restaurants.finder.restaurant.dao.RestaurantRepository;
import com.restaurants.finder.restaurant.model.Restaurant;
import com.restaurants.finder.restaurant.service.RestaurantService;

import ma.glasnost.orika.MapperFacade;

@Service
public class RestaurantServiceImp implements RestaurantService {

	@Autowired
	private RestaurantServiceClient restaurantClient;

	@Autowired
	private RestaurantRepository restaurantDAO;

	@Autowired
	private MapperFacade mapperFacade;

	@Autowired
	private Executor executor;

	@Override
	public CompletableFuture<List<Restaurant>> findRegisteredRestaurantsByName(String name) {
		return restaurantDAO.findByNameLike(name).thenApplyAsync((restaurants) -> {
			return restaurants;
		}, executor);
	}

	@Override
	public CompletableFuture<List<Restaurant>> findAvailableRestaurants(String name) {
		return restaurantClient.findByName(name).thenApply(restaurants -> restaurants.stream()
				.map(restaurant -> mapperFacade.map(restaurant, Restaurant.class)).collect(Collectors.toList()));
	}

}
