package com.sparc.usha.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	
	public String fileUpload(MultipartFile imageFileName);

}
