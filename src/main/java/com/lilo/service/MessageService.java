package com.lilo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.Message;

public interface MessageService {

	public List<Message> findAllByChatId(int chatId);

	Page<Message> findAllByChatId(int chatId, int pageNumber, int pageSize, Sort sort);

//
	public Message findById(int messageId);

	@Transactional
	public void save(Message message);

	@Transactional
	public void update(Message message);

	@Transactional
	public void deleteById(int messageId);

	@Transactional
	public void delete(Message message);
}
