package com.restaurants.finder.util;

import org.hamcrest.Matchers;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class TestResponse {
	private ResultActions resultActions;

	public TestResponse(ResultActions resultActions) {
		this.resultActions = resultActions;
	}

	public TestResponse expect(String field, Object value) throws Exception {
		this.resultActions.andExpect(MockMvcResultMatchers.jsonPath(field, Matchers.equalTo(value)));
		return this;
	}

	public TestResponse expect(String field, String value) throws Exception {
		this.resultActions.andExpect(MockMvcResultMatchers.jsonPath(field, Matchers.is(value)));
		return this;
	}

	public TestResponse expectResult(ResultMatcher resultMatcher) throws Exception {
		this.resultActions.andExpect(resultMatcher);
		return this;
	}

	public TestResponse hasSize(int size) throws Exception {
		this.resultActions.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(size)));
		return this;
	}

	public TestResponse isBadRequest() throws Exception {
		this.resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
		return this;
	}

	public TestResponse isNotNull(String field) throws Exception {
		this.resultActions.andExpect(MockMvcResultMatchers.jsonPath(field, Matchers.notNullValue()));
		return this;
	}

	public TestResponse isNull(String field) throws Exception {
		this.resultActions.andExpect(MockMvcResultMatchers.jsonPath(field, Matchers.nullValue()));
		return this;
	}

	public TestResponse isOk() throws Exception {
		this.resultActions.andExpect(MockMvcResultMatchers.status().isOk());
		return this;
	}
}
