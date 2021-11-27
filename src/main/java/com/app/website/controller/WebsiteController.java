package com.app.website.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@Controller
public class WebsiteController {
	
	@RequestMapping("index")
	public String index() {
		return "index";
	}

}
