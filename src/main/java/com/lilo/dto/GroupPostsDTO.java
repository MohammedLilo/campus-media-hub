package com.lilo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lilo.entity.GroupPost;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GroupPostsDTO {
	private List<GroupPost> groupPosts;
	@JsonProperty("isLast")
	private boolean isLast;
}
