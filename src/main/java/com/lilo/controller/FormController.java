package com.lilo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormController {

	@GetMapping("/registration")
	String getuserRegistrationForm() {
		return "user-registration-form";
	}

	@GetMapping("/user-detail-form")
	String getUserDetailForm() {
		return "update-user-detail";
	}

	@GetMapping("/group-form")
	String getGroupCreationForm() {
		return "create-group";
	}

	@GetMapping("/post-form")
	String getPostForm() {
		return "create-post";
	}

	@GetMapping("/comment-form")
	String getCommentForm() {
		return "comment-form";
	}
}
