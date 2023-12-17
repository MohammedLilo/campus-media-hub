package com.lilo.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.Block;
import com.lilo.exception.IllegalOperationException;
import com.lilo.repository.BlockRepository;

@Service
public class BlockServiceImpl implements BlockService {

	private BlockRepository blockRepository;
	private FriendshipService friendshipService;
	private final FriendshipRequestService friendshipRequestService;

	public BlockServiceImpl(BlockRepository blockRepository, @Lazy FriendshipService friendshipService,
			FriendshipRequestService friendshipRequestService) {
		this.blockRepository = blockRepository;
		this.friendshipService = friendshipService;
		this.friendshipRequestService = friendshipRequestService;
	}

	@Override
	public List<Block> findAllByUserId(int userId) {
		return blockRepository.findByUserId(userId);
	}

	@Override
	public Page<Block> findAllByUserId(int userId, int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		return blockRepository.findByUserId(userId, pageable);
	}

	@Override
	public Block findById(int id) {
		return blockRepository.findById(id).get();
	}

	@Override
	public Block findByIdAndUserId(int id, int userId) {
		Block existingBlock = blockRepository.findById(id).orElseThrow();
		if (existingBlock.getUserId() == userId)
			return existingBlock;
		else
			throw new IllegalArgumentException("requested blockId and userId must be related");
	}

	@Override
	public Block findByUserIdAndBlockedUserId(int userId, int blockedUserId) {
		return blockRepository.findByUserIdAndBlockedUserId(userId, blockedUserId);
	}

	@Override
	public void save(int userId, int blockedUserId) throws IllegalOperationException {
		if (findByUserIdAndBlockedUserId(userId, blockedUserId) != null)
			throw new IllegalOperationException("user is already blocked");
		Block block = new Block(userId, blockedUserId, LocalDateTime.now());
		
		friendshipService.deleteByUserIdAndFriendId(userId, blockedUserId);
		friendshipRequestService.deleteBySenderIdAndRecipientId(userId, blockedUserId);
		friendshipRequestService.deleteBySenderIdAndRecipientId(blockedUserId, userId);

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
