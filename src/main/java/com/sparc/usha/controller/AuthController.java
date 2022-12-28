package com.sparc.usha.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparc.usha.Security.UserDetailsImpl;
import com.sparc.usha.Security.jwt.JwtUtils;
import com.sparc.usha.customException.EmailNotFoundException;
import com.sparc.usha.customException.UserNotFoundException;
import com.sparc.usha.entity.Role;
import com.sparc.usha.entity.UserEntity;
import com.sparc.usha.repository.MunicipalityRepository;
import com.sparc.usha.repository.RoleRepository;
import com.sparc.usha.repository.UserRepository;
import com.sparc.usha.request.ChangePasswordRequest;
import com.sparc.usha.request.LoginRequest;
import com.sparc.usha.request.SignupRequest;
import com.sparc.usha.response.JsonMessageResponse;
import com.sparc.usha.response.MessageResponse;
import com.sparc.usha.response.ResponseHandler;
import com.sparc.usha.response.UserInfoResponse;
import com.sparc.usha.utility.MyMailUtil;

/**
 * @author prasanjit
 *
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	MunicipalityRepository municipalityRepo;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	private MyMailUtil mailUtil;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		// UsernamePasswordAuthenticationToken userAuthObject=new
		// UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
		// loginRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getMobNo(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal(); // here i have done upcasting
		String genaratedjwtToken = jwtUtils.generateToken(userDetails);
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
		Integer id = userDetails.getRole().getId();
		return ResponseEntity.ok()
				.body(new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getMobileNo(),
						userDetails.getEmail(), userDetails.getMunicipalityID(), roles, id, genaratedjwtToken,
						"Bearer"));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByMobileNo(signUpRequest.getMobileNo())) {
			return ResponseHandler.generateResponse("Error: mobile no is already in use!!", HttpStatus.CONFLICT, null);
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseHandler.generateResponse("Error: Email is already in use!", HttpStatus.CONFLICT, null);
		}
		// Create new user's account
		UserEntity user = new UserEntity();
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));
		user.setName(signUpRequest.getName());
		user.setMobileNo(signUpRequest.getMobileNo());
		user.setAlternatePhoneNo(signUpRequest.getAlternatemobileNo());
		user.setMunicipalityCorpId(municipalityRepo.findById(signUpRequest.getMuncipalityID()).get());
		user.setRoleId(signUpRequest.getRoleId());
		Role role = roleRepository.findById(signUpRequest.getRoleId()).get();
		// user.setActiveSts(signUpRequest.getActiveSts());
		user.setRoles(role);
		user.setCreatedOn(LocalDate.now());
		// Set<Role> roles = new HashSet<>();
		user.setCreatedBy(signUpRequest.getLogedInUserId());
		userRepository.save(user);
		return ResponseEntity.ok(new JsonMessageResponse("User registered successfully!"));
	}

	@PostMapping("/sentEmail")
	public ResponseEntity<?> emailService(@RequestParam String emailid, @RequestParam String emailBody,
			@RequestParam String emailSubject) throws AddressException, MessagingException, EmailNotFoundException {
		boolean existsByEmail = userRepository.existsByEmail(emailid);
		if (existsByEmail == true) {
			try {
				boolean sendEmail = mailUtil.sendEmail(emailid,emailSubject, emailBody);
				if (sendEmail == true) {
					return ResponseEntity.ok(new MessageResponse("email send successfully"));
				} else {
					throw new EmailNotFoundException("email could not able to sent : there may be some problem's");
				}
			} catch (Exception e) {
				e.printStackTrace();
				// throw e;
				return ResponseEntity.ok(new MessageResponse(e.getMessage()));
			}
		} else
			throw new EmailNotFoundException("your email is not authorized");
	}

	@PostMapping("/forgetPassword")
	public ResponseEntity<?> forgetPassword(@RequestParam String email, @RequestParam String newPassword)
			throws EmailNotFoundException {
		UserEntity userObject = userRepository.findByEmail(email)
				.orElseThrow(() -> new EmailNotFoundException("email not found !!"));
		//UserEntity userObject = userRepository.findByEmail(email).orElse(null);
		if (userObject != null) {
			userObject.setPassword(encoder.encode(newPassword));
		} else
			throw new UserNotFoundException("user not found with the EmailId: " + email + " ");
		userRepository.save(userObject);
		return ResponseEntity.ok(new MessageResponse("password changed successfully !"));
	}

	@PutMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
		UserEntity userEntity = userRepository.findById(request.getUserId()).orElse(null);
		if (userEntity != null) {
			if (encoder.matches(request.getOldPassword(), userEntity.getPassword())) {
				userEntity.setPassword(encoder.encode(request.getNewPassword()));
				userRepository.save(userEntity);
				return ResponseEntity.ok(new MessageResponse("success"));
			} else
				return ResponseEntity.ok(new MessageResponse("Old Password is incorrect"));
		} else
			return ResponseEntity.ok(new MessageResponse("User Not Found"));

	}

}
