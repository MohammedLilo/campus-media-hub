package com.lilo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lilo.document.Chat;

@Repository
public interface ChatRepository extends MongoRepository<Chat, ObjectId> {

	Page<Chat> findAllBySenderId(int senderId, Pageable pageable);

	Chat findBySenderIdAndRecipientId(int senderId, int recipientId);
}
