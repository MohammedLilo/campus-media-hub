package com.lilo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lilo.entity.GroupMembership;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GroupMembershipsDTO {
	private List<GroupMembership> groupMemberships;
	@JsonProperty("isLast")
	private boolean isLast;
}
