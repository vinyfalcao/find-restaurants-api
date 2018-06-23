package com.restaurants.finder.restaurant.client;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.restaurants.finder.restaurant.client.model.RestaurantGoogleVO;

public interface RestaurantServiceClient {

	CompletableFuture<List<RestaurantGoogleVO>> findByName(String keyword);

}
