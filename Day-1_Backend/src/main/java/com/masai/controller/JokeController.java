package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.masai.service.JokeService;

@RestController
public class JokeController {

	@Autowired
	JokeService jokeService;
	
	@PostMapping("/joke/{topic}")
	ResponseEntity<String> generateJoke(@PathVariable String topic) throws JsonProcessingException{
		return new ResponseEntity<String>(jokeService.generateJoke(topic), HttpStatus.OK);
	}
}
