package com.lilo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lilo.entity.FriendshipRequest;

public interface FriendshipRequestRepository extends JpaRepository<FriendshipRequest, Integer> {

	Page<FriendshipRequest> findBySenderId(int senderId, Pageable pageable);

	Page<FriendshipRequest> findByRecipientId(int recipientId, Pageable pageable);

	FriendshipRequest findBySenderIdAndRecipientId(int senderId, int recipientId);

}
