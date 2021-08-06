package com.caribbean.online.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="CUSTOMERS")
public class CustomerEntity implements Serializable {

	private static final long serialVersionUID = -2626850894242271187L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false, length=50)
	private String firstName;
	
	@Column(nullable=false, length=50)	
	private String lastName;
	
	@Column(nullable=false, unique=true)
	private String customerId;
	
	@Column(nullable=false, length=150, unique=true)
	private String email;
	
	@Column(nullable=false, length=200)
	private String address;
	
	@Column(nullable=false, length=20)
	private String phoneNumber;

	@Column(nullable=false, unique=true)
	private String encryptedPassword;

}
