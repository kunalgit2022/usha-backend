package com.sparc.usha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparc.usha.entity.Role;
import com.sparc.usha.enums.ERole;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	 Optional<Role> findByRoleName(ERole roleName);

}
