package com.lilo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lilo.entity.Chat;
import com.lilo.repository.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService {

	private ChatRepository chatRepository;

	public ChatServiceImpl(ChatRepository chatRepository) {
		this.chatRepository = chatRepository;
	}

	@Override
	public List<Chat> findAllByUserId(int userId) {
		List<Chat> chats = chatRepository.findAllByUserId(userId);

		return chats;
	}

	@Override
	public Page<Chat> findByUserId(int userId, int pageNumber, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Chat> page = chatRepository.findAllByUserId(userId, pageable);

		return page;
	}

	@Override
	public Chat findById(int id) {
		Chat chat = chatRepository.findById(id).get();
		return chat;
	}

	@Override
	public void save(Chat chat) {
		chatRepository.save(chat);
	}

	@Override
	public void updateTimestamp(Chat chat) {
		chat.setUpdateTimestamp(LocalDateTime.now());
		chatRepository.save(chat);
	}

	@Override
	public void updateTimestampById(int id) {
		Chat existingChat = chatRepository.findById(id).get();
		if (existingChat != null) {
			existingChat.setUpdateTimestamp(LocalDateTime.now());
			chatRepository.save(existingChat);
		} else
			throw new NoSuchElementException("couldn't update a nonexisting element ^_^");

	}

	@Override
	public void deleteById(int id) {
		chatRepository.deleteById(id);
	}

	@Override
	public void delete(Chat chat) {
		chatRepository.delete(chat);
	}

}
