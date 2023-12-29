package com.lilo.service;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.document.ChatMessage;
import com.lilo.repository.ChatMessageRepository;

@Service
public class ChatMessageSeviceImpl implements ChatMessageService {

	private final ChatMessageRepository chatMessageRepository;
	private final ChatService chatService;

	public ChatMessageSeviceImpl(ChatMessageRepository chatMessageRepository, @Lazy ChatService chatService) {
		this.chatMessageRepository = chatMessageRepository;
		this.chatService = chatService;
	}

	@Override
	public Page<ChatMessage> FindAllByChatId(ObjectId chatId, int pageNumber, int pageSize, Sort sort) {
		return chatMessageRepository.findAllByChatId(chatId, PageRequest.of(pageNumber, pageSize, sort));
	}

	@Override
	public ChatMessage findById(ObjectId id) {
		return chatMessageRepository.findById(id).orElseThrow();
	}

	@Override
	public void save(ChatMessage chatMessage) {
		LocalDateTime now = LocalDateTime.now();
		ObjectId chatId = chatService
				.findBySenderIdAndRecipientId(chatMessage.getSenderId(), chatMessage.getRecipientId()).getId();
		ObjectId reverseChatId = chatService
				.findBySenderIdAndRecipientId(chatMessage.getRecipientId(), chatMessage.getSenderId()).getId();

		chatMessage.setChatId(chatId);
		chatMessage.setTimestamp(now);
		chatMessageRepository.save(chatMessage);

		ChatMessage ChatMessageForReverseChat = new ChatMessage(reverseChatId, chatMessage.getSenderId(),
				chatMessage.getRecipientId(), chatMessage.getContent(), now);
		chatMessageRepository.save(ChatMessageForReverseChat);
	}
//
//	/**
//	 * Used for deleting a message from a chat, this method doesn't delete the
//	 * message from the reverse chat.
//	 */
//	@Override
//	public void deleteById(ObjectId id) {
//		ChatMessage chatMessage = chatMessageRepository.findById(id).orElseThrow();
//		chatMessageRepository.delete(chatMessage);
//	}
//
//	/**
//	 * Used for deleting a message from a chat, this method doesn't delete the
//	 * message from the reverse chat.
//	 */
//	@Override
//	public void delete(ChatMessage chatMessage) {
//		chatMessageRepository.delete(chatMessage);
//	}
//
//	/**
//	 * Used for deleting all messages of a chat based on {@code chatId}, it doesn't
//	 * delete messages from the reverse chat.
//	 */
//	@Override
//	public void deleteAllByChatId(ObjectId chatId) {
//		chatMessageRepository.deleteAllByChatId(chatId);
//	}

}
