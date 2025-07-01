package com.MultipleDBConnections.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.support.MultipartFilter;

import com.MultipleDBConnections.model1.FileData;
import com.MultipleDBConnections.model1.Student;
import com.MultipleDBConnections.model2.Department;
import com.MultipleDBConnections.repo1.FileDataRepo;
import com.MultipleDBConnections.repo1.StudentRepo;
import com.MultipleDBConnections.repo2.DepartmentRepo;

@RestController
@RequestMapping("/api")
public class MultipleDBController {

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	DepartmentRepo departmentRepo;
	
	@Autowired
	FileDataRepo fileDataRepo;

	@PostMapping(value = "/save-student")
	public Student saveStudent(@RequestBody Student std) {
		Map<String, Object> result = new HashMap<>();

		return studentRepo.save(std);
	}
	
	@GetMapping(value="/get-Student")
	public List<Student> getStudent(String name, String email){
		  List<Student> res = studentRepo.findByFirstNameAndEmailOrderByEmail(name, email);
		  List<Student> res1 = studentRepo.findByFirstNameOrEmail(name, email);
		  
		 List<Student> res2 = studentRepo.findByFirstNameOrEmailOrderByEmailDesc(name, email);
		 List<Student> top = studentRepo.findTopByFirstNameOrEmailOrderByEmailDesc(name, email);
		 List<Student> top2 = studentRepo.findTop2ByFirstNameOrEmailOrderByEmailDesc(name, email);
//		  List<Student> res2 = studentRepo.findByFirstNameAndemail(name, email);
		return res;
	}
	@PostMapping(value = "/save-department")
	public Department saveDepartment(@RequestBody Department dept) {
		Map<String, Object> result = new HashMap<>();
		
		return departmentRepo.save(dept);
	}
	
	
	@GetMapping(value="/get-pagable-data")
	public Page<Student> getDataByPagenation(Integer pageNum, Integer pageSize){
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Student> res = studentRepo.findAll(pageable);

		Sort sort = Sort.by(Sort.Direction.ASC, "userId");
		Pageable pageableSort = PageRequest.of(pageNum, pageSize, sort);
		Page<Student> pageableSortRes = studentRepo.findAll(pageableSort);
		return pageableSortRes;
	}
	
	@GetMapping(value = "/get-student-by-query")
	public List<Student> getStudentByQuery(@RequestParam String name, @RequestParam String email) {
		List<Student> res = studentRepo.getStudentsByList(name, email, Sort.by("email").descending());
		return res;
	}
	
	@RequestMapping(value = "/test")
	public String getTest() {
		return null;

	}
	
	public String uploadFile(MultipartFilter file) {
//		fileDataRepo.save(FileData);
		return null;
	}
}
