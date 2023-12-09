package com.lilo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lilo.entity.Friendship;

public interface FriendshipRepository extends JpaRepository<Friendship, Integer> {

//	@Modifying
//	@Query("DELETE FROM Friendship f WHERE f.userId= :userId AND f.friendId= :friendId")
//	public void deleteByUserIdAndFriendId(@Param("userId") int userId, @Param("friendId") int friendId);

	void deleteByUserIdAndFriendId(int userId, int friendId);

	public Friendship findByUserIdAndFriendId(int userId, int friendId);

	public List<Friendship> findByUserId(int userId);

	Page<Friendship> findByUserId(int userId, Pageable pageable);

}
