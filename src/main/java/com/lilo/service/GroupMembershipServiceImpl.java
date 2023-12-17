package com.lilo.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.GroupMembership;
import com.lilo.entity.GroupPromotion;
import com.lilo.entity.KickedGroupMember;
import com.lilo.enums.GroupRoles;
import com.lilo.repository.GroupMembershipRepository;

@Service
public class GroupMembershipServiceImpl implements GroupMembershipService {
	private GroupMembershipRepository groupMembershipRepository;
	private KickedGroupMemberService kickedGroupMemberService;
	private GroupPromotionService groupPromotionService;

	public GroupMembershipServiceImpl(GroupMembershipRepository groupMembershipRepository,
			@Lazy KickedGroupMemberService kickedGroupMemberService,
			@Lazy GroupPromotionService groupPromotionService) {
		this.groupMembershipRepository = groupMembershipRepository;
		this.kickedGroupMemberService = kickedGroupMemberService;
		this.groupPromotionService = groupPromotionService;
	}

	@Override
	public Page<GroupMembership> findAllByGroupId(int groupId, int pageNo, int size, Sort sort) {
		Pageable pageable = PageRequest.of(pageNo, size, sort);
		return groupMembershipRepository.findByGroupId(groupId, pageable);
	}

	@Override
	public GroupMembership findById(int groupMembershipId) {
		return groupMembershipRepository.findById(groupMembershipId).get();
	}

	@Override
	public GroupMembership findByUserIdAndGroupId(int userId, int groupId) {
		return groupMembershipRepository.findByUserIdAndGroupId(userId, groupId);
	}

	@Override
	public boolean existsByUserIdAndGroupId(int userId, int groupId) {
		return groupMembershipRepository.existsByUserIdAndGroupId(userId, groupId);
	}

	@Override
	public void save(int userId, int groupId) {
		GroupMembership membership = new GroupMembership(userId, groupId);
		membership.setTimestamp(LocalDateTime.now());
		membership.setRole(GroupRoles.MEMBER);
		groupMembershipRepository.save(membership);
	}

	@Override
	public void update(GroupMembership groupMembership) {
		groupMembershipRepository.save(groupMembership);
	}

	@Override
	public void promote(int id, int promoterId, GroupRoles newRole) {
		GroupMembership existingMembership = groupMembershipRepository.findById(id).get();

		GroupPromotion groupPromotion = new GroupPromotion(existingMembership.getGroupId(), promoterId,
				existingMembership.getUserId(), newRole, LocalDateTime.now());

		existingMembership.setRole(newRole);
		groupMembershipRepository.save(existingMembership);
		groupPromotionService.save(groupPromotion);
	}

	@Override
	public void kick(int id) {
		GroupMembership existingMembership = groupMembershipRepository.findById(id).orElseThrow();
		kickedGroupMemberService.save(new KickedGroupMember(existingMembership.getUserId(),
				existingMembership.getGroupId(), LocalDateTime.now()));
		groupMembershipRepository.deleteById(id);
	}

	@Override
	public void leave(int id) {
		if (!groupMembershipRepository.existsById(id))
			throw new NoSuchElementException("cannot delete a non existing group membership.");
		groupMembershipRepository.deleteById(id);
	}

}
