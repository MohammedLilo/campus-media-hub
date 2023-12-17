package com.lilo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lilo.entity.GroupJoinRequest;

public interface GroupJoinRequestRepository extends JpaRepository<GroupJoinRequest, Integer> {

	Page<GroupJoinRequest> findByGroupId(int groupId, Pageable pageable);

	boolean existsByUserIdAndGroupId(int userId, int groupId);

}
