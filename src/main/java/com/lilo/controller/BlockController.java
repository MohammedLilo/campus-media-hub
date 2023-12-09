package com.lilo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lilo.dto.BlocksDTO;
import com.lilo.entity.Block;
import com.lilo.service.BlockService;

@RestController
@RequestMapping("/api")
public class BlockController {
	private final BlockService blockService;

	public BlockController(BlockService blockService) {
		this.blockService = blockService;
	}

	@GetMapping("/users/{userId}/blocks")
	BlocksDTO getBlocks(@PathVariable("userId") int userId,
			@RequestParam(name = "page", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Page<Block> page = blockService.findAllByUserId(userId, pageNumber, pageSize,
				Sort.by(Order.desc("blockTimestamp")));
		BlocksDTO blocks = new BlocksDTO(page.getContent(), page.isLast());
		return blocks;
	}

	@GetMapping("/users/{userId}/blocks/{id}")
	Block getBlock(@PathVariable("userId") int userId, @PathVariable("id") int id) {
		Block requestedBlock = blockService.findByIdAndUserId(id, userId);
		return requestedBlock;
	}

	@PostMapping("/users/{userId}/blocks")
	void createBlock(@PathVariable("userId") int userId, @RequestParam("blockedUserId") int blockedUserId) {
		blockService.save(userId, blockedUserId);
	}

	@DeleteMapping("/users/{userId}/blocks/{id}")
	void deleteBlock(@PathVariable("userId") int userId, @PathVariable("id") int id) {
		blockService.deleteById(id);
	}

}
