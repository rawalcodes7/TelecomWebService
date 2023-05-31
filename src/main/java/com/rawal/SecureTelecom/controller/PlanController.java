package com.rawal.SecureTelecom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rawal.SecureTelecom.dto.PlanDTO;
import com.rawal.SecureTelecom.service.PlanService;

@RestController
@CrossOrigin
public class PlanController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PlanService planService;

	// Fetches all plan details
	@GetMapping(value = "/plans")
	public List<PlanDTO> getAllPlans() {
		return planService.getAllPlans();
	}


}
