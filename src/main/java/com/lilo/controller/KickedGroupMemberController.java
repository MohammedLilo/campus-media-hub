package com.lilo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lilo.dto.KickedGroupMembersDTO;
import com.lilo.entity.KickedGroupMember;
import com.lilo.service.KickedGroupMemberService;

@RestController
@RequestMapping("/api")
public class KickedGroupMemberController {
	private final KickedGroupMemberService kickedGroupMemberService;

	public KickedGroupMemberController(KickedGroupMemberService kickedGroupMemberService) {
		this.kickedGroupMemberService = kickedGroupMemberService;
	}

	@GetMapping("/groups/{groupId}/kicked-group-members")
	KickedGroupMembersDTO getKickedGroupMembers(@PathVariable("groupId") int groupId,
			@RequestParam(name = "page", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "15") int pageSize) {
		Page<KickedGroupMember> page = kickedGroupMemberService.findAllByGroupId(groupId, pageNumber, pageSize,
				Sort.by(Order.desc("kickTimestamp")));
		KickedGroupMembersDTO kickedMembers = new KickedGroupMembersDTO(page.getContent(), page.isLast());
		return kickedMembers;
	}

	@GetMapping("/groups/{groupId}/kicked-group-members/{id}")
	KickedGroupMember getKickedGroupMember(@PathVariable("id") int id) {
		KickedGroupMember kickedGroupMember = kickedGroupMemberService.findById(id);
		return kickedGroupMember;
	}

}
