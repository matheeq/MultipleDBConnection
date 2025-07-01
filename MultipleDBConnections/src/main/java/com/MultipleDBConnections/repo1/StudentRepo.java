package com.MultipleDBConnections.repo1;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MultipleDBConnections.model1.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{
	
	Student findByFirstName(String name);
	
	List<Student> findByFirstNameAndEmailOrderByEmail(String name, String mail);
//	List<Student> (String name, String mail);

	List<Student> findByFirstNameOrEmail(String name, String mail);
	
	List<Student> findByFirstNameOrEmailOrderByEmailDesc(String name, String mail);
	
	List<Student> findTopByFirstNameOrEmailOrderByEmailDesc(String name, String mail);
	
	List<Student> findTop2ByFirstNameOrEmailOrderByEmailDesc(String name, String mail);
//	List<Student> findByFirstNameAndemail(String name, String mail);
	
	
	@Query("From Student where firstName=:first_name or email=:email")
	List<Student> getStudentsByList( @Param("first_name") String name, @Param("email") String email , Sort sort);
	
	

}
