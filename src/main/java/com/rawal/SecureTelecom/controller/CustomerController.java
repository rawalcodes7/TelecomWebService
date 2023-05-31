package com.rawal.SecureTelecom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rawal.SecureTelecom.dto.CallDetailsDTO;
import com.rawal.SecureTelecom.dto.CustomerDTO;
import com.rawal.SecureTelecom.dto.FriendFamilyDTO;
import com.rawal.SecureTelecom.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerService custService;

	// Create a new customer

	@PostMapping(value = "/customers")
	public void createCustomer(@RequestBody CustomerDTO custDTO) {
		logger.info("Creation request for customer {}", custDTO);
		custService.createCustomer(custDTO);
	}

	// Fetches full profile of a specific customer
	@GetMapping(value = "/customers/{phoneNo}")
	public CustomerDTO getCustomerProfile(@PathVariable Long phoneNo) {

		logger.info("Profile request for customer {}", phoneNo);
		return custService.getCustomerProfile(phoneNo);
	}

	// Fetches call details of a specific customer

	@GetMapping(value = "/customers/{phoneNo}/calldetails")
	public List<CallDetailsDTO> getCustomerCallDetails(@PathVariable long phoneNo) {
		logger.info("Calldetails request for customer {}", phoneNo);
		return custService.getCustomerCallDetails(phoneNo);
	}

	// Save the friend details of a specific customer

	@PostMapping(value = "/customers/{phoneNo}/friends")
	public void saveFriend(@PathVariable Long phoneNo, @RequestBody FriendFamilyDTO friendDTO) {
		logger.info("Creation request for customer {} with data {}", phoneNo, friendDTO);
		custService.saveFriend(phoneNo, friendDTO);
	}
	
	 //Fetches all customers
    @GetMapping(value="/customers")
    public List<CustomerDTO> getAllCustomers(){
        return custService.getAllCustomers();
    }

}
