package com.project.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.entity.BangDiemDanh;
import com.project.entity.ChiTietThe;
import com.project.entity.KhachHang;
import com.project.entity.NhanVien;
import com.project.entity.The;
import com.project.services.BangDiemDanhService;
import com.project.services.ChiTietTheService;
import com.project.services.KhachHangService;
import com.project.services.NhanVienService;
import com.project.services.TheService;

@Controller
public class CheckInController {
	@Autowired
	private KhachHangService khachHangService;
	@Autowired
	private TheService theService;
	@Autowired
	private NhanVienService nhanVienService;
	@Autowired
	private ChiTietTheService chiTietTheService;
	@Autowired
	private BangDiemDanhService bangDiemDanhService;
	
	@RequestMapping(value = "check", method = RequestMethod.GET)
	public String checkInInterface(ModelMap model, HttpSession session, 
			@RequestParam("cardId") Optional<String> maThe,
			@RequestParam("date") Optional<String> date,
			@ModelAttribute("hoTen") String hoTen,
			@ModelAttribute("errorMessage") String errorMessage) {
		if(maThe.isPresent()) {
			model.addAttribute("maThe", maThe.get());
			model.addAttribute("the", theService.findByMaThe(maThe.get()));
			model.addAttribute("nhanVien", (NhanVien) session.getAttribute("currentEmployee"));
			model.addAttribute("listNhanVien", nhanVienService.listAll());
		}
		if(date.isPresent()) {
			model.addAttribute("listDiemDanh", bangDiemDanhService.findByNgayDiemDanh(date.get()));
		} else {
			model.addAttribute("listDiemDanh", bangDiemDanhService.findByNgayDiemDanh(LocalDate.now().toString()));
		}
		if(hoTen != null) {
			List<KhachHang> listKhachHang = khachHangService.listAll();
			List<KhachHang> listKhachHangFiltered = new ArrayList<KhachHang>();
			for (KhachHang item: listKhachHang) {
				if ((item.getHo() + item.getTen()).contains(hoTen)) {
					listKhachHangFiltered.add(item);
				}
			}
			model.addAttribute("hoTen", hoTen);
			model.addAttribute("listKhachHang", listKhachHangFiltered);
		} else {
			model.addAttribute("listKhachHang", khachHangService.listAll());;
		}
		model.addAttribute("errorMessage", errorMessage);
		model.addAttribute("today", date.isPresent() ? date.get() : LocalDate.now().toString());
		model.addAttribute("todayDateTime", LocalDateTime.now());
		model.addAttribute("bangDiemDanhService", bangDiemDanhService);
		return "check";
	}
	
	@RequestMapping(value="check", method = RequestMethod.POST)
	public String checkIn(HttpSession session, 
			RedirectAttributes redirectAttributes,
			@RequestParam("cardId") String maThe,
			@RequestParam("GoiTap") String goiTap, 
			@RequestParam("NhanVien") String maNhanVien) {
		String maGoiTap = goiTap.split(",")[0];
		String ngayDangKy = goiTap.split(",")[1];
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		ChiTietThe chiTietThe = chiTietTheService.findChiTietThe(maGoiTap, maThe, ngayDangKy);
		
		if(bangDiemDanhService.findBangDiemDanh(maGoiTap, maThe, ngayDangKy, today) != null) {
			redirectAttributes.addAttribute("errorMessage", "Hôm nay đã điểm danh rồi");
			return "redirect:check.htm";
		}
		
		BangDiemDanh bangDiemDanh = new BangDiemDanh();
		bangDiemDanh.setNgayDangKy(chiTietThe.getNgayDangKy().toString());
		bangDiemDanh.setGoiTap(chiTietThe.getGoiTap());
		bangDiemDanh.setNgayDiemDanh(today);
		bangDiemDanh.setNhanVien(nhanVienService.findByMaNV(maNhanVien));
		bangDiemDanh.setThe(chiTietThe.getThe());
		bangDiemDanhService.save(bangDiemDanh);
		
		return "redirect:check.htm";
	}
	
	@RequestMapping(value = "load-cards.htm")
	public String checkIn(ModelMap model, HttpSession session,
			@RequestParam("customerId") String maKH) {
		model.addAttribute("maKH", maKH);
		model.addAttribute("listThe", khachHangService.findByMaKH(maKH).getListThe());
		return "check";
	}
	
	@RequestMapping(value = "load-customers.htm")
	public String loadCustomers(HttpSession session, RedirectAttributes redirectAttributes,
			@RequestParam("customer-name") String hoTen) {
		redirectAttributes.addAttribute("hoTen", hoTen);
		return "redirect:check.htm";
	}
	
	@RequestMapping(value = "get-card.htm")
	public String getCard(HttpSession session, RedirectAttributes redirectAttributes,
			@RequestParam("MaThe") String maThe) {
		The the = theService.findByMaThe(maThe);
		if(the == null) {
			redirectAttributes.addAttribute("errorMessage", "Thẻ không tồn tại");
			return "redirect:check.htm";
		}
		if(the.getTrangThai() == 0) {
			redirectAttributes.addAttribute("errorMessage", "Thẻ đã bị khóa");
			return "redirect:check.htm";
		}
		return "redirect:check.htm?cardId=" + maThe;
	}
	
	@RequestMapping(value = "check-by-date.htm")
	public String checkByDate(ModelMap model, HttpSession session,
			@RequestParam("cardId") Optional<String> maThe,
			@RequestParam("date-to-check") String date) {
		return "redirect:check.htm?date=" + date;
	}
}
