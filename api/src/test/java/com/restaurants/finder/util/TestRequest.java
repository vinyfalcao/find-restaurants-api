package com.restaurants.finder.util;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class TestRequest {

    private MockMvc mockMvc;
    private MockHttpServletRequestBuilder requestBuilder;

    public TestRequest(MockMvc mockMvc, MockHttpServletRequestBuilder requestBuilder) {
	this.mockMvc = mockMvc;
	this.requestBuilder = requestBuilder;
    }

    public TestResponse send() throws Exception {
	requestBuilder.header("Authorization",
		"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmbGFmanJAZ21haWwuY29tIiwiZXhwIjoxNTIzNjY2NDExfQ."
			+ "tT1qA_jU67jsS0YtXUMHkLh7lX_3OMo066wKOfr8V8nHg7gSwzzTgDcQQeFRLzaEzcw8-C-vLF1VRMVGXJTrhw");

	MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
	if (mvcResult.getRequest().getAsyncContext() != null) {
	    mvcResult.getRequest().getAsyncContext().setTimeout(100000);
	}
	return new TestResponse(mockMvc.perform(MockMvcRequestBuilders.asyncDispatch(mvcResult)));
    }
}
