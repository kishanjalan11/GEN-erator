package com.masai.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public interface ContentGenService {
	public String generate(String type,String topic) throws JsonProcessingException;
}
