package com.fdmgroup.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.fdmgroup.model.Image;

@Controller
public class ImageController {
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String crunchifyDisplayForm() {
        return "ImageTest";
    }
 
    @RequestMapping(value = "/savefiles", method = RequestMethod.POST)
    public String crunchifySave(@ModelAttribute("uploadForm") Image uploadForm, Model map, HttpSession session) 
    		throws IllegalStateException, IOException {
    	
    	String path = session.getServletContext().getRealPath("/");  
    	
		File dir = new File(path + "/resources/images");
		if (!dir.exists()) {
			dir.mkdirs();
		}
 
        List<MultipartFile> crunchifyFiles = uploadForm.getFiles();
 
        List<String> fileNames = new ArrayList<String>();
 
        if (null != crunchifyFiles && crunchifyFiles.size() > 0) {
            for (MultipartFile multipartFile : crunchifyFiles) {
 
                String fileName = multipartFile.getOriginalFilename();
                
                if (!"".equalsIgnoreCase(fileName)) {                	
                	System.out.println(dir.getAbsolutePath()+ File.separator + fileName);
                	
                    File serverFile = new File(dir.getAbsolutePath()+ File.separator + fileName); //you can change filename to gameId
                    
                    byte[] bytes = multipartFile.getBytes();
                    
                    BufferedOutputStream stream = new BufferedOutputStream(
    						new FileOutputStream(serverFile));
    				stream.write(bytes);
    				stream.close();
                    
                    fileNames.add(fileName);
                }
            }
        }
 
        map.addAttribute("files", fileNames);
        return "uploadfilesuccess";
    }

}
