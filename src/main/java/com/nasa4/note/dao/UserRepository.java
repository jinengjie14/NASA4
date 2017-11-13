package com.nasa4.note.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nasa4.note.domain.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {
	
	public User findByAccountAndPassword(String account, String password);
	
	public User findByAccount(String account);

}
