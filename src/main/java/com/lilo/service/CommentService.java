package com.lilo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.Comment;

public interface CommentService {

	Page<Comment> findAllByUserId(int userId, int pageNumber, int pageSize, Sort sort);

	Page<Comment> findAllByPostId(int postId, int pageNumber, int pageSize, Sort sort);

	public Comment findByIdAndPostId(int id, int postId);

	@Transactional
	public Comment save(Comment comment);

	@Transactional
	public void update(int id, int postId, Comment comment);

	@Transactional
	public void deleteById(int id);
}
