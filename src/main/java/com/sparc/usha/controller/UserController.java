package com.sparc.usha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparc.usha.entity.Role;
import com.sparc.usha.repository.UserRepository;
import com.sparc.usha.response.JsonMessageResponse;
import com.sparc.usha.response.ResponseHandler;
import com.sparc.usha.response.UserInfoResponse;
import com.sparc.usha.response.UserInfoResponse.UserDetailsResponse;
import com.sparc.usha.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepo;

	/**
	 * @author Mohsina 05-11-2022 Get UserDetails By UserId
	 */
	@GetMapping("/getUserDetailByUserId/{userId}")
	private ResponseEntity<?> getUserDetailByUserId(@PathVariable Integer userId) {
		List<UserDetailsResponse> userDetailByUserId = userService.getUserDetailByUserId(userId);
		if (userDetailByUserId.isEmpty())
			return ResponseHandler.generateResponse("data not avaiable", HttpStatus.OK, userDetailByUserId);
		else
			return ResponseHandler.generateResponse("data retrived successfully!", HttpStatus.OK, userDetailByUserId);
	}

	/**
	 * @author Mohsina 06-11-2022 Get Role of All User
	 */
	@GetMapping("/getAllUserRole")
	private ResponseEntity<?> getAllUserRole() {
		List<Role> roleList = userService.getAllUserRole();
		if (roleList.isEmpty())
			return ResponseHandler.generateResponse("data not avaiable", HttpStatus.OK, roleList);
		else
			return ResponseHandler.generateResponse("data retrived successfully!", HttpStatus.OK, roleList);
	}

	/**
	 * @author Mohsina 07-11-2022 Update User Info by user Id
	 */
	@PutMapping("/updateUserByUserId")
	private ResponseEntity<?> updateUserByUserId(@RequestBody UserInfoResponse response) {
		String message = userService.updateUserByUserId(response);
		if (message == "success")
			return ResponseHandler.generateResponse("success", HttpStatus.OK, message);
		else
			return ResponseHandler.generateResponse("error", HttpStatus.OK, message);

	}

	/**
	 * @author Mohsina 07-11-2022 Get All User data By MunicipalityId
	 */
	@GetMapping("/getDataOfAllUserByMunicipalityId/{municipalityId}")
	private ResponseEntity<?> getDataOfAllUser(@PathVariable Integer municipalityId) {
		List<UserDetailsResponse> userList = userService.getDataOfAllUserByMunicipalityId(municipalityId);
		if (userList.isEmpty())
			return ResponseHandler.generateResponse("data not avaiable", HttpStatus.OK, userList);
		else
			return ResponseHandler.generateResponse("data retrived successfully!", HttpStatus.OK, userList);

	}

	/**
	 * @author Mohsina 09-11-2022 exists By MobileNo
	 */
	@GetMapping("/existsByMobileNo")
	private ResponseEntity<?> existsByMobileNo(@RequestParam String mobileNo) {
		if (userRepo.existsByMobileNo(mobileNo))
			return ResponseEntity.ok(new JsonMessageResponse("Mobile No. already exist."));
		else
			return ResponseEntity.ok(new JsonMessageResponse("success"));

	}

	/**
	 * @author Mohsina 09-11-2022 exists By EmailId
	 */
	@GetMapping("/existsByEmailId")

	private ResponseEntity<?> existsByEmail(@RequestParam String emailId) {
		if (userRepo.existsByEmail(emailId))
			return ResponseEntity.ok(new JsonMessageResponse("Email already exist."));
		else
			return ResponseEntity.ok(new JsonMessageResponse("success"));

	}
}
