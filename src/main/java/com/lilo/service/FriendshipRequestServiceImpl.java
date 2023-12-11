package com.lilo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.FriendshipRequest;
import com.lilo.enums.RequestStatus;
import com.lilo.repository.FriendshipRequestRepository;

@Service
public class FriendshipRequestServiceImpl implements FriendshipRequestService {

	private FriendshipRequestRepository friendshipRequestRepository;
	private FriendshipService friendshipService;

	public FriendshipRequestServiceImpl(FriendshipRequestRepository friendshipRequestRepository,
			FriendshipService friendshipService) {
		this.friendshipRequestRepository = friendshipRequestRepository;
		this.friendshipService = friendshipService;
	}

	@Override
	public List<FriendshipRequest> findAllBySenderId(int senderId) {
		List<FriendshipRequest> requests = friendshipRequestRepository.findBySenderIdAndStatus(senderId,
				RequestStatus.PENDING);
		return requests;
	}

	@Override
	public List<FriendshipRequest> findAllByRecipientId(int Recipient) {
		List<FriendshipRequest> requests = friendshipRequestRepository.findByRecipientIdAndStatus(Recipient,
				RequestStatus.PENDING);
		return requests;
	}

	@Override
	public Page<FriendshipRequest> findPendingBySenderId(int senderId, int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<FriendshipRequest> page = friendshipRequestRepository.findBySenderIdAndStatus(senderId,
				RequestStatus.PENDING, pageable);

		return page;
	}

	@Override
	public Page<FriendshipRequest> findPendingByRecipientId(int recipientId, int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<FriendshipRequest> page = friendshipRequestRepository.findByRecipientIdAndStatus(recipientId,
				RequestStatus.PENDING, pageable);

		return page;
	}

	@Override
	public FriendshipRequest findById(int id) {
		return friendshipRequestRepository.findById(id).get();
	}

	@Override
	public FriendshipRequest findByIdAndRecipientId(int id, int recipientId) {
		FriendshipRequest existingFriendshipRequest = friendshipRequestRepository.findById(id).get();

		if (existingFriendshipRequest != null)
			if (existingFriendshipRequest.getRecipientId() == recipientId)
				return existingFriendshipRequest;
			else
				throw new IllegalArgumentException("requested friendshipRequest doesn't match with the recipientId");
		else
			throw new NoSuchElementException("No such element");
	}

	@Override
	public void requestFriendship(int senderId, int recipientId) {
		friendshipRequestRepository.save(new FriendshipRequest(senderId, recipientId));
	}

	@Override
	public void requestFriendship(FriendshipRequest friendshipRequest) {
		friendshipRequestRepository.save(friendshipRequest);

	}

	@Override
	public void approve(int id) {
		FriendshipRequest existingFriendshipRequest = friendshipRequestRepository.findById(id).get();

		if (existingFriendshipRequest.getDecisionTimestamp() == null) {
			existingFriendshipRequest.setDecisionTimestamp(LocalDateTime.now());
			existingFriendshipRequest.setStatus(RequestStatus.APPROVED);

			friendshipRequestRepository.save(existingFriendshipRequest);
			friendshipService.save(existingFriendshipRequest.getSenderId(), existingFriendshipRequest.getRecipientId());
		} else
			throw new RuntimeException();

	}

	@Override
	public void reject(int id) {
		FriendshipRequest existingFriendshipRequest = friendshipRequestRepository.findById(id).get();

		if (existingFriendshipRequest.getDecisionTimestamp() == null) {
			existingFriendshipRequest.setDecisionTimestamp(LocalDateTime.now());
			existingFriendshipRequest.setStatus(RequestStatus.REJECTED);

			friendshipRequestRepository.save(existingFriendshipRequest);
		} else
			throw new RuntimeException();
	}

}
