package com.MultipleDBConnections.repo1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MultipleDBConnections.model1.FileData;

@Repository
public interface FileDataRepo  extends JpaRepository<FileData, Integer>{

}
