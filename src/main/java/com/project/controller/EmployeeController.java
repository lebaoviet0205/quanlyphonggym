package com.project.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.entity.KhachHang;
import com.project.entity.NhanVien;
import com.project.entity.PhanQuyen;
import com.project.entity.TaiKhoan;
import com.project.services.NhanVienService;
import com.project.services.PhanQuyenService;
import com.project.services.TaiKhoanService;

@Controller
public class EmployeeController {
	@Autowired
	private NhanVienService nhanVienService;
	@Autowired
	private PhanQuyenService phanQuyenService;
	@Autowired
	private TaiKhoanService taiKhoanService;
	
	@RequestMapping("employee")
	public String employee(ModelMap model, HttpSession session) {
		model.addAttribute("listNhanVien", nhanVienService.listAll());
		
		return "employee";
	}
	
	@RequestMapping(value = "search-employee", method=RequestMethod.POST)
	public String searchEmployee(ModelMap model, HttpSession session, 
			@RequestParam("search") String search) {
		List<NhanVien> listNhanVien = nhanVienService.listAll();
		List<NhanVien> listNhanVienFiltered = new ArrayList<NhanVien>();
		
		for(NhanVien item: listNhanVien) {
			if (item.getFullname().contains(search)) {
				listNhanVienFiltered.add(item);
			}
		}
		
		model.addAttribute("search", search);
		model.addAttribute("listNhanVien", listNhanVienFiltered);
		
		return "employee";
	}
	
	@RequestMapping(value = "add-employee", method = RequestMethod.GET)
	public String addEmployeeInterface(ModelMap model, HttpSession session) {
		PhanQuyen phanQuyen = (PhanQuyen) session.getAttribute("currentAccess");
		model.addAttribute("nhanVien", new NhanVien());
		model.addAttribute("currentAccess", phanQuyen.getMaQuyen());
		model.addAttribute("today", LocalDate.now());
		
		return "addEmployee";
	}
	
	@RequestMapping(value="add-employee", method = RequestMethod.POST)
	public String addEmployee(ModelMap model, HttpSession session,
			@RequestParam("ChucVu") String chucVu,
			@RequestParam("profile") CommonsMultipartFile file,
			@ModelAttribute("nhanVien") @Validated NhanVien nhanVien, BindingResult errors) {
		if (!errors.hasErrors()) {
			boolean isValid = true;
			for(NhanVien item: nhanVienService.listAll()) {
				if (item.getEmail().equals(nhanVien.getEmail())) {
					model.addAttribute("emailError", "Email này đã có người sử dụng.");
					isValid = false;
				}
				if (item.getSDT().equals(nhanVien.getSDT())) {
					model.addAttribute("sdtError", "Số điện thoại này đã có người sử dụng.");
					isValid = false;
				}
			}
			if (!isValid) {
				return "addEmployee";
			}
			
			if (!file.isEmpty()) {
				byte[] data = file.getBytes();
				String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
				String path = session.getServletContext().getRealPath("/") + "files" + File.separator + "employee" + File.separator + nhanVien.getSDT() + "_0" + fileExtension;
				
				try {
					FileOutputStream fos = new FileOutputStream(path);
					fos.write(data);
					fos.close();
					nhanVien.setAnh("/files/employee/" + nhanVien.getSDT() + "_0" + fileExtension);
					System.out.println(path);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			
			session.setAttribute("chucVu", chucVu);
			session.setAttribute("nhanVienInfor", nhanVien);
			return "redirect:add-account.htm";
		}
		
		return "addEmployee";
	}
	
	@RequestMapping(value = "add-account", method = RequestMethod.GET)
	public String addAccountInterface(ModelMap model, HttpSession session) {
		NhanVien nhanVien = (NhanVien)session.getAttribute("nhanVienInfor");
		TaiKhoan taiKhoan = new TaiKhoan();
		String username = nhanVien.getEmail().substring(0, nhanVien.getEmail().indexOf("@"));
		String newUsername = username;
		
		int index = 1;
		while(taiKhoanService.findByUsername(newUsername) != null) {
		    newUsername = username + String.valueOf(index);
		}
		
		String password = "123456";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);
		taiKhoan.setUsername(newUsername);
		taiKhoan.setPassword(encodedPassword);
		
		model.addAttribute("taiKhoan", taiKhoan);
		
		return "addAccount";
	}
	
	@RequestMapping(value = "add-account", method = RequestMethod.POST)
	public String addAccount(ModelMap model, HttpSession session,
			@ModelAttribute("taiKhoan") @Validated TaiKhoan taiKhoan, BindingResult errors) {		
		if(!errors.hasErrors()) {
			if (nhanVienService.findByUserName(taiKhoan.getUsername()) != null) {
				model.addAttribute("errorMessage", "Tên tài khoản đã tồn tại");
				return "addAccount";
			}
			
			NhanVien nhanVien = (NhanVien)session.getAttribute("nhanVienInfor");
			String chucVu = (String)session.getAttribute("chucVu");
			
			taiKhoan.setPhanQuyen(phanQuyenService.findByMaQuyen(chucVu));
			taiKhoan.setTrangThai(1);

			nhanVien.setTaiKhoan(taiKhoan);
			nhanVienService.save(nhanVien);
			
			taiKhoan.setNhanVien(nhanVien);
			taiKhoanService.save(taiKhoan);
			
			model.addAttribute("successMessage", "Thêm nhân viên thành công");
		}
		
		return "addAccount";
	}
	
	@RequestMapping("change-account-status")
	public String changeAccountStatus(HttpSession session,
			@RequestParam("id") String username) {
		NhanVien nhanVien = nhanVienService.findByUserName(username);
		nhanVien.getTaiKhoan().setTrangThai(nhanVien.getTaiKhoan().getTrangThai() == 1 ? 0 : 1);
		nhanVienService.save(nhanVien);
		
		return "redirect:employee.htm";
	}
	
	@RequestMapping(value="edit-employee.htm", method=RequestMethod.GET)
	public String editEmployeeInterface(ModelMap model, HttpSession session,
			@RequestParam("id") String maNV,
			@ModelAttribute("errorMessage") String errorMessage) {
		PhanQuyen phanQuyen = (PhanQuyen) session.getAttribute("currentAccess");
		NhanVien nhanVien = nhanVienService.findByMaNV(maNV);
		model.addAttribute("currentAccess", phanQuyen.getMaQuyen());
		model.addAttribute("nhanVien", nhanVien);
		model.addAttribute("taiKhoan", nhanVien.getTaiKhoan());
		model.addAttribute("errorMessage", errorMessage);
		
		return "editEmployee";
	}
	
	@RequestMapping(value = "edit-employee", method = RequestMethod.POST)
	public String editEmployee(ModelMap model, HttpSession session,
			@RequestParam("id") String maNV,
			@RequestParam("chucVu") Optional<String> chucVu,
			@RequestParam("profile") CommonsMultipartFile file,
			@ModelAttribute("nhanVien") @Validated NhanVien nhanVien, BindingResult errors) {
		if(!errors.hasErrors()) {
			boolean isValid = true;
			for(NhanVien item: nhanVienService.listAll()) {
				if (item.getMaNV().equals(maNV)) {
					continue;
				}
				
				if (item.getEmail().equals(nhanVien.getEmail())) {
					model.addAttribute("emailError", "Email này đã có người sử dụng.");
					isValid = false;
				}
				if (item.getSDT().equals(nhanVien.getSDT())) {
					model.addAttribute("sdtError", "Số điện thoại này đã có người sử dụng.");
					isValid = false;
				}
			}
			if (!isValid) {
				nhanVien.setMaNV(maNV);
				model.addAttribute("nhanVien", nhanVien);
				model.addAttribute("taiKhoan", nhanVienService.findByMaNV(maNV).getTaiKhoan());
				return "editEmployee";
			}
			
			NhanVien nv = nhanVienService.findByMaNV(maNV);
			
			if (!file.isEmpty()) {	
				byte[] data = file.getBytes();
				String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
				String path = "";
				int count = 0;
				
				if (nv.getAnh() != null) {
					count = Integer.valueOf(nv.getAnh().substring(nv.getAnh().lastIndexOf('_') + 1, nv.getAnh().lastIndexOf('.')));
					count = count + 1;
				}
				
				path = session.getServletContext().getRealPath("/") + "files" + File.separator + "employee" + File.separator + nhanVien.getSDT() + "_" + count + fileExtension;
				try {
					FileOutputStream fos = new FileOutputStream(path);
					fos.write(data);
					fos.close();
					nv.setAnh("/files/employee/" + nhanVien.getSDT() + "_" + count + fileExtension);
					System.out.println(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			nv.setHo(nhanVien.getHo());
			nv.setTen(nhanVien.getTen());
			nv.setSDT(nhanVien.getSDT());
			nv.setEmail(nhanVien.getEmail());
			nv.setGioiTinh(nhanVien.getGioiTinh());
			nv.setDiaChi(nhanVien.getDiaChi());
			
			if(chucVu.isPresent()) {
				nv.getTaiKhoan().setPhanQuyen(phanQuyenService.findByMaQuyen(chucVu.get()));
			}
			
			taiKhoanService.save(nv.getTaiKhoan());
			nhanVienService.save(nv);
			model.addAttribute("maNV", nv.getMaNV());
			model.addAttribute("nhanVien", nv);
			model.addAttribute("taiKhoan", nv.getTaiKhoan());
			model.addAttribute("successMessage", "Chỉnh sửa thông tin thành công");
		}
		
		nhanVien.setMaNV(maNV);
		model.addAttribute("nhanVien", nhanVien);
		model.addAttribute("taiKhoan", nhanVienService.findByMaNV(maNV).getTaiKhoan());
		return "editEmployee";
	}
	
	@RequestMapping(value = "change-password", method = RequestMethod.GET)
	public String changePassword(ModelMap model, HttpSession session) {
		NhanVien nv = (NhanVien) session.getAttribute("currentEmployee");
		model.addAttribute("taiKhoan", nhanVienService.findByMaNV(nv.getMaNV()).getTaiKhoan());
		return "changePassword";
	}
	
	@RequestMapping(value = "edit-account", method = RequestMethod.POST)
	public String editAccount(ModelMap model, HttpSession session, RedirectAttributes redirectAttributes,
			@RequestParam("id") String username,
			@RequestParam("NhapLaiPassword") String nhapLaiPassword,
			@ModelAttribute("taiKhoan") @Validated TaiKhoan taiKhoan, BindingResult errors) {
		TaiKhoan tk = taiKhoanService.findByUsername(username);
		if(!errors.hasErrors()) {
			
			if (!nhapLaiPassword.equals(taiKhoan.getPassword())) {
				model.addAttribute("nhanVien", tk.getNhanVien());
				model.addAttribute("taiKhoan", tk);
				model.addAttribute("successMessage", "Mật khẩu nhập lại không đúng");
				return "changePassword";
			}
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(taiKhoan.getPassword());
			
			tk.setPassword(encodedPassword);
			taiKhoanService.save(tk);
			model.addAttribute("maNV", tk.getNhanVien().getMaNV());
			model.addAttribute("successMessage", "Đổi mật khẩu thành công");
		} else {
			model.addAttribute("successMessage", "Đổi mật khẩu không thành công");
		}
		
		model.addAttribute("nhanVien", tk.getNhanVien());
		model.addAttribute("taiKhoan", tk);
		return "changePassword";
	}
}
