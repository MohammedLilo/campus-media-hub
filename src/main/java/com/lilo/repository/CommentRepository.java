package com.lilo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lilo.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	List<Comment> findByUserId(int userId);

	List<Comment> findByPostId(int postId);

	Page<Comment> findByUserId(int userId, Pageable pageable);

	Page<Comment> findByPostId(int postId, Pageable pageable);

}
