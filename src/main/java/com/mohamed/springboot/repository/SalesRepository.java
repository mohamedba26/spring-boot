package com.mohamed.springboot.repository;

import com.mohamed.springboot.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales,Long> {
}
