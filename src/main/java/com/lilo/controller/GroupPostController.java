package com.lilo.controller;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lilo.constants.ImagesPaths;
import com.lilo.dto.GroupPostsDTO;
import com.lilo.entity.GroupPost;
import com.lilo.fileUtilities.FileUtility;
import com.lilo.service.GroupPostService;

@RestController
@RequestMapping("/api")
public class GroupPostController {
	private final GroupPostService groupPostService;

	public GroupPostController(GroupPostService groupPostService) {
		this.groupPostService = groupPostService;
	}

	@GetMapping("/groups/{groupId}/group-posts")
	GroupPostsDTO getGroupPosts(@PathVariable("groupId") int groupId,
			@RequestParam(name = "page", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Page<GroupPost> page = groupPostService.findAllByGroupId(groupId, pageNumber, pageSize,
				Sort.by(Order.desc("timestamp")));
		return new GroupPostsDTO(page.getContent(), page.isLast());
	}

	@GetMapping("/groups/{groupId}/group-posts/{id}")
	GroupPost getGroupPost(@PathVariable("id") int id) {
		return groupPostService.findById(id);
	}

	@PostMapping("/groups/{groupId}/group-posts")
	void createPost(@ModelAttribute("groupPost") GroupPost groupPost,
			@ModelAttribute("imageFile") MultipartFile imageFile) {
		GroupPost savedGroupPost = groupPostService.save(groupPost);
		if (imageFile != null && !imageFile.isEmpty()) {
			updateGroupPost(savedGroupPost.getId(), savedGroupPost, imageFile);
		}
	}

	@PutMapping("/groups/{groupId}/group-posts/{id}")
	void updateGroupPost(@PathVariable("id") int id, @ModelAttribute("groupPost") GroupPost groupPost,
			MultipartFile imageFile) {
		String filePath = ImagesPaths.GROUP_POST_PICTURE_FOLDER + "\\" + id;
		groupPost.setTimestamp(groupPostService.findById(id).getTimestamp());

		if (imageFile != null && !imageFile.isEmpty()) {
			groupPost.setPicture(filePath);
			try {
				FileUtility.saveImage(imageFile, filePath);
			} catch (IOException e) {
				e.printStackTrace();
				FileUtility.deleteImageFiles(filePath);
			}
		} else {
			FileUtility.deleteImageFiles(filePath);
		}
		groupPostService.update(id, groupPost);

	}

	@DeleteMapping("/groups/{groupId}/group-posts/{id}")
	void deleteGroupPost(@PathVariable("id") int id) {
		groupPostService.deleteById(id);
		FileUtility.deleteImageFiles(ImagesPaths.GROUP_POST_PICTURE_FOLDER + "/" + id);
	}

}
