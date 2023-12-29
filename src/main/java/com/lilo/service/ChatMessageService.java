package com.lilo.service;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.document.ChatMessage;

public interface ChatMessageService {

	Page<ChatMessage> FindAllByChatId(ObjectId chatId, int pageNumber, int pageSize, Sort sort);

	ChatMessage findById(ObjectId id);

	@Transactional
	void save(ChatMessage chatMessage);

//	/**
//	 * Used for deleting a message from a chat, it doesn't delete the message from
//	 * the reverse chat.
//	 */
//	@Transactional
//	void deleteById(ObjectId id);
//
//	/**
//	 * Used for deleting a message from a chat, it doesn't delete the message from
//	 * the reverse chat.
//	 */
//	@Transactional
//	void delete(ChatMessage chatMessage);
//
//	/**
//	 * Used for deleting all messages of a chat based on the {@code chatId} , it
//	 * doesn't delete messages from the reverse chat.
//	 */
//	@Transactional
//	public void deleteAllByChatId(ObjectId chatId);

}
