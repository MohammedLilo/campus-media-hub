package com.lilo.controller;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lilo.document.Chat;
import com.lilo.document.ChatMessage;
import com.lilo.dto.ChatMessagesDTO;
import com.lilo.dto.ChatsDTO;
import com.lilo.service.ChatMessageService;
import com.lilo.service.ChatService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {
	private final ChatService chatService;
	private final ChatMessageService chatMessageService;
	private final SimpMessagingTemplate messagingTemplate;

	@GetMapping("/users/{userId}/chats")
	@ResponseBody
	public ChatsDTO getChats(@PathVariable("userId") int userId,
			@RequestParam(name = "page", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Page<Chat> page = chatService.findAllByUserId(userId, pageNumber, pageSize, Sort.unsorted());
		return new ChatsDTO(page.getContent(), page.isLast());
	}

	@GetMapping("/users/{userId}/chats/{chatId}")
	@ResponseBody
	public ChatMessagesDTO getChatMessages(@PathVariable("chatId") String chatId,
			@RequestParam(name = "page", defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		Page<ChatMessage> page = chatMessageService.FindAllByChatId(new ObjectId(chatId), pageNumber, pageSize,
				Sort.by(Order.desc("timestamp")));
		return new ChatMessagesDTO(page.getContent(), page.isLast());
	}

	@MessageMapping("/chats")
	@SendToUser("/queue/messages")
	public ChatMessage sendMessage(@Payload("message") ChatMessage message) {
		if (chatService.findById(message.getChatId()) == null)
			chatService.save(new Chat(message.getSenderId(), message.getRecipientId()));
		chatMessageService.save(message);
		messagingTemplate.convertAndSendToUser(String.valueOf(message.getRecipientId()), "/queue/messages", message);
		return message;
	}

}
