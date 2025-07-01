package com.MultipleDBConnections.model1;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;

@Entity
@Table(name = "file_data")
@Builder
public class FileData {

	@Id
	@GeneratedValue(generator = "file_data_seq")
	@SequenceGenerator(name = "file_data_seq", sequenceName = "file_data_seq", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	@Lob
	@Column(name = "fileData")
	private byte[] fileData;

	public FileData() {
	}

	public FileData(Integer id, String name, String type, byte[] fileData) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.fileData = fileData;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	@Override
	public String toString() {
		return "FileData [id=" + id + ", name=" + name + ", type=" + type + ", fileData=" + Arrays.toString(fileData)
				+ "]";
	}

}
