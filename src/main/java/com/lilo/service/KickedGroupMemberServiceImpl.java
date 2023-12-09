package com.lilo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.KickedGroupMember;
import com.lilo.repository.KickedGroupMemberRepository;

@Service
public class KickedGroupMemberServiceImpl implements KickedGroupMemberService {
	private KickedGroupMemberRepository kickedGroupMemberRepository;

	public KickedGroupMemberServiceImpl(KickedGroupMemberRepository kickedGroupMemberRepository) {
		this.kickedGroupMemberRepository = kickedGroupMemberRepository;
	}

	@Override
	public List<KickedGroupMember> findAllByGroupId(int groupId) {
		List<KickedGroupMember> kickedGroupMembers = kickedGroupMemberRepository.findByGroupId(groupId);
		return kickedGroupMembers;
	}

	@Override
	public Page<KickedGroupMember> findAllByGroupId(int groupId, int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<KickedGroupMember> page = kickedGroupMemberRepository.findByGroupId(groupId, pageable);

		return page;
	}

	@Override
	public KickedGroupMember findById(int kickedGroupMemberId) {
		KickedGroupMember kickedGroupMember = kickedGroupMemberRepository.findById(kickedGroupMemberId).get();
		return kickedGroupMember;
	}

	@Override
	public void save(KickedGroupMember kickedGroupMember) {
		kickedGroupMemberRepository.save(kickedGroupMember);
	}

}
