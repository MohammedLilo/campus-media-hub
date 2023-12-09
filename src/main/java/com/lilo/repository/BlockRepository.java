package com.lilo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lilo.entity.Block;

public interface BlockRepository extends JpaRepository<Block, Integer> {

	List<Block> findByUserId(int userId);

	Page<Block> findByUserId(int userId, Pageable pageable);

	Block findByUserIdAndBlockedUserId(int userId, int blockedUserId);

	boolean existsByUserIdAndBlockedUserId(int userId, int blockedUserId);
}
