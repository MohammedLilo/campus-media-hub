package com.lilo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lilo.entity.GroupComment;

public interface GroupCommentRepository extends JpaRepository<GroupComment, Integer>{

	Page<GroupComment> findByPostId(int postId, Pageable pageable);

	Page<GroupComment> findByUserId(int userId, Pageable pageable);

}
