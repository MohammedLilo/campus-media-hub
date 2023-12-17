package com.lilo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lilo.dto.GroupJoinRequestsDTO;
import com.lilo.entity.GroupJoinRequest;
import com.lilo.exception.EllementAlreadyExistsException;
import com.lilo.exception.GroupMembershipAlreadyExistsException;
import com.lilo.service.GroupJoinRequestService;

@RestController
@RequestMapping("/api")
public class GroupJoinRequestController {
	private final GroupJoinRequestService groupJoinRequestService;

	public GroupJoinRequestController(GroupJoinRequestService groupJoinRequestService) {
		this.groupJoinRequestService = groupJoinRequestService;
	}

	@GetMapping("/group-join-requests")
	GroupJoinRequestsDTO getGroupJoinRequests(@RequestParam("groupId") int groupId,
			@RequestParam(name = "page", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "15") int pageSize) {
		Page<GroupJoinRequest> page = groupJoinRequestService.findByGroupId(groupId, pageNumber, pageSize,
				Sort.by(Order.desc("timestamp")));
		return new GroupJoinRequestsDTO(page.getContent(), page.isLast());
	}

	@GetMapping("/group-join-requests/{id}")
	GroupJoinRequest getGroupJoinRequest(@PathVariable("id") int id) {
		return groupJoinRequestService.findById(id);
	}

	@PostMapping("/group-join-requests")
	void createGroupJoinRequest(@RequestParam("userId") int userId, @RequestParam("groupId") int groupId)
			throws EllementAlreadyExistsException, GroupMembershipAlreadyExistsException {
		groupJoinRequestService.requestToJoin(userId, groupId);
	}

	@DeleteMapping("/group-join-requests/{id}/approve")
	void approveGroupJoinRequest(@PathVariable("id") int id) {
		groupJoinRequestService.approve(id);
	}

	@DeleteMapping("/group-join-requests/{id}/reject")
	void rejectGroupJoinRequest(@PathVariable("id") int id) {
		groupJoinRequestService.reject(id);
	}
}
