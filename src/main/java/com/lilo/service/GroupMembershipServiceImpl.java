package com.lilo.service;

import java.time.LocalDateTime;
import java.util.List;

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
	private UserService userService;
	private GroupPromotionService groupPromotionService;

	public GroupMembershipServiceImpl(GroupMembershipRepository groupMembershipRepository,
			@Lazy KickedGroupMemberService kickedGroupMemberService, @Lazy UserService userService,
			@Lazy GroupPromotionService groupPromotionService) {
		this.groupMembershipRepository = groupMembershipRepository;
		this.kickedGroupMemberService = kickedGroupMemberService;
		this.userService = userService;
		this.groupPromotionService = groupPromotionService;
	}

	@Override
	public List<GroupMembership> findAllByGroupId(int groupId) {
		List<GroupMembership> groupMemberships = groupMembershipRepository.findByGroupId(groupId);

		return groupMemberships;
	}

	@Override
	public Page<GroupMembership> findAllByGroupId(int groupId, int pageNo, int size, Sort sort) {
		Pageable pageable = PageRequest.of(pageNo, size, sort);
		Page<GroupMembership> page = groupMembershipRepository.findByGroupId(groupId, pageable);

		return page;
	}

	@Override
	public GroupMembership findById(int groupMembershipId) {
		GroupMembership groupMembership = groupMembershipRepository.findById(groupMembershipId).get();

		return groupMembership;
	}

	@Override
	public GroupMembership findByUserIdAndGroupId(int userId, int groupId) {
		GroupMembership groupMembership = groupMembershipRepository.findByUserIdAndGroupId(userId, groupId);

		return groupMembership;
	}

//	@Override
//	public void save(GroupMembership groupMembership) {
//		groupMembership.setMembershipTimestamp(LocalDateTime.now());
//		groupMembership.setRole(GroupRoles.MEMBER);
//
//		groupMembershipRepository.save(groupMembership);
//	}
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

//	@Override
//	public void promote(GroupMembership groupMembership, GroupRoles groupRoles) {
//		groupMembership.setRole(groupRoles);
//		groupMembershipRepository.save(groupMembership);
//
//	}

	@Override
	public void promote(int id, int promoterId, GroupRoles newRole) {
		GroupMembership existingMembership = groupMembershipRepository.findById(id).get();

		GroupPromotion groupPromotion = new GroupPromotion(existingMembership.getGroupId(), promoterId,
				existingMembership.getUserId(), newRole);

		existingMembership.setRole(newRole);
		groupMembershipRepository.save(existingMembership);
		groupPromotionService.save(groupPromotion);
	}

	@Override
	public void kick(GroupMembership groupMembership) {
		kickedGroupMemberService.save(new KickedGroupMember(groupMembership.getUserId(), groupMembership.getGroupId()));

		groupMembershipRepository.delete(groupMembership);
	}

	@Override
	public void kick(int id) {
		GroupMembership existingMembership = groupMembershipRepository.findById(id).get();
		kickedGroupMemberService
				.save(new KickedGroupMember(existingMembership.getUserId(), existingMembership.getGroupId()));
		groupMembershipRepository.deleteById(id);
	}

	@Override
	public void leave(GroupMembership groupMembership) {
		groupMembershipRepository.delete(groupMembership);
	}

	@Override
	public void leave(int id) {
		groupMembershipRepository.deleteById(id);
		;
	}

}
