package com.example.parser.repository;

import com.example.parser.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Customer getBySubjId(Long subjId);
    List<Customer> getTop100ByIsUpdatedFalse();
}