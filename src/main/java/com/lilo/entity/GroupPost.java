package com.lilo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "group_post")
@Getter
@Setter
@NoArgsConstructor
public class GroupPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "content")
	private String content;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "group_id")
	private int groupId;

	@Column(name = "picture")
	private String picture;

	@Column(name = "timestamp")
	private LocalDateTime timestamp;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
	private List<GroupComment> comments;

	public GroupPost(String content, int userId, int groupId) {
		this.content = content;
		this.userId = userId;
		this.groupId = groupId;
	}

}
