package com.lilo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.GroupMembership;
import com.lilo.enums.GroupRoles;

public interface GroupMembershipService {

	List<GroupMembership> findAllByGroupId(int groupId);

	Page<GroupMembership> findAllByGroupId(int groupId, int pageNo, int size, Sort sort);

	GroupMembership findById(int groupMembershipId);

	GroupMembership findByUserIdAndGroupId(int userId, int groupId);

//	@Transactional
//	void save(GroupMembership groupMembership);
	@Transactional
	void save(int userId, int groupId);

	@Transactional
	void update(GroupMembership groupMembership);

//	@Transactional
//	void promote(GroupMembership groupMembership, GroupRoles groupRoles);
	@Transactional
	void promote(int id, int promoterId, GroupRoles groupRoles);

	@Transactional
	void kick(GroupMembership groupMembership);

	@Transactional
	void kick(int id);

	@Transactional
	void leave(GroupMembership groupMembership);

	@Transactional
	void leave(int id);
}
