package com.lilo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lilo.entity.GroupMembership;

public interface GroupMembershipRepository extends JpaRepository<GroupMembership, Integer> {

	List<GroupMembership> findByGroupId(int groupId);

	Page<GroupMembership> findByGroupId(int groupId, Pageable pageable);

	GroupMembership findByUserIdAndGroupId(int userId,int groupId);


}
