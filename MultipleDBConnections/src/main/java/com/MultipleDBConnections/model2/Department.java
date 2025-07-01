package com.MultipleDBConnections.model2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue(generator = "department_seq_id")
	@SequenceGenerator(name = "department_seq_id", sequenceName = "department_seq_id", allocationSize = 1)
	private Integer id;

	@Column(name = "dept_name")
	private String deptName;

	public Department() {
	}

	public Department(Integer id, String deptName) {
		this.id = id;
		this.deptName = deptName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", deptName=" + deptName + "]";
	}

}
