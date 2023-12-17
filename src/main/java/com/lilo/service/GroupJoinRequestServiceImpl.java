package com.lilo.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.GroupJoinRequest;
import com.lilo.exception.EllementAlreadyExistsException;
import com.lilo.exception.GroupMembershipAlreadyExistsException;
import com.lilo.repository.GroupJoinRequestRepository;

@Service
public class GroupJoinRequestServiceImpl implements GroupJoinRequestService {
	private GroupJoinRequestRepository groupJoinRequestRepository;
	private GroupMembershipService groupMembershipService;

	public GroupJoinRequestServiceImpl(GroupJoinRequestRepository groupJoinRequestRepository,
			@Lazy GroupMembershipService groupMembershipService) {
		this.groupJoinRequestRepository = groupJoinRequestRepository;
		this.groupMembershipService = groupMembershipService;
	}

	@Override
	public Page<GroupJoinRequest> findAllByGroupId(int groupId, int pageNo, int size, Sort sort) {
		Pageable pageable = PageRequest.of(pageNo, size, sort);
		return groupJoinRequestRepository.findByGroupId(groupId, pageable);
	}

	@Override
	public Page<GroupJoinRequest> findByGroupId(int groupId, int pageNo, int size, Sort sort) {
		Pageable pageable = PageRequest.of(pageNo, size, sort);
		return groupJoinRequestRepository.findByGroupId(groupId, pageable);
	}

	@Override
	public GroupJoinRequest findById(int GroupJoinRequestId) {
		return groupJoinRequestRepository.findById(GroupJoinRequestId).get();
	}

	@Override
	public void requestToJoin(int userId, int groupId)
			throws EllementAlreadyExistsException, GroupMembershipAlreadyExistsException {

		if (groupJoinRequestRepository.existsByUserIdAndGroupId(userId, groupId))
			throw new EllementAlreadyExistsException("a pending request already exists");
		if (groupMembershipService.existsByUserIdAndGroupId(userId, groupId))
			throw new GroupMembershipAlreadyExistsException("a group membership already exists for this user.");

		groupJoinRequestRepository.save(new GroupJoinRequest(userId, groupId, LocalDateTime.now()));
	}

	@Override
	public void approve(int id) {
		GroupJoinRequest request = groupJoinRequestRepository.findById(id).get();
		groupJoinRequestRepository.deleteById(id);
		groupMembershipService.save(request.getUserId(), request.getGroupId());
	}

	@Override
	public void reject(int id) {
		if (!groupJoinRequestRepository.existsById(id))
			throw new NoSuchElementException("cannot delete a non existing element.");
		groupJoinRequestRepository.deleteById(id);

	}

}
