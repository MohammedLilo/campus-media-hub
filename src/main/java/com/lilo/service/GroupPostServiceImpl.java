package com.lilo.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.GroupPost;
import com.lilo.repository.GroupPostRepository;

@Service
public class GroupPostServiceImpl implements GroupPostService {

	private GroupPostRepository groupPostRepository;

	public GroupPostServiceImpl(GroupPostRepository groupPostRepository) {
		this.groupPostRepository = groupPostRepository;
	}

	@Override
	public Page<GroupPost> findAllByGroupId(int groupId, int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<GroupPost> page = groupPostRepository.findByGroupId(groupId, pageable);

		return page;
	}

	@Override
	public GroupPost findById(int groupPostId) {
		GroupPost groupPost = groupPostRepository.findById(groupPostId).get();

		return groupPost;
	}

	@Override
	public GroupPost save(GroupPost groupPost) {
		groupPost.setTimestamp(LocalDateTime.now());
		return groupPostRepository.save(groupPost);
	}

//	@Override
//	public GroupPost update(GroupPost groupPost) {
//		return groupPostRepository.save(groupPost);
//	}

	@Override
	public GroupPost update(int id, GroupPost groupPost) {
		GroupPost existingGroupPost = groupPostRepository.findById(id).get();
		if (existingGroupPost != null) {
			groupPost.setTimestamp(existingGroupPost.getTimestamp());
			return groupPostRepository.save(groupPost);
		} else
			throw new NoSuchElementException("cannot update a nonexisting element");
	}

	@Override
	public void deleteById(int groupPostId) {
		groupPostRepository.deleteById(groupPostId);
	}

}
