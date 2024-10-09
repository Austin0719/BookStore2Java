package com.example.bookStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.bookStore.entity.BorrowingRecord;




@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer> {
	@Query("FROM BorrowingRecord WHERE user.userId = ?1 AND inventory.inventoryId =?2 AND returnTime IS NULL")
	BorrowingRecord findByUserIdAndInventoryId(Integer userId, Integer inventory);
	
	@Query("FROM BorrowingRecord WHERE user.userId = ?1 And returnTime IS NULL")
	List<BorrowingRecord> findByUserId(Integer userId);
	
	@Query("FROM BorrowingRecord WHERE user.userId = ?1 And returnTime IS NOT NULL")
	List<BorrowingRecord> findByUserIdReturnBooks(Integer userId);
	
}
