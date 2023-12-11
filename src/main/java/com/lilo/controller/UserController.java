package com.lilo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lilo.constants.ImagesPaths;
import com.lilo.dto.UsersDTO;
import com.lilo.entity.User;
import com.lilo.entity.UserDetail;
import com.lilo.entity.UserPrivacy;
import com.lilo.enums.Visibility;
import com.lilo.fileUtilities.FileUtility;
import com.lilo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	UsersDTO users(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Page<User> usersPage = userService.findAll(pageNumber, pageSize, Sort.by(Order.desc("registrationTimestamp")));
		return new UsersDTO(usersPage.getContent(), usersPage.isLast());

	}

	@GetMapping("/users/{id}")
	User getUserById(@PathVariable("id") int id) {
		return userService.findById(id);

	}

	@PostMapping("/users")
	void registerUser(@ModelAttribute User user) {
		user.setUserPrivacy(new UserPrivacy(Visibility.ALL, Visibility.NONE, Visibility.NONE));
		userService.save(user);
	}

	@PutMapping("/users/{id}")
	void updateUser(@PathVariable("id") int id, @ModelAttribute User user) {
		userService.update(id, user);
	}

	@DeleteMapping("users/{id}")
	void deleteUser(@PathVariable("id") int id) {
		userService.deleteById(id);
		FileUtility.deleteImageFiles(ImagesPaths.PROFILE_PICTURE_FOLDER + "//" + id);
	}

	@PutMapping("/users/{id}/user-detail")
	void updateUserDetail(@PathVariable("id") int id, @ModelAttribute UserDetail userDetail,
			@ModelAttribute MultipartFile imageFile) {
		String filePath = ImagesPaths.PROFILE_PICTURE_FOLDER + "//" + id;

		FileUtility.saveImage(imageFile, filePath);

		User user = userService.findById(id);
		userDetail.setProfilePicture(filePath);
		user.setUserDetail(userDetail);

		userService.update(id, user);
	}

	@PostMapping("/users/{id}/user-privacy")
	void createUserPrivacy(@PathVariable("id") int id, @ModelAttribute UserPrivacy userPrivacy) {
		User user = userService.findById(id);
		user.setUserPrivacy(userPrivacy);
		userService.update(id, user);
	}

	@PutMapping("/users/{id}/user-privacy")
	void updateUserPrivacy(@PathVariable("id") int id, @ModelAttribute UserPrivacy userPrivacy) {
		User user = userService.findById(id);
		user.setUserPrivacy(userPrivacy);
		userService.update(id, user);
	}
}
