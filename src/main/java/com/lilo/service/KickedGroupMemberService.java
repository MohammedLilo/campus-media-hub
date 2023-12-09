package com.lilo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.KickedGroupMember;

public interface KickedGroupMemberService {

	List<KickedGroupMember> findAllByGroupId(int groupId);

	Page<KickedGroupMember> findAllByGroupId(int groupId, int pageNumber, int pageSize, Sort sort);

	KickedGroupMember findById(int kickedGroupMemberId);

	@Transactional
	void save(KickedGroupMember kickedGroupMember);
}
