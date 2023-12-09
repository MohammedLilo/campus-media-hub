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

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "request_timestamp")
	private LocalDateTime requestTimestamp;

	@Column(name = "decision_timestamp")
	private LocalDateTime decisionTimestamp;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private RequestStatus status;

	public GroupJoinRequest(int userId, int groupId, String firstName, String lastName) {
		this.userId = userId;
		this.groupId = groupId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "GroupJoinRequest [id=" + id + ", groupId=" + groupId + ", userId=" + userId + ", requestTimestamp="
				+ requestTimestamp + ", decisionTimestamp=" + decisionTimestamp + ", status=" + status + "]";
	}

}
