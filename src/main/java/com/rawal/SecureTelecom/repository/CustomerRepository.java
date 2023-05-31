package com.rawal.SecureTelecom.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rawal.SecureTelecom.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	
}
