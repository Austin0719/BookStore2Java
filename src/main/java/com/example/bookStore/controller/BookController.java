package com.example.bookStore.controller;


import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookStore.entity.BookDTO;
import com.example.bookStore.service.BookService;




@RestController
@RequestMapping("/bookStore")
public class BookController {
	@Autowired
	private BookService bkService;
	
	@PostMapping("/borrow")
	public ResponseEntity<BookDTO> borrow(@RequestBody String json) {
		JSONObject reqJsonObj = new JSONObject(json);
		Integer userId = reqJsonObj.isNull("userId") ? null : reqJsonObj.getInt("userId");
		Integer inventoryId = reqJsonObj.isNull("inventoryId") ? null : reqJsonObj.getInt("inventoryId");

		BookDTO borrow = bkService.borrow(userId,inventoryId);
		return ResponseEntity.ok(borrow);
		
	}
	
	@PostMapping("/returning")
	public ResponseEntity<BookDTO> returning(@RequestBody String json) {
		JSONObject reqJsonObj = new JSONObject(json);
		Integer userId = reqJsonObj.isNull("userId") ? null : reqJsonObj.getInt("userId");
		Integer inventoryId = reqJsonObj.isNull("inventoryId") ? null : reqJsonObj.getInt("inventoryId");
		BookDTO returning = bkService.returning(userId, inventoryId);
		return ResponseEntity.ok(returning);
	}
	
	@GetMapping("/auth/showAllBooks")
	public List<BookDTO> showAllBooks() {
		List<BookDTO> showAllBooks = bkService.showAllBooks();
		return showAllBooks;
	}
	
	@GetMapping("/showBorrowingBooks/{userId}")
	public List<BookDTO> showBorrowingBooks(@PathVariable(value = "userId") Integer userId) {
		
		List<BookDTO> showBorrowingBooks = bkService.showBorrowingBooks(userId);
		return showBorrowingBooks;
	}
	
	@GetMapping("/showReturningBooks/{userId}")
	public List<BookDTO> showReturning(@PathVariable(value = "userId") Integer userId) {
		
		List<BookDTO> showBorrowingBooks = bkService.showReturningBooks(userId);
		return showBorrowingBooks;
	}
	
}
