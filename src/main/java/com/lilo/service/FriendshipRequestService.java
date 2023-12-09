package com.lilo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.FriendshipRequest;

public interface FriendshipRequestService {

	Page<FriendshipRequest> findPendingBySenderId(int senderId, int pageNumber, int pageSize, Sort sort);

	Page<FriendshipRequest> findPendingByReceiverId(int receiverId, int pageNumber, int pageSize, Sort sort);

	FriendshipRequest findByIdAndReceiverId(int id, int receiverId);

	FriendshipRequest findById(int id);

	@Transactional
	void requestFriendship(int senderId, int receiverId);

	@Transactional
	void requestFriendship(FriendshipRequest friendshipRequest);

	@Transactional
	void approve(int id);

	@Transactional
	void reject(int id);

	List<FriendshipRequest> findAllBySenderId(int senderId);

	List<FriendshipRequest> findAllByReceiverId(int receiverId);

}
