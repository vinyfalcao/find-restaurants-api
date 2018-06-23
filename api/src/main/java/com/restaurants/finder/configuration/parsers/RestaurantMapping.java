package com.restaurants.finder.configuration.parsers;

import org.springframework.stereotype.Component;

import com.restaurants.finder.restaurant.client.model.RestaurantGoogleVO;
import com.restaurants.finder.restaurant.model.Restaurant;

import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

/**
 * 
 * @author Vinicius Falcão
 *
 */
@Component
public class RestaurantMapping implements OrikaMapperFactoryConfigurer {

	@Override
	public void configure(MapperFactory orikaMapperFactory) {
		orikaMapperFactory.classMap(Restaurant.class, RestaurantGoogleVO.class).field("googleId", "id").byDefault()
				.register();
	}
}
