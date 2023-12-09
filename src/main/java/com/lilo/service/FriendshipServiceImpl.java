package com.lilo.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.context.annotation.Lazy;
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
	private final BlockService blockService;

	public FriendshipServiceImpl(FriendshipRepository friendshipRepository, @Lazy BlockService blockService) {
		this.friendshipRepository = friendshipRepository;
		this.blockService = blockService;
	}

	@Override
	public Page<Friendship> findAllByUserId(int userId, int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Friendship> page = friendshipRepository.findByUserId(userId, pageable);
		return page;
	}

	@Override
	public List<Friendship> findAllByUserId(int userId) {
		List<Friendship> friendships = friendshipRepository.findByUserId(userId);
		return friendships;
	}

	@Override
	public Friendship findById(int id) {
		Friendship friendship = friendshipRepository.findById(id).get();
		return friendship;
	}

	@Override
	public Friendship findByIdAndUserId(int id, int userId) {
		Friendship existingFriendship = friendshipRepository.findById(id).get();
		if (existingFriendship != null)
			if (existingFriendship.getUserId() == userId)
				return existingFriendship;
			else
				throw new IllegalArgumentException(
						"the requested friendship's userId is not the same as the argument userId");
		else
			throw new NoSuchElementException("element doesn't exist");
	}

	@Override
	public Friendship findByUserIdAndFriendId(int userId, int friendId) {
		Friendship friendship = friendshipRepository.findByUserIdAndFriendId(userId, friendId);
		return friendship;
	}

	@Override
	public void save(int userId, int friendId) {
		boolean isAnyBlocked = blockService.isAnyBlocked(userId, friendId);

		if (!isAnyBlocked) {
			Friendship friendship = new Friendship(userId, friendId);
			Friendship existingFriendship = friendshipRepository.findByUserIdAndFriendId(friendship.getUserId(),
					friendship.getFriendId());

			if (existingFriendship == null) {
				Friendship reverseFriendship = new Friendship(friendship.getFriendId(), friendship.getUserId());

				friendshipRepository.save(friendship);
				friendshipRepository.save(reverseFriendship);
			} else {
				throw new RuntimeException("friendship already exists");
			}
		} else
			throw new RuntimeException("one user has blocked the other");
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
