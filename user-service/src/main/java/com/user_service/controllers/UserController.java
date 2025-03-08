package com.user_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "Worlddhhd") String name) {
	  return String.format("Hello %s!", name);
	}
    
}
