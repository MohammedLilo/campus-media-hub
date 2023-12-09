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
@Table(name = "chat")
@Getter
@Setter
@NoArgsConstructor
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "sender_id")
	private int senderId;

	@Column(name = "recipient_id")
	private int recipientId;
	@Column(name = "update_timestamp")
	private LocalDateTime updateTimestamp;

//	@OneToMany
//	@JoinColumn(name = "chat_id")
//	private List<Message> messages;

	public Chat(int senderId, int recipientId) {
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.updateTimestamp = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "Chat [id=" + id + ", senderId=" + senderId + ", recipientId=" + recipientId + ", updateTimestamp="
				+ updateTimestamp + "]";
	}

}
