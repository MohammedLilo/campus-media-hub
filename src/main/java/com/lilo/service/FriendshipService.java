package com.lilo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.Friendship;

public interface FriendshipService {

	Page<Friendship> findAllByUserId(int userId, int pageNumber, int pageSize, Sort sort);

	public Friendship findById(int id);

	Friendship findByUserIdAndFriendId(int userId, int friendId);

	@Transactional
	public void save(int userId, int friendId);

	@Transactional
	public void deleteById(int id);

	@Transactional
	public void deleteByUserIdAndFriendId(int userId, int friendId);


}
