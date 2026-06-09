package com.example.trip_share.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trip_share.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
