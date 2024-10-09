package com.example.bookStore.entity;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class BookDTO {
	private Integer inventoryId;
	private Integer userId;
	private String status;
	private String name;
	private String author;
	private String introduction;
	private String isbn;
	private LocalDateTime borrowingTime;
	private LocalDateTime returnTime;
	

}
