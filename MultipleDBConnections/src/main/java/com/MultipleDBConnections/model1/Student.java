package com.MultipleDBConnections.model1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "student_details")
public class Student {

	@Id
	@GeneratedValue(generator = "student_seq")
	@SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
	@Column(name = "user_Id")
	private int userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "email")
	private String email;

	public Student() {
	}

	public Student(int userId, String firstName, String email) {
		this.userId = userId;
		this.firstName = firstName;
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [userId=" + userId + ", firstName=" + firstName + ", email=" + email + "]";
	}

}
