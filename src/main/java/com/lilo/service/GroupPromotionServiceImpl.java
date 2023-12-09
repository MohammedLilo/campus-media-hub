package com.lilo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.GroupPromotion;
import com.lilo.repository.GroupPromotionRepository;

@Service
public class GroupPromotionServiceImpl implements GroupPromotionService {
	private GroupPromotionRepository groupPromotionRepository;

	public GroupPromotionServiceImpl(GroupPromotionRepository groupPromotionRepository) {
		this.groupPromotionRepository = groupPromotionRepository;
	}

	@Override
	public List<GroupPromotion> findAllByGroupId(int groupId) {
		List<GroupPromotion> promotions = groupPromotionRepository.findByGroupId(groupId);

		return promotions;
	}

	@Override
	public Page<GroupPromotion> findAllByGroupId(int groupId, int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<GroupPromotion> page = groupPromotionRepository.findByGroupId(groupId, pageable);

		return page;
	}

	@Override
	public GroupPromotion findById(int id) {
		GroupPromotion requestedGroupPromotion = groupPromotionRepository.findById(id).get();
		return requestedGroupPromotion;
	}

	@Override
	public void save(GroupPromotion groupPromotion) {
		groupPromotionRepository.save(groupPromotion);
	}

}
