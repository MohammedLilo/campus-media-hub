package com.lilo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.lilo.entity.Chat;

public interface ChatService {

	public List<Chat> findAllByUserId(int userId);

	Page<Chat> findByUserId(int userId, int pageNumber, int pageSize, Sort sort);

	public Chat findById(int id);

	@Transactional
	public void save(Chat chat);

	@Transactional
	void updateTimestamp(Chat chat);

	@Transactional
	public void updateTimestampById(int id);

	@Transactional
	public void deleteById(int id);

	@Transactional
	public void delete(Chat chat);
}
