package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.entity.NhanVien;
import com.project.entity.PhanQuyen;
import com.project.entity.TaiKhoan;
import com.project.services.NhanVienService;
import com.project.services.PhanQuyenService;
import com.project.services.TaiKhoanService;

@Controller
public class LoginController {
	@Autowired
	private TaiKhoanService taiKhoanService;
	@Autowired
	private NhanVienService nhanVienService;
	@Autowired
	private PhanQuyenService phanQuyenService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginInterface(ModelMap modelMap, HttpSession session) {
		modelMap.addAttribute("error", "");
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(ModelMap modelMap, @RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session, HttpServletRequest request) {
		String captcha = session.getAttribute("captcha_security").toString();
		String verifyCaptcha = request.getParameter("captcha");
		List<TaiKhoan> listTaiKhoan = taiKhoanService.listAll();
		TaiKhoan currentUser = null;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		for (TaiKhoan taiKhoan : listTaiKhoan) {
			if (taiKhoan.getUsername().equals(username) && passwordEncoder.matches(password, taiKhoan.getPassword())) {
				currentUser = taiKhoan;
			}
		}

		if (currentUser == null) {
			modelMap.addAttribute("error", "Invalid username or password.");
			return "login";
		}
		
		if (currentUser.getTrangThai() == 0) {
			modelMap.addAttribute("error", "This account has been locked.");
			return "login";
		}

		if (captcha.equals(verifyCaptcha)) {
			session.setAttribute("currentUser", currentUser);
			NhanVien nhanVien = nhanVienService.findByUserName(currentUser.getUsername());
			session.setAttribute("currentEmployee", nhanVien);
			PhanQuyen phanQuyen = phanQuyenService.findByMaQuyen(currentUser.getPhanQuyen().getMaQuyen());
			session.setAttribute("currentAccess", phanQuyen);
			return "redirect:dashboard.htm";
		} else {
			modelMap.addAttribute("error", "Invalid captcha.");
			return "login";
		}
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("currentUser");
		session.removeAttribute("currentEmployee");
		session.removeAttribute("currentAccess");
		return "redirect:/login.htm";
	}
}
