package com.example.bookStore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.bookStore.entity.Inventory;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
	@Query("FROM Inventory WHERE isbn = ?1")
	Inventory findByIsbn(String isbn);
}
