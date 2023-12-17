package com.lilo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.Post;
import com.lilo.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;

	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public Page<Post> findAll(int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> page = postRepository.findAll(pageable);

		return page;
	}

	@Override
	public List<Post> findAllByUserId(int userId) {
		List<Post> posts = postRepository.findByUserId(userId);
		return posts;
	}

	@Override
	public Page<Post> findAllByUserId(int userId, int pageNumber, int pageSize, Sort sort) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> page = postRepository.findAllByUserId(userId, pageable);

		return page;
	}

	@Override
	public Post findById(int id) {
		Post post = postRepository.findById(id).get();
		return post;
	}

//	@Override
//	public void save(Post post, int userId) {
//		User user = userRepository.findById(userId).get();
//		post.setUser(user);
//		postRepository.save(post);
//	}

	@Override
	public Post save(Post post) {
		post.setTimestamp(LocalDateTime.now());
		return postRepository.save(post);
	}

	@Override
	public void update(int id, Post post) {
		Optional<Post> existingPost = postRepository.findById(id);
		if (existingPost.isPresent())
			postRepository.save(post);
		else
			throw new NoSuchElementException("cannot update a nonexisting element");
	}

	@Override
	public void deleteById(int id) {
		postRepository.deleteById(id);
	}

}
