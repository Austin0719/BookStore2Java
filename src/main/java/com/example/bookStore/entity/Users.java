package com.example.bookStore.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "phone_number", unique = true, nullable = false)
	private String phoneNumber;

	@Column(nullable = false)
	private String password;

	@Column(name = "user_name",nullable = false)
	private String userName;

	@Column(name = "registration_time")
	private LocalDateTime registrationTime;

	@Column(name = "lastlogin_time")
	private LocalDateTime lastLoginTime;

	@PrePersist
	public void onCreate() {
		if (registrationTime == null) {
			registrationTime = LocalDateTime.now();
		}

	}
	
	@OneToMany(mappedBy = "user")
    private List<BorrowingRecord> borrowingRecords;
}
