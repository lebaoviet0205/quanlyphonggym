package com.project.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.entity.NhanVien;
import com.project.entity.PhanQuyen;
import com.project.entity.TaiKhoan;
import com.project.services.NhanVienService;
import com.project.services.PhanQuyenService;
import com.project.services.TaiKhoanService;

@Controller
public class HomeController {
	@RequestMapping(value = "/")
	public ModelAndView test(HttpServletResponse response) throws IOException {
		return new ModelAndView("home");
	}

	@RequestMapping("home")
	public String home() {
		return "home";
	}

	@RequestMapping("contact")
	public String contact() {
		return "contact";
	}

	@RequestMapping("about")
	public String about() {
		return "about";
	}

	@RequestMapping("class")
	public String classes() {
		return "class";
	}

	@RequestMapping("team")
	public String team() {
		return "team";
	}

	@RequestMapping("blog")
	public String blog() {
		return "blog";
	}

	@RequestMapping("detail")
	public String detail() {
		return "detail";
	}

	@RequestMapping("testimonial")
	public String testimonial() {
		return "testimonial";
	}
}
