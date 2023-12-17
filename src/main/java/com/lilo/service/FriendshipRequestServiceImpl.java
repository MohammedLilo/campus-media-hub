package com.lilo.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.FriendshipRequest;
import com.lilo.exception.EllementAlreadyExistsException;
import com.lilo.exception.FriendshipAlreadyExistsException;
import com.lilo.exception.MismatchedFriendshipRequestAndRecipientId;
import com.lilo.exception.UnacceptableFriendshipRequestException;
import com.lilo.repository.FriendshipRequestRepository;

@Service
public class FriendshipRequestServiceImpl implements FriendshipRequestService {

	private final FriendshipRequestRepository friendshipRequestRepository;
	private final FriendshipService friendshipService;
	private final BlockService blockService;

	public FriendshipRequestServiceImpl(FriendshipRequestRepository friendshipRequestRepository,
			@Lazy FriendshipService friendshipService, @Lazy BlockService blockService) {
		this.friendshipRequestRepository = friendshipRequestRepository;
		this.friendshipService = friendshipService;
		this.blockService = blockService;
	}

	@Override
	public Page<FriendshipRequest> findBySenderId(int senderId, int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		return friendshipRequestRepository.findBySenderId(senderId, pageable);
	}

	@Override
	public Page<FriendshipRequest> findByRecipientId(int recipientId, int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		return friendshipRequestRepository.findByRecipientId(recipientId, pageable);
	}

	@Override
	public FriendshipRequest findById(int id) {
		return friendshipRequestRepository.findById(id).get();
	}

	@Override
	public FriendshipRequest findBySenderIdAndRecipientId(int senderId, int recipientId) {
		return friendshipRequestRepository.findBySenderIdAndRecipientId(senderId, recipientId);
	}

	@Override
	public FriendshipRequest findByIdAndRecipientId(int id, int recipientId)
			throws MismatchedFriendshipRequestAndRecipientId {
		FriendshipRequest existingFriendshipRequest = friendshipRequestRepository.findById(id).orElseThrow();
		if (existingFriendshipRequest.getRecipientId() == recipientId)
			return existingFriendshipRequest;
		else
			throw new MismatchedFriendshipRequestAndRecipientId(
					"requested friendshipRequest doesn't match with the recipientId");
	}

	@Override
	public void requestFriendship(int senderId, int recipientId) throws UnacceptableFriendshipRequestException,
			EllementAlreadyExistsException, FriendshipAlreadyExistsException {
		if (blockService.isAnyBlocked(senderId, recipientId))
			throw new UnacceptableFriendshipRequestException(
					"cannot send a friendship request because one of the two users is blocked by the other.");

		if (friendshipRequestRepository.findBySenderIdAndRecipientId(senderId, recipientId) != null)
			throw new EllementAlreadyExistsException("A friendship request already exists.");

		if (friendshipService.findByUserIdAndFriendId(senderId, recipientId) != null)
			throw new FriendshipAlreadyExistsException(
					"cannot create a friendship request because a friendship already exists between the two users.");

		friendshipRequestRepository.save(new FriendshipRequest(senderId, recipientId, LocalDateTime.now()));
	}

	@Override
	public void approve(int id) {
		FriendshipRequest existingFriendshipRequest = friendshipRequestRepository.findById(id).orElseThrow();
		friendshipService.save(existingFriendshipRequest.getSenderId(), existingFriendshipRequest.getRecipientId());
		deleteById(id);
	}

	@Override
	public void reject(int id) {
		FriendshipRequest existingFriendshipRequest = friendshipRequestRepository.findById(id).orElseThrow();
		friendshipRequestRepository.save(existingFriendshipRequest);
		deleteById(id);
	}

	public void deleteById(int id) {
		if (friendshipRequestRepository.findById(id).isPresent())
			friendshipRequestRepository.deleteById(id);
		else
			throw new NoSuchElementException("cannot delete a non existing FriendshipRequest");
	}

	@Override
	public void deleteBySenderIdAndRecipientId(int senderId, int recipientId) {
		friendshipRequestRepository.deleteBySenderIdAndRecipientId(senderId, recipientId);
	}
}
