package com.restaurants.finder.restaurant.client.imp;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.restaurants.finder.restaurant.client.RestaurantServiceClient;
import com.restaurants.finder.restaurant.client.model.RestaurantGoogleVO;

/**
 * 
 * @author Vinicius Falcão
 *
 */
@Service
public class RestaurantServiceClientImp implements RestaurantServiceClient {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Executor executor;

	@Value("${url.base.google.places}")
	private String BASE_URL;

	@Value("${google.api.key}")
	private String API_KEY;

	@Value("${google.api.latlong}")
	private String LAT_LONG;

	@Override
	public CompletableFuture<List<RestaurantGoogleVO>> findByName(String keyword) {
		return CompletableFuture.supplyAsync(() -> {
			String json = restTemplate.getForEntity(getGoogleAPIUrl(keyword), String.class).getBody();
			JsonArray objectJson = new JsonParser().parse(json).getAsJsonObject().get("results").getAsJsonArray();
			Type listType = new TypeToken<ArrayList<RestaurantGoogleVO>>() {
			}.getType();
			return new Gson().fromJson(objectJson, listType);
		}, executor);
	}

	private String getGoogleAPIUrl(String keyword) {
		StringBuilder sb = new StringBuilder(BASE_URL);
		sb.append("?location=" + LAT_LONG);
		sb.append("&radius=5000");
		sb.append("&type=restaurant");
		sb.append("&keyword=" + keyword);
		sb.append("&key=" + API_KEY);
		return sb.toString();
	}

}
