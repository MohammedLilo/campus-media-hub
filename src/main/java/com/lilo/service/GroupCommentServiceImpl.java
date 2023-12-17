package com.lilo.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.GroupComment;
import com.lilo.repository.GroupCommentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GroupCommentServiceImpl implements GroupCommentService {

	private GroupCommentRepository groupCommentRepository;

	public GroupCommentServiceImpl(GroupCommentRepository groupCommentRepository) {
		this.groupCommentRepository = groupCommentRepository;
	}

	@Override
	public Page<GroupComment> findAllByPostId(int postId, int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		return groupCommentRepository.findByPostId(postId, pageable);
	}

	@Override
	public Page<GroupComment> findAllByUserId(int userId, int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		return groupCommentRepository.findByUserId(userId, pageable);
	}

	@Override
	public GroupComment findByIdAndPostId(int id, int postId) {
		GroupComment existingComment = groupCommentRepository.findById(id).get();
		if (existingComment != null)
			if (existingComment.getPostId() == postId)
				return existingComment;
			else
				throw new IllegalArgumentException("requested comment should be associated with the specified post");
		else
			throw new EntityNotFoundException("cannot update a nonexisting comment");
	}

	@Override
	public GroupComment save(GroupComment groupComment) {
		groupComment.setTimestamp(LocalDateTime.now());
		return groupCommentRepository.save(groupComment);
	}

	@Override
	public void update(int id, int postId, GroupComment comment) {
		GroupComment existingComment = groupCommentRepository.findById(id).get();

		if (existingComment != null)
			if (existingComment.getPostId() == postId) {
				comment.setTimestamp(existingComment.getTimestamp());
				groupCommentRepository.save(comment);
			} else
				throw new IllegalArgumentException("requested comment should be associated with the specified post");
		else
			throw new EntityNotFoundException("cannot update a nonexisting comment");

	}

	@Override
	public void deleteById(int id) {
		groupCommentRepository.deleteById(id);
	}

}
