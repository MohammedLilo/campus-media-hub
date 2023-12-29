package com.lilo.service;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.document.Chat;
import com.lilo.repository.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService {

	private final ChatRepository chatRepository;

	public ChatServiceImpl(ChatRepository chatRepository) {
		this.chatRepository = chatRepository;
	}

	@Override
	public Page<Chat> findAllByUserId(int userId, int pageNumber, int pageSize, Sort sort) {
		return chatRepository.findAllBySenderId(userId, PageRequest.of(pageNumber, pageSize, sort));
	}

	@Override
	public Chat findById(ObjectId id) {
		return chatRepository.findById(id).orElse(null);
	}

	@Override
	public Chat findBySenderIdAndRecipientId(int senderId, int recipientId) {
		return chatRepository.findBySenderIdAndRecipientId(senderId, recipientId);
	}

	@Override
	public void save(Chat chat) {
		chat.setId(new ObjectId());
		chatRepository.save(chat);

		Chat reverseChat = new Chat(new ObjectId(), chat.getRecipientId(), chat.getSenderId());
		chatRepository.save(reverseChat);
	}

//	@Override
//	public void deleteById(ObjectId id) {
//		Chat chat = chatRepository.findById(id).orElseThrow();
//		delete(chat);
//	}
//
//	@Override
//	public void delete(Chat chat) {
//		deleteAssociatedMessages(chat);
//		chatRepository.delete(chat);
//		chatRepository.delete(chatRepository.findBySenderIdAndRecipientId(chat.getRecipientId(), chat.getSenderId()));
//	}
//
//	/**
//	 * Deletes all messages associated with this {@code chat}, It doesn't delete
//	 * messages from the reverse chat.
//	 */
//	private void deleteAssociatedMessages(Chat chat) {
//		chatMessageService.deleteAllByChatId(chat.getId());
//	}

}
