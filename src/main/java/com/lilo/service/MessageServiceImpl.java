package com.lilo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.Chat;
import com.lilo.entity.Message;
import com.lilo.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {
	private MessageRepository messageRepository;
	private ChatService chatService;

	public MessageServiceImpl(MessageRepository messageRepository, ChatService chatService) {
		this.messageRepository = messageRepository;
		this.chatService = chatService;
	}

	@Override
	public List<Message> findAllByChatId(int chatId) {
		List<Message> messages = messageRepository.findByChatId(chatId);

		return messages;
	}

	@Override
	public Page<Message> findAllByChatId(int chatId, int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Message> page = messageRepository.findByChatId(chatId, pageable);

		return page;
	}

	@Override
	public Message findById(int messageId) {
		Message message = messageRepository.findById(messageId).get();

		return message;
	}

	@Override
	public void save(Message message) {
		Chat chat = chatService.findById(message.getChatId());
		chatService.updateTimestamp(chat);

		messageRepository.save(message);

	}

	@Override
	public void update(Message message) {
		messageRepository.save(message);
	}

	@Override
	public void deleteById(int messageId) {
		messageRepository.deleteById(messageId);
	}

	@Override
	public void delete(Message message) {
		messageRepository.delete(message);
	}

}
