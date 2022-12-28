package com.sparc.usha.Security;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparc.usha.entity.MunicipalityCorpoEntity;
import com.sparc.usha.entity.Role;
import com.sparc.usha.entity.UserEntity;

public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String role;
	private String email;
	private String mobileNo;
	private String alternatePhoneNo;
	@JsonIgnore
	private String password;
	private Integer municipalityID;
	private UserEntity user;

	public UserDetailsImpl(UserEntity user) {
		super();
		this.user = user;
	}

	private Collection<? extends GrantedAuthority> authorities;

//	public UserDetailsImpl(Long id, String name, String email, String password,
//			Collection<? extends GrantedAuthority> authorities) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.email = email;
//		this.password = password;
//		this.authorities = authorities;
//	}

	/*
	 * public static UserDetailsImpl build(UserEntity user) {
	 * List<SimpleGrantedAuthority> authorities = Set.of(user.getRoles()).stream()
	 * .map(role -> new
	 * SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toSet());
	 * return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(),
	 * user.getPassword(), authorities); }
	 */

//	public static UserDetailsImpl build(UserEntity user) {
//		 Set<Role> roles = Set.of(user.getRoles());
//		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);
//	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Set.of(user.getRoles()).stream().map(f -> new SimpleGrantedAuthority(f.getRoleName().name()))
				.collect(Collectors.toList());
		// return null;
	}

	public Integer getId() {
		return user.getId();
	}

	public String getEmail() {
		return user.getEmail();
	}

	public MunicipalityCorpoEntity getMunicipalityID() {
		return user.getMunicipalityCorpId();
	}

	public Role getRole() {
		return user.getRoles();
	}

	public String getMobileNo() {
		return user.getMobileNo();
	}	

	public String getAlternatePhoneNo() {
		return user.getAlternatePhoneNo();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorities, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetailsImpl other = (UserDetailsImpl) obj;
		return Objects.equals(authorities, other.authorities) && Objects.equals(user, other.user);
	}

}
