package com.lilo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.GroupJoinRequest;

public interface GroupJoinRequestService {

	List<GroupJoinRequest> findAllByGroupId(int groupId);

	List<GroupJoinRequest> findAllPendingByGroupId(int groupId);

	List<GroupJoinRequest> findAllRejectedByGroupId(int groupId);

	Page<GroupJoinRequest> findAllByGroupId(int groupId, int pageNo, int size, Sort sort);

	Page<GroupJoinRequest> findPendingRequestsByGroupId(int groupId, int pageNo, int size, Sort sort);

	Page<GroupJoinRequest> findRejectedRequestsByGroupId(int groupId, int pageNo, int size, Sort sort);

	GroupJoinRequest findById(int GroupJoinRequestId);

//	@Transactional
//	void requestToJoin(GroupJoinRequest groupJoinRequest);

	@Transactional
	void requestToJoin(int userId, int groupId);

//	@Transactional
//	void approve(GroupJoinRequest groupJoinRequest);
	@Transactional
	void approve(int id);

//	@Transactional
//	void reject(GroupJoinRequest groupJoinRequest);
	@Transactional
	void reject(int id);
}
