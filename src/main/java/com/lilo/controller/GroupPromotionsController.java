package com.lilo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lilo.dto.GroupPromotionsDTO;
import com.lilo.entity.GroupPromotion;
import com.lilo.service.GroupPromotionService;

@RestController
@RequestMapping("/api")
public class GroupPromotionsController {
	private final GroupPromotionService groupPromotionService;

	public GroupPromotionsController(GroupPromotionService groupPromotionService) {
		this.groupPromotionService = groupPromotionService;
	}

	@GetMapping("/group-promotions")
	GroupPromotionsDTO getGroupPromotions(@RequestParam("groupId") int groupId,
			@RequestParam(name = "page", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "20") int pageSize) {
		Page<GroupPromotion> page = groupPromotionService.findAllByGroupId(groupId, pageNumber, pageSize,
				Sort.by(Order.desc("promotionTimestamp")));

		return new GroupPromotionsDTO(page.getContent(), page.isLast());
	}

	@GetMapping("/group-promotions/{id}")
	GroupPromotion getGroupPromotion(@PathVariable("id") int id) {
		return groupPromotionService.findById(id);
	}
}
