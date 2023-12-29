package com.lilo.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "chat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
	@Id
	@Field("_id")
	private ObjectId id;

	@Field("sender_id")
	private int senderId;

	@Field("recipient_id")
	private int recipientId;

	public Chat(int senderId, int recipientId) {
		this.senderId = senderId;
		this.recipientId = recipientId;
	}

	@Override
	public String toString() {
		return "Chat [id=" + id + ", senderId=" + senderId + ", recipientId=" + recipientId + "]";
	}

}
