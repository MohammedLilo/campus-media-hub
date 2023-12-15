package com.lilo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lilo.entity.GroupComment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GroupCommentsDTO {
	private List<GroupComment> groupComments;
	@JsonProperty("isLast")
	private boolean isLast;
}
