package com.lilo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lilo.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

	List<Message> findByChatId(int chatId);

	Page<Message> findByChatId(int chatId, Pageable pageable);

}
