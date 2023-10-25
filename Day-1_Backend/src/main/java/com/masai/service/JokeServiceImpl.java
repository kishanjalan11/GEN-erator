package com.masai.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JokeServiceImpl implements JokeService{

	@Value("${api.openai.secretkey}")
	private String secretKey;
	@Value("${api.openai.url}")
	private String url;
	
	RestTemplate restTemplate;
	ObjectMapper objectMapper;
	HttpHeaders httpHeaders;
	
	public JokeServiceImpl() {
		super();
	}

	@Autowired
	public JokeServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper, HttpHeaders httpHeaders) {
		super();
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
		this.httpHeaders = httpHeaders;
	}


	public String generateJoke(String topic) throws JsonProcessingException{

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("Authorization", "Bearer " + secretKey);
		
		Map<String,String> userRole=new HashMap<>();
		userRole.put("role", "user");
		userRole.put("content", topic);
		
		Map<String, String> systemRole = new HashMap<>();
		systemRole.put("role", "system");
		systemRole.put("content", "You are a Joke Creater who generates creative and very funny jokes");
		
		List<Map<String, String>> roles = new ArrayList<>();
		roles.add(systemRole);
		roles.add(userRole);
		
		Body body = new Body("gpt-3.5-turbo", roles, 100, 1.0, 0.9, 1.0, 1.0);
		
		HttpEntity<String> requestEntity = new HttpEntity<>(objectMapper.writeValueAsString(body), httpHeaders);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
		
		JsonNode responseJson = objectMapper.readTree(responseEntity.getBody());
		String response = responseJson.get("choices").get(0).get("message").get("content").asText();
		return response;
	}

}
