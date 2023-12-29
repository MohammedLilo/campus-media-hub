package com.lilo.service;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.document.Chat;

public interface ChatService {

	Page<Chat> findAllByUserId(int userId, int pageNumber, int pageSize, Sort sort);

	Chat findById(ObjectId id);

	Chat findBySenderIdAndRecipientId(int senderId, int recipientId);

	@Transactional
	void save(Chat chat);

//	/**
//	 * Deletes the chat and its messages, It deletes Deletes the chat and the
//	 * reverse chat (with their messages).
//	 * 
//	 * @param chat
//	 */
//	@Transactional
//	void deleteById(ObjectId id);
//
//	/**
//	 * Deletes the chat and its messages, It deletes Deletes the chat and the
//	 * reverse chat (with their messages).
//	 * 
//	 * @param chat
//	 */
//	@Transactional
//	void delete(Chat chat);
}
