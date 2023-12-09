package com.lilo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lilo.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	public List<Post> findByUserId(int userId);

	public Page<Post> findAllByUserId(int userId, Pageable pageable);

}
