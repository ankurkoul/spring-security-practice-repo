package com.cybertooth.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MySecurityController {

	@RequestMapping("/")
	public String hello() {
		return "home.jsp";
	}
}