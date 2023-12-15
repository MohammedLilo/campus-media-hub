package com.lilo.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.Comment;
import com.lilo.repository.CommentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;

	public CommentServiceImpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	@Override
	public Page<Comment> findAllByUserId(int userId, int pageNumber, int pageSize, Sort sort) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Comment> page = commentRepository.findByUserId(userId, pageable);

		return page;
	}

	@Override
	public Page<Comment> findAllByPostId(int postId, int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Comment> page = commentRepository.findByPostId(postId, pageable);

		return page;
	}

	@Override
	public Comment findByIdAndPostId(int id, int postId) {
		Comment existingComment = commentRepository.findById(id).get();
		if (existingComment != null)
			if (existingComment.getPostId() == postId)
				return existingComment;
			else
				throw new IllegalArgumentException("request comment should be associated with the specified post");
		else
			throw new EntityNotFoundException("cannot update a nonexisting comment");
	}

	@Override
	public Comment save(Comment comment) {
		comment.setTimestamp(LocalDateTime.now());
		return commentRepository.save(comment);
	}

	@Override
	public void update(int id, int postId, Comment comment) {
		Comment existingComment = commentRepository.findById(id).get();

		if (existingComment != null)
			if (existingComment.getPostId() == postId) {
				comment.setTimestamp(existingComment.getTimestamp());
				commentRepository.save(comment);
			} else
				throw new IllegalArgumentException("request comment should be associated with the specified post");
		else
			throw new EntityNotFoundException("cannot update a nonexisting comment");

	}

	@Override
	public void deleteById(int id) {
		Optional<Comment> existingComment = commentRepository.findById(id);
		if (existingComment.isPresent())
			commentRepository.deleteById(id);
		else
			throw new NoSuchElementException("cannot delete a nonexisting comment");
	}
}
