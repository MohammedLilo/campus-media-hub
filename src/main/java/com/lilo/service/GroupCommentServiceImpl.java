package com.lilo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.GroupComment;
import com.lilo.repository.GroupCommentRepository;

@Service
public class GroupCommentServiceImpl implements GroupCommentService {

	private GroupCommentRepository groupCommentRepository;

	public GroupCommentServiceImpl(GroupCommentRepository groupCommentRepository) {
		this.groupCommentRepository = groupCommentRepository;
	}

	@Override
	public Page<GroupComment> findAllByGroupPostId(int groupPostId, int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<GroupComment> page = groupCommentRepository.findByPostId(groupPostId, pageable);

		return page;
	}

	@Override
	public GroupComment findById(int GroupCommentId) {
		GroupComment groupComment = groupCommentRepository.findById(GroupCommentId).get();
		return groupComment;
	}

	@Override
	public void save(GroupComment groupComment) {
		groupCommentRepository.save(groupComment);
	}

	@Override
	public void updateContent(GroupComment groupComment, String newContent) {
		groupComment.setContent(newContent);
		groupCommentRepository.save(groupComment);
	}

	@Override
	public void deleteById(int groupCommentId) {
		groupCommentRepository.deleteById(groupCommentId);
	}

	@Override
	public void delete(GroupComment groupComment) {
		groupCommentRepository.delete(groupComment);
	}

}
