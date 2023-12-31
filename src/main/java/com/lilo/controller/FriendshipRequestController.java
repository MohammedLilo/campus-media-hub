package com.lilo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lilo.dto.FriendshipRequestsDTO;
import com.lilo.entity.FriendshipRequest;
import com.lilo.exception.EllementAlreadyExistsException;
import com.lilo.exception.FriendshipAlreadyExistsException;
import com.lilo.exception.UnacceptableFriendshipRequestException;
import com.lilo.service.FriendshipRequestService;

@RestController
@RequestMapping("/api")
public class FriendshipRequestController {
	private final FriendshipRequestService friendshipRequestService;

	public FriendshipRequestController(FriendshipRequestService friendshipRequestService) {
		this.friendshipRequestService = friendshipRequestService;
	}

	@GetMapping("/friendship-requests")
	FriendshipRequestsDTO getFriendshipRequests(@RequestParam("recipientId") int recipientId,
			@RequestParam(name = "page", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Page<FriendshipRequest> page = friendshipRequestService.findByRecipientId(recipientId, pageNumber, pageSize,
				Sort.by(Order.desc("timestamp")));
		return new FriendshipRequestsDTO(page.getContent(), page.isLast());

	}

	@GetMapping("/friendship-requests/{id}")
	FriendshipRequest getFriendshipRequest(@PathVariable("id") int id) {
		return friendshipRequestService.findById(id);
	}

	@PostMapping("/friendship-requests")
	void createFriendshipRequest(@RequestParam("recipientId") int recipientId, @RequestParam("senderId") int senderId)
			throws UnacceptableFriendshipRequestException, EllementAlreadyExistsException, FriendshipAlreadyExistsException {
		friendshipRequestService.requestFriendship(senderId, recipientId);
	}

	@PutMapping("/friendship-requests/{id}/approve")
	void approveFriendshipRequest(@PathVariable("id") int id) {
		friendshipRequestService.approve(id);
	}

	@PutMapping("/friendship-requests/{id}/reject")
	void rejectFriendshipRequest(@PathVariable("id") int id) {
		friendshipRequestService.reject(id);
	}
}
