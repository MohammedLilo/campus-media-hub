package com.lilo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lilo.entity.Friendship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FriendshipsDTO {
	private List<Friendship> friendships;
	@JsonProperty("isLast")
	private boolean isLast;
}
