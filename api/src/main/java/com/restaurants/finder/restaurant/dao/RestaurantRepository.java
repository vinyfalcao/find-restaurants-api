package com.restaurants.finder.restaurant.dao;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.restaurants.finder.common.repository.AsyncRepository;
import com.restaurants.finder.restaurant.model.Restaurant;

/**
 * 
 * @author Vinicius Falcão
 *
 */
public interface RestaurantRepository extends AsyncRepository<Restaurant, Long> {

	@Query("Select r from Restaurant r where r.name like %:name%")
	CompletableFuture<List<Restaurant>> findByNameLike(@Param("name") String name);

}
