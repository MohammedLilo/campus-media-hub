package com.lilo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.Group;
import com.lilo.enums.GroupPrivacyLevels;

public interface GroupService {

	public List<Group> findByName(String groupName);

	Page<Group> findAll(int pageNumber, int pageSize, Sort sort);

	Page<Group> findByName(String groupName, int pageNumber, int pageSize, Sort sort);

	public Group findById(int groupId);

	@Transactional
	public Group save(Group group);

	@Transactional
	void update(int id, Group group);

	@Transactional
	public void updateName(int groupId, String newName);

	@Transactional
	void updateDescription(int groupId, String newDescription);

	@Transactional
	void updatePrivacyLevel(int groupId, GroupPrivacyLevels newPrivacyLevel);

	@Transactional
	public void deleteById(int groupId);
}
