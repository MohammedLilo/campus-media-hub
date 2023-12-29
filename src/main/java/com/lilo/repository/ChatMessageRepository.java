package com.lilo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.lilo.document.ChatMessage;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, ObjectId> {

	Page<ChatMessage> findAllByChatId(ObjectId chatId, Pageable pageable);

	@Query(value = " {'chatId' : ?0} ", delete = true)
	void deleteAllByChatId(ObjectId chatId);
}
