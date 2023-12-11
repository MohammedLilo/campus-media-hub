package com.lilo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lilo.entity.FriendshipRequest;
import com.lilo.enums.RequestStatus;

public interface FriendshipRequestRepository extends JpaRepository<FriendshipRequest, Integer> {

	List<FriendshipRequest> findBySenderIdAndStatus(int senderId, RequestStatus status);

	List<FriendshipRequest> findByRecipientIdAndStatus(int recipientId, RequestStatus status);

	Page<FriendshipRequest> findBySenderIdAndStatus(int senderId, RequestStatus status, Pageable pageable);

	Page<FriendshipRequest> findByRecipientIdAndStatus(int recipientId, RequestStatus status, Pageable pageable);

	FriendshipRequest findBySenderIdAndRecipientId(int senderId, int recipientId);

}
