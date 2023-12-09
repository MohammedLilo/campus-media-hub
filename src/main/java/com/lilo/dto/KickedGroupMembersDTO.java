package com.lilo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lilo.entity.KickedGroupMember;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class KickedGroupMembersDTO {
	private List<KickedGroupMember> kickedGroupMembers;
	@JsonProperty("isLast")
	private boolean isLast;

}
