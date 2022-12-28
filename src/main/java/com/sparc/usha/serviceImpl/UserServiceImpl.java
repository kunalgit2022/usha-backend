package com.sparc.usha.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparc.usha.entity.Role;
import com.sparc.usha.entity.UserEntity;
import com.sparc.usha.repository.MunicipalityRepository;
import com.sparc.usha.repository.RoleRepository;
import com.sparc.usha.repository.UserRepository;
import com.sparc.usha.response.UserInfoResponse;
import com.sparc.usha.response.UserInfoResponse.UserDetailsResponse;
import com.sparc.usha.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	MunicipalityRepository municipalityRepo;
	@Autowired
	RoleRepository roleRepo;

	@Override
	public List<UserDetailsResponse> getUserDetailByUserId(Integer userId) {
		Optional<UserEntity> userData = userRepo.findById(userId);
		List<UserDetailsResponse> userDataList = new ArrayList<>();
		if (userData.isPresent()) {
			userData.stream().forEach(x -> {
				UserDetailsResponse response = new UserInfoResponse().new UserDetailsResponse();
				response.setName(x.getName());
				response.setMobileNo(x.getMobileNo());
				response.setAlternatePhoneNo(x.getAlternatePhoneNo());
				response.setRoleId(x.getRoleId());
				response.setMunicipalityId(x.getMunicipalityCorpId().getMunicipalityId());
				response.setMunicipalityName(x.getMunicipalityCorpId().getMunicipalityName());
				response.setEmail(x.getEmail());
				userDataList.add(response);
			});
		}
		return userDataList;
	}

	@Override
	public List<Role> getAllUserRole() {
		return roleRepo.findAll();
	}

//	@Override
//	public List<UserEntity> updateUserByUserId(Integer userId) {
//		Optional<UserEntity> userData = userRepo.findById(userId);	
//		if(userData.isPresent()) {
//			userData.stream().forEach(x->{
//				UserInfoResponse response = new UserInfoResponse();
//				response.setMobileNo(x.getMobileNo());
//				response.setEmail(x.getEmail());
//				response.setActiveStatus(x.isActiveSts());
//				response.setMuncipalityDetails(municipalityRepo.findById(x.getMunicipalityCorpId().getMunicipalityId()).get());
//				response.setName(x.getName());
//				response.set
//			});
//		}
//		return null;
	// }
	@Override
	public String updateUserByUserId(UserInfoResponse response) {
		UserEntity dataList = userRepo.getById(response.getId());
		try {
			if (dataList != null) {
				if(response.getName()!=null)					
				dataList.setName(response.getName());
				//dataList.setActiveSts(response.getActiveStatus());
				if(response.getMunicipalityId()!=null)
				dataList.setMunicipalityCorpId(
						municipalityRepo.getById(response.getMunicipalityId()));
				if(response.getRoleId()!=null)
				dataList.setRoleId(response.getRoleId());
				if(response.getEmail()!=null)
				dataList.setEmail(response.getEmail());			
				if(response.getMobileNo()!=null)
				dataList.setMobileNo(response.getMobileNo());
				if(response.getAlternatePhoneNo()!=null)
				dataList.setAlternatePhoneNo(response.getAlternatePhoneNo());
				if(response.getUpdatedBy()!=null)
				dataList.setUpdatedBy(response.getUpdatedBy());
				dataList.setUpdatedOn(LocalDate.now());
				UserEntity save = userRepo.save(dataList);
				if (save != null)
					return "success";
				else
					return "failed";
			} else
				return "error";
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	@Override
	public List<UserDetailsResponse> getDataOfAllUserByMunicipalityId(Integer municipalityId) {
		List<UserEntity> data = userRepo.getByMunicipalityId(municipalityId); 
		List<UserDetailsResponse> list = new ArrayList<>();
		data.forEach(x->{
			UserDetailsResponse response = new UserInfoResponse().new UserDetailsResponse();
			response.setId(x.getId());
			response.setName(x.getName());
			response.setEmail(x.getEmail());
			response.setMobileNo(x.getMobileNo());
			response.setAlternatePhoneNo(x.getAlternatePhoneNo());
			response.setMuncipalityDetails(x.getMunicipalityCorpId());
			//response.setMunicipalityId(municipalityId);
			//response.setMunicipalityName(municipalityRepo.findById(municipalityId).get().getMunicipalityName());
			response.setRoleName(roleRepo.findById(x.getRoleId()).get().getRoleName().toString());
			response.setRoleId(x.getRoleId());
			response.setActiveStatus(x.isActiveSts());
			list.add(response);
			
		});
		return list;
	}
	
}
