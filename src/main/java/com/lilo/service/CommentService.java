package com.lilo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.Comment;

public interface CommentService {

	public List<Comment> findAllByUserId(int userId);

	public List<Comment> findAllByPostId(int postId);

	Page<Comment> findAllByUserId(int userId, int pageNumber, int pageSize, Sort sort);

	Page<Comment> findAllByPostId(int postId, int pageNumber, int pageSize, Sort sort);

	public Comment findByIdAndPostId(int id, int postId);

	@Transactional
	public void save(Comment comment);

	@Transactional
	public void update(int id, int postId, Comment comment);

	@Transactional
	public void deleteById(int id);

	@Transactional
	void delete(Comment comment);

}
