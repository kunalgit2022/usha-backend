	package com.sparc.usha.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sparc.usha.entity.MunicipalityCorpoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
//@JsonInclude(value = Include.NON_NULL)
public class UserInfoResponse {	
	private String alternatePhoneNo;
	private Integer id;
	//private String username;
	private String mobileNo;
	private String email;
	private MunicipalityCorpoEntity muncipalityDetails; 	
	private Integer municipalityId;
	private List<String> roles;
	private Integer roleId;
	private String municipalityName;
	private String roleName;
	private String token;
	private String type;
	private String name;
	private Integer updatedBy;
	private Boolean activeStatus;
	public UserInfoResponse(@NonNull Integer id, String name, String mobileNo, String email,MunicipalityCorpoEntity muncipalityDetails, List<String> roles, Integer roleId, String token, String type) {
		super();
		this.id = id;
		this.name = name;
		this.mobileNo = mobileNo;		
		this.email = email;		
		this.muncipalityDetails = muncipalityDetails;
		this.roles = roles;
		this.roleId = roleId;
		this.token = token;
		this.type = type;
	}
	
	@Data
	public class UserDetailsResponse{
		private Integer id;
		private String alternatePhoneNo;		
		private String mobileNo;
		private String email;
		private MunicipalityCorpoEntity muncipalityDetails; 
		private String municipalityName;
		private Integer municipalityId;
		private Integer roleId;		
		private String roleName;
		private String name;
		private Boolean activeStatus;
		
	}
	
	
}
