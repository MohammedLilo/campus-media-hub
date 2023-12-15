package com.lilo.controller;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
import com.lilo.dto.GroupsDTO;
import com.lilo.entity.Group;
import com.lilo.fileUtilities.FileUtility;
import com.lilo.service.GroupService;

@RestController
@RequestMapping("/api")
public class GroupController {

	private final GroupService groupService;

	public GroupController(GroupService groupService) {
		this.groupService = groupService;
	}

	@GetMapping("/groups")
	GroupsDTO getGroups(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Page<Group> page = groupService.findAll(pageNumber, pageSize, Sort.unsorted());
		return new GroupsDTO(page.getContent(), page.isLast());
	}

	@GetMapping("/groups/{id}")
	Group getGroup(@PathVariable("id") int id) {
		return groupService.findById(id);
	}

	@PostMapping("/groups")
	void createGroup(@ModelAttribute Group group, @ModelAttribute MultipartFile imageFile) {
		Group savedGroup = groupService.save(group);
		if (imageFile != null && !imageFile.isEmpty()) {
			updateGroup(savedGroup.getId(), savedGroup, imageFile);
		}
	}

	@PutMapping("/groups/{id}")
	void updateGroup(@PathVariable("id") int id, @ModelAttribute Group group, @ModelAttribute MultipartFile imageFile) {
		String filePath = ImagesPaths.GROUP_PICTURE_FORLDER + "\\" + id;
		group.setTimestamp(groupService.findById(id).getTimestamp());

		if (imageFile != null && !imageFile.isEmpty()) {
			group.setPicture(filePath);
			try {
				FileUtility.saveImage(imageFile, filePath);
			} catch (IOException e) {
				e.printStackTrace();
				FileUtility.deleteImageFiles(filePath);
			}
		} else {
			FileUtility.deleteImageFiles(filePath);
		}
		groupService.update(id, group);
	}

	@DeleteMapping("/groups/{id}")
	void deleteGroup(@PathVariable("id") int id) {
		groupService.deleteById(id);
		FileUtility.deleteImageFiles(ImagesPaths.GROUP_PICTURE_FORLDER + "/" + id);
	}
}
