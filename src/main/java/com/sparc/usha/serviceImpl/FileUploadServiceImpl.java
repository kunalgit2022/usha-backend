package com.sparc.usha.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sparc.usha.customException.FileStorageException;
import com.sparc.usha.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	
	@Value("${file.upload-dir}")
	private String filelocation;

	@Override
	public String fileUpload(MultipartFile imageFileName) {
		Path fileStorageLocation = Paths.get(filelocation).toAbsolutePath().normalize();
		//String extension = FilenameUtils.getExtension(imageFileName.getOriginalFilename());
		try {
			Files.createDirectories(fileStorageLocation);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("issue with creating file directory");
		}
		 String originalimageFileName = StringUtils.cleanPath(imageFileName.getOriginalFilename());
		 //Path filePath = Paths.get(fileStorageLocation + "\\" + imageFilePath);
		   Path filePath = Paths.get(fileStorageLocation + "/" + originalimageFileName);
		try {
			// Check if the file's name contains invalid characters
			if (originalimageFileName.isEmpty() || originalimageFileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence or file is empty" + imageFileName);
			} else {
				// Copy file to the target location (Replacing existing file with the same name)
				Files.copy(imageFileName.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
				Set<PosixFilePermission> perms = Files.readAttributes(filePath, PosixFileAttributes.class)
						.permissions();
				perms.add(PosixFilePermission.OWNER_READ);
				perms.add(PosixFilePermission.GROUP_READ);
				perms.add(PosixFilePermission.OTHERS_READ);
				Files.setPosixFilePermissions(filePath, perms);
			}

		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + imageFileName + ". Please try again!", ex);
		}

		return originalimageFileName;
	}

}
