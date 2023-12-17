package com.lilo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.FriendshipRequest;
import com.lilo.exception.EllementAlreadyExistsException;
import com.lilo.exception.FriendshipAlreadyExistsException;
import com.lilo.exception.MismatchedFriendshipRequestAndRecipientId;
import com.lilo.exception.UnacceptableFriendshipRequestException;

public interface FriendshipRequestService {

	Page<FriendshipRequest> findBySenderId(int senderId, int pageNumber, int pageSize, Sort sort);

	Page<FriendshipRequest> findByRecipientId(int recipientId, int pageNumber, int pageSize, Sort sort);

	FriendshipRequest findById(int id);

	FriendshipRequest findByIdAndRecipientId(int id, int recipientId) throws MismatchedFriendshipRequestAndRecipientId;

	FriendshipRequest findBySenderIdAndRecipientId(int senderId, int recipientId);

	@Transactional
	void requestFriendship(int senderId, int recipientId) throws UnacceptableFriendshipRequestException,
			EllementAlreadyExistsException, FriendshipAlreadyExistsException;

	@Transactional
	void approve(int id);

	@Transactional
	void reject(int id);

	@Transactional
	void deleteById(int id);

	@Transactional
	void deleteBySenderIdAndRecipientId(int senderId, int recipientId);

}
