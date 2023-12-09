package com.lilo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.Group;
import com.lilo.enums.GroupPrivacyLevels;
import com.lilo.repository.GroupRepository;

@Service
public class GroupServiceImpl implements GroupService {

	private GroupRepository groupRepository;

	public GroupServiceImpl(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public Page<Group> findAll(int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Group> page = groupRepository.findAll(pageable);

		return page;
	}

	@Override
	public List<Group> findByName(String groupName) {
		List<Group> groups = groupRepository.findByNameIgnoreCase(groupName);
		return groups;
	}

	@Override
	public Page<Group> findByName(String groupName, int pageNumber, int pageSize, Sort sort) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Group> page = groupRepository.findAllByName(groupName, pageable);

		return page;
	}

	@Override
	public Group findById(int groupId) {
		Group group = groupRepository.findById(groupId).get();
		return group;
	}

	@Override
	public Group save(Group group) {
		group.setCreationTimestamp(LocalDateTime.now());
		return groupRepository.save(group);
	}

	@Override
	public void update(int id, Group group) {
		Optional<Group> existingGroup = groupRepository.findById(id);

		if (existingGroup.isPresent())
			groupRepository.save(group);
	}

	@Override
	public void updateName(int groupId, String newName) {
		Group group = groupRepository.findById(groupId).get();

		group.setName(newName);

		groupRepository.save(group);
	}

	@Override
	public void updateDescription(int groupId, String newDescription) {
		Group group = groupRepository.findById(groupId).get();

		group.setDescription(newDescription);

		groupRepository.save(group);
	}

	@Override
	public void updatePrivacyLevel(int groupId, GroupPrivacyLevels newPrivacyLevel) {
		Group group = groupRepository.findById(groupId).get();

		group.setPrivacyLevel(newPrivacyLevel);

		groupRepository.save(group);
	}

	@Override
	public void deleteById(int groupId) {
		groupRepository.deleteById(groupId);
	}

}
