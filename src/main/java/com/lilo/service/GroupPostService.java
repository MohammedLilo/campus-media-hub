package com.lilo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.GroupPost;

public interface GroupPostService {

	Page<GroupPost> findAllByGroupId(int groupId, int pageNumber, int pageSize, Sort sort);

	GroupPost findById(int groupPostId);

	@Transactional
	GroupPost save(GroupPost groupPost);

//	@Transactional
//	GroupPost update(GroupPost groupPost);

	@Transactional
	GroupPost update(int id, GroupPost groupPost);

	@Transactional
	void deleteById(int groupPostId);

}
