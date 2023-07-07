package com.mll.backen.repository;

import com.mll.backen.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products,Integer> {
}
