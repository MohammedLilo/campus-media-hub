package com.lilo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.Post;

public interface PostService {

//	@Transactional
//	public void save(Post post, int userId);

	@Transactional
	Post save(Post post);

	Page<Post> findAll(int pageNumber, int pageSize, Sort sort);

	public List<Post> findAllByUserId(int userId);

	Page<Post> findAllByUserId(int userId, int pageNumber, int pageSize, Sort sort);

	public Post findById(int id);

	@Transactional
	public void update(int id, Post post);

	@Transactional
	public void deleteById(int id);

}
