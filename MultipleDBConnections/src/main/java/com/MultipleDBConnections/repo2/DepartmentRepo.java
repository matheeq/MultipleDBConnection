package com.MultipleDBConnections.repo2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MultipleDBConnections.model2.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer>{

}
