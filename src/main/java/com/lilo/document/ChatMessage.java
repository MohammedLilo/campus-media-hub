package com.lilo.document;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("chat_message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
	@Id
	@Field("_id")
	private ObjectId id;

	@Field("chat_id")
	private ObjectId chatId;

	@Field("sender_id")
	private int senderId;

	@Field("recipient_id")
	private int recipientId;

	@Field("content")
	private String content;

	@Field("timestamp")
	private LocalDateTime timestamp;

	public ChatMessage(ObjectId chatId, int senderId, int recipientId, String content) {
		this.chatId = chatId;
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.content = content;
	}

	public ChatMessage(ObjectId chatId, int senderId, int recipientId, String content, LocalDateTime timestamp) {
		this.chatId = chatId;
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.content = content;
		this.timestamp = timestamp;
	}

}
