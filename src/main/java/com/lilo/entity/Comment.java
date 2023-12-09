package com.lilo.entity;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "content")
	private String content;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "post_id")
	private int postId;

	@Column(name = "timestamp")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;

//	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
//	@JoinColumn(name = "post_id")
//	private Post post;

	public Comment(String content, int userId, int postId) {
		this.content = content;
		this.userId = userId;
		this.postId = postId;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", userId=" + userId + ", postId=" + postId
				+ ", commentTimestamp=" + timestamp + "]";
	}
}
