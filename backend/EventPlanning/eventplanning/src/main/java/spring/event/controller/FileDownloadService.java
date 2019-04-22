package spring.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;


import spring.event.model.FileStorageProperties;
import spring.event.exception.MyFileNotFoundException;


import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;




@Service
public class FileDownloadService {
	
	 private final Path fileStorageLocation;
	 
	 @Autowired
	    public FileDownloadService(FileStorageProperties fileStorageProperties) {
	        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
	                .toAbsolutePath().normalize();

	        try {
	            Files.createDirectories(this.fileStorageLocation);
	        } catch (Exception ex) {
	            System.out.println(ex);
	        	//throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
	        }
	    }

	
	public Resource loadFileAsResource(String fileName) {
        try {
        	
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
            	
            	throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        	
        }
    }

}
