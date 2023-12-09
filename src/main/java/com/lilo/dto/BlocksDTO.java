package com.lilo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lilo.entity.Block;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BlocksDTO {
	private List<Block> blocks;
	@JsonProperty("isLast")
	private boolean isLast;
}
