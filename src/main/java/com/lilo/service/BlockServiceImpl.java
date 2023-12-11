package com.lilo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.Block;
import com.lilo.repository.BlockRepository;

@Service
public class BlockServiceImpl implements BlockService {

	private BlockRepository blockRepository;
	private FriendshipService friendshipService;

	public BlockServiceImpl(BlockRepository blockRepository, @Lazy FriendshipService friendshipService) {
		this.blockRepository = blockRepository;
		this.friendshipService = friendshipService;
	}

	@Override
	public List<Block> findAllByUserId(int userId) {
		List<Block> blocks = blockRepository.findByUserId(userId);
		return blocks;
	}

	@Override
	public Page<Block> findAllByUserId(int userId, int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Block> page = blockRepository.findByUserId(userId, pageable);
		return page;
	}

	@Override
	public Block findById(int id) {
		Block block = blockRepository.findById(id).get();
		return block;
	}

	@Override
	public Block findByIdAndUserId(int id, int userId) {
		Block existingBlock = blockRepository.findById(id).get();
		if (existingBlock != null) {
			if (existingBlock.getUserId() == userId)
				return existingBlock;
			else
				throw new IllegalArgumentException("requested blockId and userId must be related");
		} else
			throw new NoSuchElementException("element not found");
	}

	@Override
	public Block findByUserIdAndBlockedUserId(int userId, int blockedUserId) {
		Block existingBlock = blockRepository.findByUserIdAndBlockedUserId(userId, blockedUserId);

		return existingBlock;
	}

	@Override
	public void save(int userId, int blockedUserId) {
		Block block = new Block(userId, blockedUserId);
//		block.setTimestamp(LocalDateTime.now());

		friendshipService.deleteByUserIdAndFriendId(block.getUserId(), block.getBlockedUserId());
		blockRepository.save(block);

	}

	@Override
	public void deleteById(int id) {
		blockRepository.deleteById(id);
	}

	@Override
	public boolean isAnyBlocked(int userId, int otherUserId) {
		boolean status = blockRepository.existsByUserIdAndBlockedUserId(userId, otherUserId)
				|| blockRepository.existsByUserIdAndBlockedUserId(otherUserId, userId);
		return status;
	}

}
