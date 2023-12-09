package com.lilo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lilo.dto.ChatsDTO;
import com.lilo.entity.Chat;
import com.lilo.service.ChatService;

@RestController
@RequestMapping("/api")
public class ChatController {

	private final ChatService chatService;

	public ChatController(ChatService chatService) {
		this.chatService = chatService;
	}

	@GetMapping("/users/{userId}/chats")
	ChatsDTO getChats(@PathVariable("userId") int userId,
			@RequestParam(name = "page", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "15") int pageSize) {
		Page<Chat> page = chatService.findByUserId(userId, pageNumber, pageSize,
				Sort.by(Order.desc("updateTimestamp")));
		ChatsDTO chats = new ChatsDTO(page.getContent(), page.isLast());
		return chats;
	}

	@GetMapping("/users/{userId}/chats/{id}")
	Chat getChat(@PathVariable("id") int id) {
		Chat requestedChat = chatService.findById(id);
		return requestedChat;
	}

	@PostMapping("/users/{senderId}/chats")
	void createChat(@PathVariable("senderId") int senderId, @RequestParam("recipientId") int recipientId) {
		chatService.save(new Chat(senderId, recipientId));
	}

	@PutMapping("/users/{userId}/chats/{id}")
	void updateChat(@PathVariable("id") int id) {
		chatService.updateTimestampById(id);
	}

	@DeleteMapping("/users/{userId}/chats/{id}")
	void deleteChat(@PathVariable("id") int id) {
		chatService.deleteById(id);
	}
}
