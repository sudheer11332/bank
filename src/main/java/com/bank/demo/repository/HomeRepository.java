package com.bank.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.demo.model.User;

@Repository
@Transactional
public interface HomeRepository extends JpaRepository<User, Integer> {

	@Query("SELECT e.balance FROM User e WHERE e.userName = :uname ")
	int findBalanceByUserName(@Param("uname") String uname);
	
	@Modifying
	@Query("UPDATE User u SET u.balance = :balance where u.userName= :uname ")
	void save(@Param("balance")int balance, @Param("uname") String uname);
}
