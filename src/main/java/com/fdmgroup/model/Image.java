package com.fdmgroup.model;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class Image {
	 private List<MultipartFile> Files;

	/**
	 * @return the files
	 */
	public List<MultipartFile> getFiles() {
		return Files;
	}

	/**
	 * @param files the files to set
	 */
	public void setFiles(List<MultipartFile> files) {
		Files = files;
	}
	 
	 
}
