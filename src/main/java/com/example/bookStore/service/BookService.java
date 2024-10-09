package com.example.bookStore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookStore.entity.Book;
import com.example.bookStore.entity.BookDTO;
import com.example.bookStore.entity.BorrowingRecord;
import com.example.bookStore.entity.Inventory;
import com.example.bookStore.entity.Users;
import com.example.bookStore.repository.BookRepository;
import com.example.bookStore.repository.BorrowingRecordRepository;
import com.example.bookStore.repository.InventoryRepository;
import com.example.bookStore.repository.UsersRepository;

@Service
public class BookService {
	@Autowired
	private InventoryRepository inventoryRepo;
	@Autowired
	private UsersRepository usersRepo;
	@Autowired
	private BorrowingRecordRepository borrowRepo;
	@Autowired
	private BookRepository bkRepo;
	
	public List<BookDTO> showAllBooks() {
		List<BookDTO> bookDTOList = new ArrayList<>();
		List<Book> all = bkRepo.findAll();
		for(Book temp : all) {
			Inventory byIsbn = inventoryRepo.findByIsbn(temp.getIsbn());
			
			BookDTO bookDTO = new BookDTO();
			bookDTO.setAuthor(temp.getAuthor());
			bookDTO.setIntroduction(temp.getIntroduction());
			bookDTO.setIsbn(temp.getIsbn());
			bookDTO.setName(temp.getName());
			bookDTO.setInventoryId(byIsbn.getInventoryId());
			bookDTO.setStatus(byIsbn.getStatus());
			bookDTOList.add(bookDTO);
		}
		return bookDTOList;
	}
	
	public List<BookDTO> showBorrowingBooks(Integer userId){
		List<BookDTO> bookDTOList = new ArrayList<>();
		List<BorrowingRecord> byUserId = borrowRepo.findByUserId(userId);
		for(BorrowingRecord temp : byUserId) {
			BookDTO bookDTO = new BookDTO();
			Optional<Inventory> byId = inventoryRepo.findById(temp.getInventory().getInventoryId());
			if(byId.isPresent()){
				String isbn = byId.get().getIsbn();
				Optional<Book> byId2 = bkRepo.findById(isbn);
				if(byId2.isPresent()) {
					bookDTO.setName(byId2.get().getName());
					bookDTO.setIsbn(byId2.get().getIsbn());
					bookDTO.setAuthor(byId2.get().getAuthor());
					bookDTO.setIntroduction(byId2.get().getIntroduction());
				}
			}		
			bookDTO.setBorrowingTime(temp.getBorrowingTime());
			bookDTO.setReturnTime(temp.getReturnTime());
			bookDTO.setInventoryId(byId.get().getInventoryId());
			bookDTOList.add(bookDTO);
			
		}
		return bookDTOList;
	}
	
	public List<BookDTO> showReturningBooks(Integer userId){
		List<BookDTO> bookDTOList = new ArrayList<>();
		List<BorrowingRecord> byUserId = borrowRepo.findByUserIdReturnBooks(userId);
		for(BorrowingRecord temp : byUserId) {
			BookDTO bookDTO = new BookDTO();
			Optional<Inventory> byId = inventoryRepo.findById(temp.getInventory().getInventoryId());
			if(byId.isPresent()){
				String isbn = byId.get().getIsbn();
				Optional<Book> byId2 = bkRepo.findById(isbn);
				if(byId2.isPresent()) {
					bookDTO.setName(byId2.get().getName());
					bookDTO.setIsbn(byId2.get().getIsbn());
					bookDTO.setAuthor(byId2.get().getAuthor());
					bookDTO.setIntroduction(byId2.get().getIntroduction());
				}
			}		
			bookDTO.setBorrowingTime(temp.getBorrowingTime());
			bookDTO.setReturnTime(temp.getReturnTime());
			bookDTOList.add(bookDTO);
			
		}
		return bookDTOList;
	}
	
	

	public BookDTO borrow(Integer userId, Integer inventoryId) {
		Optional<Inventory> byId = inventoryRepo.findById(inventoryId);
		if (byId.isPresent()) {
			Inventory inven = byId.get();
			if (inven.getStatus() .equals("可借閱")) {
				inven.setStatus("出借中");
				BorrowingRecord borrowingRecord = addBorrowingRecord(userId, inventoryId);
				if (borrowingRecord != null) {
					inventoryRepo.save(inven);
					borrowRepo.save(borrowingRecord);
					BookDTO bookDTO = new BookDTO();
					bookDTO.setInventoryId(inventoryId);
					bookDTO.setStatus(inven.getStatus());
					bookDTO.setUserId(userId);
					return bookDTO;
				}
				return null;
			}
			return null;
		}
		return null;
	}
	
	
	
	public BookDTO returning(Integer userId, Integer inventoryId) {
		Optional<Inventory> byId = inventoryRepo.findById(inventoryId);
		if (byId.isPresent()) {
			Inventory inven = byId.get();
			if (inven.getStatus().equals("出借中") ) {
				inven.setStatus("可借閱");
				BorrowingRecord borrowingRecord = updateBorrowingRecord(userId, inventoryId);
				if (borrowingRecord != null) {
					inventoryRepo.save(inven);
					borrowRepo.save(borrowingRecord);
					BookDTO bookDTO = new BookDTO();
					bookDTO.setInventoryId(inventoryId);
					bookDTO.setStatus(inven.getStatus());
					bookDTO.setUserId(userId);
					return bookDTO;
				}
			}
			return null;
		}
		return null;
	}
	

	public BorrowingRecord addBorrowingRecord(Integer userId, Integer inventoryId) {
		Optional<Users> byId = usersRepo.findById(userId);
		Optional<Inventory> byId2 = inventoryRepo.findById(inventoryId);
		if (byId.isPresent() && byId2.isPresent()) {
			BorrowingRecord bRecord = new BorrowingRecord();
			bRecord.setUser(byId.get());
			bRecord.setInventory(byId2.get());
			bRecord.setBorrowingTime(LocalDateTime.now());
			return bRecord;
		}
		return null;
	}
	
	public BorrowingRecord updateBorrowingRecord(Integer userId, Integer inventoryId) {
		BorrowingRecord temp = borrowRepo.findByUserIdAndInventoryId(userId, inventoryId);
		if (temp!=null) {
			temp.setReturnTime(LocalDateTime.now());
			borrowRepo.save(temp);
			return temp;
		}
		return null;		
	}

}
