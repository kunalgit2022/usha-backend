package com.sparc.usha.service;

import java.util.List;

import com.sparc.usha.entity.Role;
import com.sparc.usha.entity.UserEntity;
import com.sparc.usha.response.UserInfoResponse;
import com.sparc.usha.response.UserInfoResponse.UserDetailsResponse;

public interface UserService {
	
	List<UserDetailsResponse> getUserDetailByUserId(Integer userId);

	List<Role> getAllUserRole();

	//List<UserEntity> updateUserByUserId(Integer userId);

	String updateUserByUserId(UserInfoResponse response);

	List<UserDetailsResponse> getDataOfAllUserByMunicipalityId(Integer municipalityId);

}
