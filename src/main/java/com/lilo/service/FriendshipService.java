package com.lilo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.Friendship;

public interface FriendshipService {

	public List<Friendship> findAllByUserId(int userId);

	Page<Friendship> findAllByUserId(int userId, int pageNumber, int pageSize, Sort sort);

	public Friendship findById(int id);

	public Friendship findByIdAndUserId(int id, int userId);

	public Friendship findByUserIdAndFriendId(int userId, int friendId);

	@Transactional
	public void save(int userId, int friendId);

	@Transactional
	public void deleteById(int id);

	@Transactional
	public void deleteByUserIdAndFriendId(int userId, int friendId);

}
