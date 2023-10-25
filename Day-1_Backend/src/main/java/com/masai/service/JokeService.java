package com.masai.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public interface JokeService {
	public String generateJoke(String topic) throws JsonProcessingException;
}
