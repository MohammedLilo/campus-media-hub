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
@Table(name = "group_comment")
@Getter
@Setter
@NoArgsConstructor
public class GroupComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "content")
	private String content;

	@Column(name = "post_id")
	private int postId;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "group_id")
	private int groupId;

	@Column(name = "comment_timestamp")
	private LocalDateTime commentTimestamp;

	public GroupComment(String content, int userId, int postId, int groupId) {
		super();
		this.content = content;
		this.postId = postId;
		this.userId = userId;
		this.groupId = groupId;
		this.commentTimestamp = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "GroupComment [id=" + id + ", content=" + content + ", postId=" + postId + ", userId=" + userId
				+ ", groupId=" + groupId + ", commentTimestamp=" + commentTimestamp + "]";
	}

}
