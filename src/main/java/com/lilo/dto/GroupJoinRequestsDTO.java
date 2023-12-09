package com.lilo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lilo.entity.GroupJoinRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GroupJoinRequestsDTO {
	private List<GroupJoinRequest> groupJoinRequests;
	@JsonProperty("isLast")
	private boolean isLast;
}
