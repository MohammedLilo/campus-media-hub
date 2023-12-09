package com.lilo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lilo.entity.GroupJoinRequest;
import com.lilo.enums.RequestStatus;

public interface GroupJoinRequestRepository extends JpaRepository<GroupJoinRequest, Integer> {

	List<GroupJoinRequest> findByGroupId(int groupId);

	List<GroupJoinRequest> findByGroupIdAndStatus(int groupId, RequestStatus status);

	Page<GroupJoinRequest> findByGroupId(int groupId, Pageable pageable);

	Page<GroupJoinRequest> findByGroupIdAndStatus(int groupId, RequestStatus status, Pageable pageable);

	@Query("SELECT EXISTS(SELECT g from GroupJoinRequest g WHERE g.userId= :userId AND g.groupId= :groupId AND g.status= PENDING)")
	boolean existsByUserIdAndGroupIdPending(@Param("userId") int userId, @Param("groupId") int groupId);

}

