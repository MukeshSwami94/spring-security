package com.mk.jdbcuserdetailsmanagercustomized.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/home")
	public String getHomeDetails() {
		return "THis is home details info";
	}
	@GetMapping("/user")
	public String getUserInfor() {
		return "THis is User Info";
	}
	@GetMapping("/userList")
	public String getUserList() {
		return "User List";
	}
}
