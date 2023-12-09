package com.lilo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lilo.entity.GroupPromotion;

public interface GroupPromotionRepository extends JpaRepository<GroupPromotion, Integer> {

	List<GroupPromotion> findByGroupId(int groupId);

	Page<GroupPromotion> findByGroupId(int groupId, Pageable pageable);

}
