package com.lilo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lilo.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {

	List<Group> findByNameIgnoreCase(String groupName);

	Page<Group> findAllByName(String name, Pageable pageable);

}
