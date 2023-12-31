package com.junioralves.workshop.dto;

import java.io.Serializable;

import com.junioralves.workshop.domain.User;

public class UserDTO implements Serializable {
	
	private static final long serialVersionUID =1L;
	
	private String id;
	private String name;
	private String email;
	
	public UserDTO() {
		
	}
	
	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		email= obj.getEmail();
		
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(),objDto.getName(),objDto.getEmail()); // Instanciando o Dto
		
	}
	
}
