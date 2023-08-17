package com.project.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class UploadFileController {
	@RequestMapping(value="upload-file", method = RequestMethod.GET)
	public String fileUploadUI () {
		return "fileForm";
	}
	
	@RequestMapping(value="upload-file", method = RequestMethod.POST)
	public String fileUpload (@RequestParam("profile") CommonsMultipartFile file, HttpSession session, ModelMap model) {
		byte[] data = file.getBytes();
		String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
		String path = session.getServletContext().getRealPath("/") + "files" + File.separator + "1234" + fileExtension;
		try {
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(data);
			fos.close();
			model.addAttribute("path", "/files/" + "1234" + fileExtension);
			System.out.println(path);
			System.out.println("File uploaded");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Uploading Error");
		}
		return "fileUploaded";
	}
}
