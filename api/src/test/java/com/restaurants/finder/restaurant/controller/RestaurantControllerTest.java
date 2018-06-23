package com.restaurants.finder.restaurant.controller;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.restaurants.finder.commom.controller.AbstractTestController;
import com.restaurants.finder.testrules.DatabaseClassRule;

import lombok.Getter;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Getter
public class RestaurantControllerTest extends AbstractTestController {

	@LocalServerPort
	private int serverPort;

	private final String url = "/api/restaurant/";

	@ClassRule
	public static DatabaseClassRule dataBaseContainer = new DatabaseClassRule("postgres:9.6-alpine", "5432", "postgres",
			"postgres");

	@Test
	public void findRegisteredRestaurantsByName_Success() throws Exception {
		get(url + "/byName/" + "Restaurant").send().isOk().hasSize(3);
	}

}
