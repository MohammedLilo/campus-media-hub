package com.lilo.controller;

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
import com.lilo.dto.CommentsDTO;
import com.lilo.entity.Comment;
import com.lilo.fileUtilities.FileUtility;
import com.lilo.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping("/posts/{postId}/comments")
	CommentsDTO getComments(@PathVariable("postId") int postId,
			@RequestParam(name = "page", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Page<Comment> page = commentService.findAllByPostId(postId, pageNumber, pageSize,
				Sort.by(Order.desc("timestamp")));
		return new CommentsDTO(page.getContent(), page.isLast());
	}

	@GetMapping("/posts/{postId}/comments/{id}")
	Comment getComment(@PathVariable("postId") int postId, @PathVariable("id") int id) {
		return commentService.findByIdAndPostId(id, postId);
	}

	@PostMapping("/posts/{postId}/comments")
	void createComment(@PathVariable("postId") int postId, @ModelAttribute(name = "comment") Comment comment,
			@ModelAttribute(name = "imageFile") MultipartFile imageFile) {
		Comment savedComment = commentService.save(comment);
		updateComment(postId, savedComment.getId(), savedComment, imageFile);

	}

	@PutMapping("/posts/{postId}/comments/{id}")
	void updateComment(@PathVariable("postId") int postId, @PathVariable("id") int id,
			@ModelAttribute(name = "comment") Comment comment,
			@ModelAttribute(name = "imageFile") MultipartFile imageFile) {
		comment.setTimestamp(commentService.findByIdAndPostId(id, postId).getTimestamp());

		String filePath = ImagesPaths.COMMENT_PICTURE_FOLDER + "/" + comment.getId();
		comment.setPicture(filePath);

		commentService.update(id, postId, comment);
		FileUtility.saveImage(imageFile, filePath);
	}

	@DeleteMapping("/posts/{postId}/comments/{id}")
	void deleteComment(@PathVariable("id") int id) {
		commentService.deleteById(id);
	}
}
