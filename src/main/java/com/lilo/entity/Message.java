package com.lilo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "content")
	private String content;

	@Column(name = "timestamp")
	private LocalDateTime timestamp;

	@Column(name = "chat_id")
	private int chatId;

	@Column(name = "sender_id")
	private int senderId;

	@Column(name = "recipient_id")
	private int recipientId;

	public Message(String content, int chatId, int senderId, int recipientId) {
		this.content = content;
		this.timestamp = LocalDateTime.now();
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.chatId = chatId;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + ", messageTimestamp=" + timestamp + ", chatId="
				+ chatId + ", senderId=" + senderId + ", recipientId=" + recipientId + "]";
	}
}
