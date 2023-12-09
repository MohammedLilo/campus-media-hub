package com.lilo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lilo.entity.GroupPromotion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GroupPromotionsDTO {
	private List<GroupPromotion> groupPromotions;
	@JsonProperty("isLast")
	private boolean isLast;
}
