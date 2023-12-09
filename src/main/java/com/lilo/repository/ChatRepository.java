package com.lilo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lilo.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

	@Query("SELECT c FROM Chat c WHERE c.senderId= :userId Or c.recipientId= :userId")
	List<Chat> findAllByUserId(@Param("userId") int userId);

	@Query("SELECT c FROM Chat c WHERE c.senderId= :userId Or c.recipientId= :userId")
	Page<Chat> findAllByUserId(int userId, Pageable pageable);

//	@Query("SELECT c FROM Chat c LEFT JOIN FETCH c.messages WHERE c.id= :id")
//	 Chat findByIdWithMessages(@Param("id") int id);

}
