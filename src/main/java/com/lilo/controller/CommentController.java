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

import com.lilo.dto.CommentsDTO;
import com.lilo.entity.Comment;
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
				Sort.by(Order.desc("commentTimestamp")));
		CommentsDTO comments = new CommentsDTO(page.getContent(), page.isLast());
		return comments;
	}

	@GetMapping("/posts/{postId}/comments/{id}")
	Comment getComment(@PathVariable("postId") int postId, @PathVariable("id") int id) {
		Comment requestedComment = commentService.findByIdAndPostId(id, postId);
		return requestedComment;
	}

	@PostMapping("/posts/{postId}/comments")
	void createComment(@ModelAttribute Comment comment) {
		commentService.save(comment);
	}

	@PutMapping("/posts/{postId}/comments/{id}")
	void updateComment(@PathVariable("postId") int postId, @PathVariable("id") int id,
			@ModelAttribute Comment comment) {
		commentService.update(id, postId, comment);
	}

	@DeleteMapping("/posts/{postId}/comments/{id}")
	void deleteComment(@PathVariable("id") int id) {
		commentService.deleteById(id);
	}
}
