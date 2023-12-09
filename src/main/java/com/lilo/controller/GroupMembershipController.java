package com.lilo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lilo.dto.GroupMembershipsDTO;
import com.lilo.entity.GroupMembership;
import com.lilo.enums.GroupRoles;
import com.lilo.service.GroupMembershipService;

@RestController
@RequestMapping("/api")
public class GroupMembershipController {
	private final GroupMembershipService groupMembershipService;

	public GroupMembershipController(GroupMembershipService groupMembershipService) {
		this.groupMembershipService = groupMembershipService;
	}

	@GetMapping("/groups/{groupId}/group-memberships")
	GroupMembershipsDTO getGroupMemberships(@PathVariable("groupId") int groupId,
			@RequestParam(name = "page", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "20") int pageSize) {
		Page<GroupMembership> page = groupMembershipService.findAllByGroupId(groupId, pageNumber, pageSize,
				Sort.by(Order.asc("firstName"), Order.asc("lastName")));
		GroupMembershipsDTO memberships = new GroupMembershipsDTO(page.getContent(), page.isLast());

		return memberships;
	}

	@GetMapping("/groups/{groupId}/group-memberships/{id}")
	GroupMembership getGroupMembership(@PathVariable("id") int id) {
		GroupMembership requestedMembership = groupMembershipService.findById(id);
		return requestedMembership;
	}

	@PutMapping("/groups/{groupId}/group-memberships/{id}/promote")
	void promoteGroupMembership(@PathVariable("id") int id, @RequestParam("promoterId") int promoterId,
			@RequestParam("newRole") GroupRoles newRole) {
		groupMembershipService.promote(id, promoterId, newRole);
	}

	@DeleteMapping("/groups/{groupId}/group-memberships/{id}/kick")
	void kickGroupMembership(@PathVariable("id") int id) {
		groupMembershipService.kick(id);
	}

	@DeleteMapping("/groups/{groupId}/group-memberships/{id}")
	void leaveGroupMembership(@PathVariable("id") int id) {
		groupMembershipService.leave(id);
	}
}
