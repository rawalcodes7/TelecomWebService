package com.rawal.SecureTelecom.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rawal.SecureTelecom.entity.CallDetails;

public interface CallDetailsRepository extends JpaRepository<CallDetails, Long> {

	List<CallDetails> findByCalledBy(long calledBy);
}
