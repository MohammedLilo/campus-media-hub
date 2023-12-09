package com.lilo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.GroupComment;

public interface GroupCommentService {

	Page<GroupComment> findAllByGroupPostId(int groupPostId, int pageNumber, int pageSize, Sort sort);

	GroupComment findById(int GroupCommentId);

	@Transactional
	void save(GroupComment groupComment);

	@Transactional
	void updateContent(GroupComment groupComment, String newContent);

	@Transactional
	void deleteById(int groupCommentId);

	@Transactional
	void delete(GroupComment groupComment);
}
