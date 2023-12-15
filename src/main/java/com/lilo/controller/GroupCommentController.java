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
import com.lilo.dto.GroupCommentsDTO;
import com.lilo.entity.GroupComment;
import com.lilo.fileUtilities.FileUtility;
import com.lilo.service.GroupCommentService;

@RestController
@RequestMapping("/api")
public class GroupCommentController {

	private final GroupCommentService groupCommentService;

	public GroupCommentController(GroupCommentService groupCommentService) {
		this.groupCommentService = groupCommentService;
	}

	@GetMapping("/groups/{groupId}/group-posts/{postId}/group-comments")
	public GroupCommentsDTO getGroupComments(@PathVariable("groupId") int groupId, @PathVariable("postId") int postId,
			@RequestParam(name = "page", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Page<GroupComment> page = groupCommentService.findAllByPostId(postId, pageNumber, pageSize,
				Sort.by(Order.desc("timestamp")));
		return new GroupCommentsDTO(page.getContent(), page.isLast());
	}

	@GetMapping("/groups/{groupId}/group-posts/{postId}/group-comments/{id}")
	public GroupComment getGroupComment(@PathVariable("id") int id, @PathVariable("postId") int postId) {
		return groupCommentService.findByIdAndPostId(id, postId);
	}

	@PostMapping("/groups/{groupId}/group-posts/{postId}/group-comments")
	public void createGroupComment(@PathVariable("postId") int postId,
			@ModelAttribute("groupComment") GroupComment groupComment,
			@ModelAttribute(name = "imageFile") MultipartFile imageFile) {
		GroupComment savedGroupComment = groupCommentService.save(groupComment);
		if (imageFile != null && !imageFile.isEmpty()) {
			updateGroupComment(savedGroupComment.getId(), postId, savedGroupComment, imageFile);
		}
	}

	@PutMapping("/groups/{groupId}/group-posts/{postId}/group-comments/{id}")
	public void updateGroupComment(@PathVariable("id") int id, @PathVariable("postId") int postId,
			GroupComment groupComment, MultipartFile imageFile) {
		groupComment.setTimestamp(groupCommentService.findByIdAndPostId(id, postId).getTimestamp());
		String filePath = ImagesPaths.GROUP_COMMENT_PICTURE_FOLDER + "/" + id;

		if (imageFile != null && !imageFile.isEmpty()) {
			groupComment.setPicture(filePath);
			try {
				FileUtility.saveImage(imageFile, filePath);
			} catch (IOException e) {
				e.printStackTrace();
				FileUtility.deleteImageFiles(filePath);
			}
		} else {
			FileUtility.deleteImageFiles(filePath);
		}
		groupCommentService.update(id, postId, groupComment);
	}

	@DeleteMapping("/groups/{groupId}/group-posts/{postId}/group-comments/{id}")
	public void deleteGroupComment(@PathVariable("id") int id) {
		groupCommentService.deleteById(id);
		FileUtility.deleteImageFiles(ImagesPaths.GROUP_COMMENT_PICTURE_FOLDER + "/" + id);
	}
}
