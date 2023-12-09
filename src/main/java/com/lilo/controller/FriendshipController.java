package com.lilo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lilo.dto.FriendshipsDTO;
import com.lilo.entity.Friendship;
import com.lilo.service.FriendshipService;

@RestController
@RequestMapping("/api")
public class FriendshipController {
	private final FriendshipService friendshipService;

	public FriendshipController(FriendshipService friendshipService) {
		this.friendshipService = friendshipService;
	}

	@GetMapping("/users/{userId}/friendships")
	FriendshipsDTO getFriendships(@PathVariable("userId") int userId,
			@RequestParam(name = "page", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Page<Friendship> page = friendshipService.findAllByUserId(userId, pageNumber, pageSize, Sort.unsorted());
		return new FriendshipsDTO(page.getContent(), page.isLast());
	}

	@GetMapping("/users/{userId}/friendships/{id}")
	Friendship getFriendship(@PathVariable("id") int id) {
		return friendshipService.findById(id);

	}

	@DeleteMapping("/users/{userId}/friendships/{id}")
	void deleteFriendship(@PathVariable("id") int id) {
		friendshipService.deleteById(id);
	}

}
