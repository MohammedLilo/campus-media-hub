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

import com.lilo.dto.MessagesDTO;
import com.lilo.entity.Message;
import com.lilo.service.MessageService;

@RestController
@RequestMapping("/api")
public class MessageController {
	private final MessageService messageService;

	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@GetMapping("users/{userId}/chats/{chatId}/messages")
	MessagesDTO getMessages(@PathVariable("chatId") int chatId,
			@RequestParam(name = "page", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "15") int pageSize) {
		Page<Message> page = messageService.findAllByChatId(chatId, pageNumber, pageSize,
				Sort.by(Order.desc("messageTimestamp")));
		MessagesDTO messages = new MessagesDTO(page.getContent(), page.isLast());
		return messages;
	}

	@PostMapping("users/{senderId}/chats/{chatId}/messages")
	void createMessage(@PathVariable("senderId") int senderId, @PathVariable("chatId") int chatId,
			@ModelAttribute("content") String content, @RequestParam("recipientId") int recipientId) {
		messageService.save(new Message(content, chatId, senderId, recipientId));
	}

	@PutMapping("users/{senderId}/chats/{chatId}/messages/{id}")
	void updateMessage(@PathVariable("id") int id, @ModelAttribute("content") String content) {
		Message requestedMessage = messageService.findById(id);
		requestedMessage.setContent(content);

		messageService.update(requestedMessage);
	}

	@DeleteMapping("users/{senderId}/chats/{chatId}/messages/{id}")
	void deleteMessage(@PathVariable("id") int id) {
		messageService.deleteById(id);
	}

}
