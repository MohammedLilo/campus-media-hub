package com.lilo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lilo.entity.FriendshipRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FriendshipRequestsDTO {

	private List<FriendshipRequest> friendshipRequests;
	@JsonProperty("isLast")
	private boolean isLast;
}
