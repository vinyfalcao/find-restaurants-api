package com.restaurants.finder.commom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurants.finder.util.TestRequest;

public abstract class AbstractTestController {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private String parseJson(Object object) {
	try {
	    return MAPPER.writeValueAsString(object);
	} catch (JsonProcessingException e) {
	    return null;
	}
    }

    private String getBasePath() {
	return "http://localhost:" + getServerPort() + getUrl();
    }

    protected TestRequest get() {
	return get("");
    }

    protected TestRequest get(String path) {
	return new TestRequest(mockMvc, getWithPath(path));
    }

    protected TestRequest post(Object objectToJson) {
	return new TestRequest(mockMvc, postRequestJson(objectToJson));
    }

    protected TestRequest post(String path) {
	return new TestRequest(mockMvc, postWithPath(path));
    }

    protected TestRequest post(String path, Object objectToJson) {
	return new TestRequest(mockMvc, postRequestJson(path, objectToJson));
    }

    protected TestRequest put(Object objectToJson) {
	return new TestRequest(mockMvc, putRequestJson(objectToJson));
    }

    protected TestRequest put(String path) {
	return new TestRequest(mockMvc, putWithPath(path));
    }

    protected TestRequest put(String path, Object objectToJson) {
	return new TestRequest(mockMvc, putRequestJson(path, objectToJson));
    }

    private MockHttpServletRequestBuilder postRequestJson(Object objectToJson) {
	return postRequestJson("", objectToJson);
    }

    private MockHttpServletRequestBuilder postRequestJson(String path, Object objectToJson) {
	return postWithPath(path).content(this.parseJson(objectToJson)).contentType("application/json");
    }

    private MockHttpServletRequestBuilder putRequestJson(Object objectToJson) {
	return putRequestJson("", objectToJson);
    }

    private MockHttpServletRequestBuilder putRequestJson(String path, Object objectToJson) {
	return putRequestJson(path).content(this.parseJson(objectToJson)).contentType("application/json");
    }

    private MockHttpServletRequestBuilder getWithPath(String path) {
	return MockMvcRequestBuilders.get(getBasePath() + path);
    }

    private MockHttpServletRequestBuilder postWithPath(String path) {
	return MockMvcRequestBuilders.post(getBasePath() + path);
    }

    private MockHttpServletRequestBuilder putWithPath(String path) {
	return MockMvcRequestBuilders.put(getBasePath() + path);
    }

    /**
     * Random port that is generated using WebEnvironment.RANDOM_PORT.
     * 
     * @return
     */
    protected abstract int getServerPort();

    /**
     * Base URL of the Endpoint.
     * 
     * @return
     */
    protected abstract String getUrl();
}
