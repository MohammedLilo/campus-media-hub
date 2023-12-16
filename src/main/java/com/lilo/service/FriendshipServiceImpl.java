package com.lilo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.Friendship;
import com.lilo.repository.FriendshipRepository;

@Service
public class FriendshipServiceImpl implements FriendshipService {

	private final FriendshipRepository friendshipRepository;

	public FriendshipServiceImpl(FriendshipRepository friendshipRepository) {
		this.friendshipRepository = friendshipRepository;
	}

	@Override
	public Page<Friendship> findAllByUserId(int userId, int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		return friendshipRepository.findByUserId(userId, pageable);
	}

	@Override
	public Friendship findById(int id) {
		return friendshipRepository.findById(id).get();
	}

	@Override
	public Friendship findByUserIdAndFriendId(int userId, int friendId) {
		return friendshipRepository.findByUserIdAndFriendId(userId, friendId);
	}

	@Override
	public void save(int userId, int friendId) {
		Friendship friendship = new Friendship(userId, friendId);
		Friendship reverseFriendship = new Friendship(friendId, userId);
		friendshipRepository.save(friendship);
		friendshipRepository.save(reverseFriendship);
	}

	@Override
	public void deleteById(int id) {
		Friendship friendship = findById(id);
		friendshipRepository.deleteByUserIdAndFriendId(friendship.getUserId(), friendship.getFriendId());
		friendshipRepository.deleteByUserIdAndFriendId(friendship.getFriendId(), friendship.getUserId());
	}

	@Override
	public void deleteByUserIdAndFriendId(int userId, int friendId) {

		friendshipRepository.deleteByUserIdAndFriendId(userId, friendId);
		friendshipRepository.deleteByUserIdAndFriendId(friendId, userId);
	}

}
