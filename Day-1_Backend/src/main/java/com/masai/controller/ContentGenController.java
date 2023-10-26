package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.masai.service.ContentGenService;

@RestController
@CrossOrigin(origins = "*")
public class ContentGenController {

	@Autowired
	ContentGenService genService;
	
	@PostMapping("/{type}/{topic}")
	ResponseEntity<String> generate(@PathVariable String type, @PathVariable String topic) throws JsonProcessingException{
		return new ResponseEntity<String>(genService.generate(type,topic), HttpStatus.OK);
	}
}
