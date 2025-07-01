package com.example.demo.DAO;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>, JpaSpecificationExecutor<Product>{

	public List<Product> findAll(Specification<Product> spec);
}
