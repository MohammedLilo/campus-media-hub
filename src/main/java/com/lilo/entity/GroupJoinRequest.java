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
@Table(name = "group_join_request")
@Getter
@Setter
@NoArgsConstructor
public class GroupJoinRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "group_id")
	private int groupId;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "timestamp")
	private LocalDateTime timestamp;

	public GroupJoinRequest(int userId, int groupId, LocalDateTime timestamp) {
		this.userId = userId;
		this.groupId = groupId;
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "GroupJoinRequest [id=" + id + ", groupId=" + groupId + ", userId=" + userId + ", requestTimestamp="
				+ timestamp + ", decisionTimestamp=";
	}

}
