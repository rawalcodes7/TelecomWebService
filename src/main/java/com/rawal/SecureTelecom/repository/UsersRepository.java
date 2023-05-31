package com.rawal.SecureTelecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rawal.SecureTelecom.entity.Users;

public interface UsersRepository extends JpaRepository<Users, String> {

}
