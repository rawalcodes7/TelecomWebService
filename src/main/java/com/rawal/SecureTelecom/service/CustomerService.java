package com.rawal.SecureTelecom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.rawal.SecureTelecom.dto.CallDetailsDTO;
import com.rawal.SecureTelecom.dto.CustomerDTO;
import com.rawal.SecureTelecom.dto.FriendFamilyDTO;
import com.rawal.SecureTelecom.entity.CallDetails;
import com.rawal.SecureTelecom.entity.Customer;
import com.rawal.SecureTelecom.entity.FriendFamily;
import com.rawal.SecureTelecom.entity.Plan;
import com.rawal.SecureTelecom.entity.UserRoles;
import com.rawal.SecureTelecom.entity.Users;
import com.rawal.SecureTelecom.repository.CallDetailsRepository;
import com.rawal.SecureTelecom.repository.CustomerRepository;
import com.rawal.SecureTelecom.repository.UserRolesRepository;
import com.rawal.SecureTelecom.repository.UsersRepository;

@Service
public class CustomerService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerRepository custRepo;

	@Autowired
	UserRolesRepository userRolesRepo;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Autowired
	CallDetailsRepository callDetailsRepo;

	@Autowired
	UsersRepository userRepo;

	// Create a new customer
	public void createCustomer(CustomerDTO custDTO) {
		Users user = new Users();
		UserRoles userRole = new UserRoles();
		logger.info("Creation request for customer {}", custDTO);
		Customer cust = custDTO.createEntity();
		user.setUsername(custDTO.getName());
		Plan p = new Plan();
		p.setPlanId(custDTO.getCurrentPlan().getPlanId());
		user.setPassword(encoder.encode(custDTO.getPassword()));
		userRole.setRole("ROLE_CUSTOMER");
		userRole.setUsername(user.getUsername());
		cust.setPlan(p);
		userRepo.save(user);
		userRolesRepo.save(userRole);
		custRepo.save(cust);

	}

	// Fetches full profile of a specific customer
	public CustomerDTO getCustomerProfile(Long phoneNo) {
		CustomerDTO custDTO = null;
		logger.info("Profile request for customer {}", phoneNo);
		Optional<Customer> cust = custRepo.findById(phoneNo);
		if (cust.isPresent())
			custDTO = CustomerDTO.valueOf(cust.get());

		logger.info("Profile for customer : {}", custDTO);
		return custDTO;
	}

	// Fetches call details of a specific customer
	public List<CallDetailsDTO> getCustomerCallDetails(@PathVariable long phoneNo) {

		logger.info("Calldetails request for customer {}", phoneNo);

		List<CallDetails> callDetails = callDetailsRepo.findByCalledBy(phoneNo);
		List<CallDetailsDTO> callsDTO = new ArrayList<>();

		for (CallDetails call : callDetails) {
			callsDTO.add(CallDetailsDTO.valueOf(call));
		}
		logger.info("Calldetails for customer : {}", callDetails);

		return callsDTO;
	}

	// Save the friend details of a specific customer
	public void saveFriend(Long phoneNo, FriendFamilyDTO friendDTO) {
		logger.info("Creation request for customer {} with data {}", phoneNo, friendDTO);
		friendDTO.setPhoneNo(phoneNo);
		FriendFamily friend = friendDTO.createFriend();
		List<FriendFamily> friendList = new ArrayList<>();
		friendList.add(friend);

		Customer c = custRepo.getOne(phoneNo);
		c.getFriends().add(friend);
		c.setFriends(c.getFriends());
		custRepo.save(c);
	}
	
	public List<CustomerDTO> getAllCustomers() {
	       List<Customer> list= custRepo.findAll();
	       List<CustomerDTO> dtoList=new ArrayList<>();
	       for(Customer cust:list){
	           dtoList.add(CustomerDTO.valueOf(cust));
	       }
	       logger.info("All the customers : {}", dtoList);
	       return dtoList;
	    }

}
