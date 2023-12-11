package com.lilo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.FriendshipRequest;

public interface FriendshipRequestService {

	Page<FriendshipRequest> findPendingBySenderId(int senderId, int pageNumber, int pageSize, Sort sort);

	Page<FriendshipRequest> findPendingByRecipientId(int recipientId, int pageNumber, int pageSize, Sort sort);

	FriendshipRequest findByIdAndRecipientId(int id, int recipientId);

	FriendshipRequest findById(int id);

	@Transactional
	void requestFriendship(int senderId, int recipientId);

	@Transactional
	void requestFriendship(FriendshipRequest friendshipRequest);

	@Transactional
	void approve(int id);

	@Transactional
	void reject(int id);

	List<FriendshipRequest> findAllBySenderId(int senderId);

	List<FriendshipRequest> findAllByRecipientId(int recipientId);

}
