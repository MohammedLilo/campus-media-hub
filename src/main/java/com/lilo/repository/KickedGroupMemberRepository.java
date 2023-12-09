package com.lilo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lilo.entity.KickedGroupMember;

public interface KickedGroupMemberRepository extends JpaRepository<KickedGroupMember, Integer> {

	Page<KickedGroupMember> findByGroupId(int groupId, Pageable pageable);

	List<KickedGroupMember> findByGroupId(int groupId);

}
