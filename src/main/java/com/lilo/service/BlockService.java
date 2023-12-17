package com.lilo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.Block;
import com.lilo.exception.IllegalOperationException;

public interface BlockService {

	public List<Block> findAllByUserId(int userId);

	Page<Block> findAllByUserId(int userId, int pageNumber, int pageSize, Sort sort);

	public Block findById(int id);

	public Block findByIdAndUserId(int id, int userId);

	public Block findByUserIdAndBlockedUserId(int userId, int blockedUserId);

	@Transactional
	public void save(int userId, int blockedUserId) throws IllegalOperationException;

	@Transactional
	public void deleteById(int id);

	public boolean isAnyBlocked(int userId, int otherUserId);

}
