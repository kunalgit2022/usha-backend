package com.sparc.usha.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupRequest {
	private String Name;
	private String mobileNo;
	private String alternatemobileNo;
	//@JsonIgnore
	private String password;
	private String email;
	private Integer roleId;
	private Integer logedInUserId;
	private Integer muncipalityID;
	private Boolean activeSts=true;
}
