package com.lilo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.GroupMembership;
import com.lilo.enums.GroupRoles;

public interface GroupMembershipService {

	Page<GroupMembership> findAllByGroupId(int groupId, int pageNo, int size, Sort sort);

	GroupMembership findById(int groupMembershipId);

	GroupMembership findByUserIdAndGroupId(int userId, int groupId);

	boolean existsByUserIdAndGroupId(int userId, int groupId);

	@Transactional
	void save(int userId, int groupId);

	@Transactional
	void update(GroupMembership groupMembership);

	@Transactional
	void promote(int id, int promoterId, GroupRoles newRole);

	@Transactional
	void kick(int id);

	@Transactional
	void leave(int id);

}
