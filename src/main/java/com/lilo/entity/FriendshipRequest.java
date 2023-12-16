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
@Table(name = "Friendship_request")
@Getter
@Setter
@NoArgsConstructor
public class FriendshipRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "sender_id")
	private int senderId;

	@Column(name = "recipient_id")
	private int recipientId;

	@Column(name = "timestamp")
	private LocalDateTime timestamp;

	public FriendshipRequest(int senderId, int recipientId, LocalDateTime timestamp) {
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.timestamp = timestamp;
	}

}
