package com.lilo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lilo.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentsDTO {

	private List<Comment> comments;
	@JsonProperty("isLast")
	private boolean isLast;
}
