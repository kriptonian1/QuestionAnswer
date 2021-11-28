package com.app.website.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Configuration
@Controller
public class WebsiteController {
	
//	@ResponseBody
	@RequestMapping(value = {"/", "/home", "/index"})
	public String index() {
		return "index";
	}

}