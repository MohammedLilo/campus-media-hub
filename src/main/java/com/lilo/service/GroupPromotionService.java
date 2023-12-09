package com.lilo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.GroupPromotion;

public interface GroupPromotionService {
	List<GroupPromotion> findAllByGroupId(int groupId);

	Page<GroupPromotion> findAllByGroupId(int groupId, int pageNumber, int pageSize, Sort sort);

	@Transactional
	void save(GroupPromotion groupPromotion);

	GroupPromotion findById(int id);

}
