package com.lilo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.GroupJoinRequest;
import com.lilo.enums.RequestStatus;
import com.lilo.repository.GroupJoinRequestRepository;

@Service
public class GroupJoinRequestServiceImpl implements GroupJoinRequestService {
	private GroupJoinRequestRepository groupJoinRequestRepository;
	private GroupMembershipService groupMembershipService;
	private UserService userService;

	public GroupJoinRequestServiceImpl(GroupJoinRequestRepository groupJoinRequestRepository,
			GroupMembershipService groupMembershipService, UserService userService) {
		this.groupJoinRequestRepository = groupJoinRequestRepository;
		this.groupMembershipService = groupMembershipService;
		this.userService = userService;
	}

	@Override
	public List<GroupJoinRequest> findAllByGroupId(int groupId) {
		List<GroupJoinRequest> requests = groupJoinRequestRepository.findByGroupId(groupId);

		return requests;
	}

	@Override
	public List<GroupJoinRequest> findAllPendingByGroupId(int groupId) {
		List<GroupJoinRequest> requests = groupJoinRequestRepository.findByGroupIdAndStatus(groupId,
				RequestStatus.PENDING);

		return requests;
	}

	@Override
	public List<GroupJoinRequest> findAllRejectedByGroupId(int groupId) {
		List<GroupJoinRequest> requests = groupJoinRequestRepository.findByGroupIdAndStatus(groupId,
				RequestStatus.REJECTED);

		return requests;
	}

	@Override
	public Page<GroupJoinRequest> findAllByGroupId(int groupId, int pageNo, int size, Sort sort) {
		Pageable pageable = PageRequest.of(pageNo, size, sort);
		Page<GroupJoinRequest> page = groupJoinRequestRepository.findByGroupId(groupId, pageable);

		return page;
	}

	@Override
	public Page<GroupJoinRequest> findPendingRequestsByGroupId(int groupId, int pageNo, int size, Sort sort) {
		Pageable pageable = PageRequest.of(pageNo, size, sort);

		Page<GroupJoinRequest> page = groupJoinRequestRepository.findByGroupIdAndStatus(groupId, RequestStatus.PENDING,
				pageable);

		return page;
	}

	@Override
	public Page<GroupJoinRequest> findRejectedRequestsByGroupId(int groupId, int pageNo, int size, Sort sort) {
		Pageable pageable = PageRequest.of(pageNo, size, sort);

		Page<GroupJoinRequest> page = groupJoinRequestRepository.findByGroupIdAndStatus(groupId, RequestStatus.REJECTED,
				pageable);

		return page;
	}

	@Override
	public GroupJoinRequest findById(int GroupJoinRequestId) {
		GroupJoinRequest request = groupJoinRequestRepository.findById(GroupJoinRequestId).get();

		return request;
	}

//	@Override
//	public void requestToJoin(GroupJoinRequest groupJoinRequest) {
//		groupJoinRequest.setStatus(RequestStatus.PENDING);
//		groupJoinRequest.setRequestTimestamp(LocalDateTime.now());
//
//		groupJoinRequestRepository.save(groupJoinRequest);
//	}

	@Override
	public void requestToJoin(int userId, int groupId) {
		boolean requestExists = groupJoinRequestRepository.existsByUserIdAndGroupIdPending(userId, groupId);

		if (!requestExists) {
			GroupJoinRequest request = new GroupJoinRequest(userId, groupId);

			request.setStatus(RequestStatus.PENDING);
			request.setTimestamp(LocalDateTime.now());

			groupJoinRequestRepository.save(request);
		} else
			throw new RuntimeException("a pending request already exists");

	}

//	@Override
//	public void approve(GroupJoinRequest groupJoinRequest) {
//		groupJoinRequest.setFirstName(null);
//
//		groupJoinRequest.setStatus(RequestStatus.APPROVED);
//		groupJoinRequest.setDecisionTimestamp(LocalDateTime.now());
//
//		groupJoinRequestRepository.save(groupJoinRequest);
//
//		groupMembershipService.save(new GroupMembership(groupJoinRequest.getUserId(), groupJoinRequest.getGroupId(),
//				groupJoinRequest.getFirstName(), groupJoinRequest.getLastName()));
//
//	}

	@Override
	public void approve(int id) {
		GroupJoinRequest request = groupJoinRequestRepository.findById(id).get();

		request.setStatus(RequestStatus.APPROVED);
		request.setDecisionTimestamp(LocalDateTime.now());

		groupJoinRequestRepository.save(request);
		groupMembershipService.save(request.getUserId(), request.getGroupId());
	}

	@Override
	public void reject(int id) {
		GroupJoinRequest request = groupJoinRequestRepository.findById(id).get();

		request.setStatus(RequestStatus.REJECTED);
		request.setDecisionTimestamp(LocalDateTime.now());

		groupJoinRequestRepository.save(request);
	}

}
