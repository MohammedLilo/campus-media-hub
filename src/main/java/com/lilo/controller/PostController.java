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
import com.lilo.dto.PostsDTO;
import com.lilo.entity.Post;
import com.lilo.fileUtilities.FileUtility;
import com.lilo.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/posts")
	PostsDTO findPosts(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Page<Post> page = postService.findAll(pageNumber, pageSize, Sort.by(Order.desc("timestamp")));
		return new PostsDTO(page.getContent(), page.isLast());
	}

	@GetMapping("/posts/{id}")
	Post getPost(@PathVariable("id") int id) {
		return postService.findById(id);
	}

	@PostMapping("/posts")
	void createPost(@ModelAttribute(name = "post") Post post,
			@ModelAttribute(name = "imageFile") MultipartFile imageFile) {
		Post savedPost = postService.save(post);
		updatePost(savedPost.getId(), savedPost, imageFile);
	}

	@PutMapping("/posts/{id}")
	void updatePost(@PathVariable("id") int id, @ModelAttribute(name = "post") Post post,
			@ModelAttribute(name = "imageFile") MultipartFile imageFile) {
		post.setTimestamp(postService.findById(id).getTimestamp());
		
		String filePath = ImagesPaths.POST_PICTURE_FOLDER + "/" + post.getId();
		post.setPicture(filePath);
		
		postService.update(id, post);
		FileUtility.saveImage(imageFile, filePath);
	}

	@DeleteMapping("/posts/{id}")
	void deletePost(@PathVariable("id") int id) {
		postService.deleteById(id);
		FileUtility.deleteImageFiles(ImagesPaths.POST_PICTURE_FOLDER + "\\" + id);
	}

}
