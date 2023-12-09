package com.lilo.entity;

import java.time.LocalDateTime;

import com.lilo.enums.RequestStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

	@Column(name = "request_timestamp")
	private LocalDateTime requestTimestamp;

	@Column(name = "decision_timestamp")
	private LocalDateTime decisionTimestamp;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private RequestStatus status;

	public FriendshipRequest(int senderId, int recipientId) {
		this.senderId = senderId;
		this.recipientId = recipientId;

		this.requestTimestamp = LocalDateTime.now();
		this.status = RequestStatus.PENDING;
	}

	@Override
	public String toString() {
		return "FriendshipRequest [id=" + id + ", senderId=" + senderId + ", recepientId=" + recipientId
				+ ", requestTimestamp=" + requestTimestamp + ", decisionTimestamp=" + decisionTimestamp + ", status="
				+ status + "]";
	}

}
