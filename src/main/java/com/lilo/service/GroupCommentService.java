package com.lilo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.GroupComment;

public interface GroupCommentService {

	Page<GroupComment> findAllByUserId(int userId, int pageNumber, int pageSize, Sort sort);

	Page<GroupComment> findAllByPostId(int postId, int pageNumber, int pageSize, Sort sort);

	public GroupComment findByIdAndPostId(int id, int postId);

	@Transactional
	GroupComment save(GroupComment groupComment);

	@Transactional
	public void update(int id, int postId, GroupComment comment);

	@Transactional
	void deleteById(int groupCommentId);

}
