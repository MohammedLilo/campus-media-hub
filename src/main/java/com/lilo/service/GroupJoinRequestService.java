package com.lilo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.GroupJoinRequest;
import com.lilo.exception.EllementAlreadyExistsException;
import com.lilo.exception.GroupMembershipAlreadyExistsException;

public interface GroupJoinRequestService {

	Page<GroupJoinRequest> findAllByGroupId(int groupId, int pageNo, int size, Sort sort);

	Page<GroupJoinRequest> findByGroupId(int groupId, int pageNo, int size, Sort sort);

	GroupJoinRequest findById(int GroupJoinRequestId);

	@Transactional
	void requestToJoin(int userId, int groupId)
			throws EllementAlreadyExistsException, GroupMembershipAlreadyExistsException;

	@Transactional
	void approve(int id);

	@Transactional
	void reject(int id);
}
