package com.app.website.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
//@RestController
public class WebsiteController {
	
	@RequestMapping("index")
	public String index() {
		return "index";
	}

}
