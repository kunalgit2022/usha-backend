package com.sparc.usha.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sparc.usha.entity.UserEntity;
import com.sparc.usha.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity findByMobileNo = userRepo.findByMobileNo(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		UserDetailsImpl newuser=new UserDetailsImpl(findByMobileNo);
		return newuser;
	}

}
