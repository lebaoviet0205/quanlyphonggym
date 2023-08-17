package com.project.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.entity.ChiTietThe;
import com.project.entity.GiaGoiTap;
import com.project.entity.GoiTap;
import com.project.entity.LopDichVu;
import com.project.entity.NhanVien;
import com.project.entity.PhanQuyen;
import com.project.services.GiaGoiTapService;
import com.project.services.GoiTapService;
import com.project.services.LopDichVuService;

@Controller
public class ClassController {
	@Autowired
	private LopDichVuService lopDichVuService;
	@Autowired
	private GiaGoiTapService giaGoiTapService;
	@Autowired
	private GoiTapService goiTapService;
	
	@RequestMapping("dashboard-class")
	public String dashboardClass(ModelMap model, HttpSession session) {
		model.addAttribute("listLop", lopDichVuService.listAll());
		return "dashboardClass";
	}
	
	@RequestMapping(value="search-class", method = RequestMethod.POST)
	public String searchClass(ModelMap model, HttpSession session,
			@RequestParam("search") String search) {
		List<LopDichVu> listLop = lopDichVuService.listAll();
		List<LopDichVu> listLopFiltered = new ArrayList<LopDichVu>();
		
		for(LopDichVu item: listLop) {
			if (item.getTenLop().contains(search)) {
				listLopFiltered.add(item);
			}
		}
		
		model.addAttribute("search", search);
		model.addAttribute("listLop", listLopFiltered);
		return "dashboardClass";
	}
	
	@RequestMapping(value = "new-class", method = RequestMethod.GET)
	public String addClassInterfce(ModelMap model, HttpSession session) {
		LopDichVu lopDichVu = new LopDichVu();
		model.addAttribute("className", "Lớp dịch vụ mới");
		model.addAttribute("lopDichVu", lopDichVu);
		return "classDetail";
	}
	
	@RequestMapping(value = "new-class", method = RequestMethod.POST)
	public String addClass(ModelMap model, HttpSession session,
			@ModelAttribute("lopDichVu") @Validated LopDichVu lopDichVu, BindingResult errors) {
		if(!errors.hasErrors()) {
			lopDichVu.setNguoiQuanLyLDV((NhanVien) session.getAttribute("currentEmployee"));
			lopDichVu.setListGoiTap(new ArrayList<GoiTap>());
			lopDichVuService.save(lopDichVu);
			return "redirect:edit-class.htm?id=" + lopDichVu.getMaLop();
		}
		return "classDetail";
	}
	
	@RequestMapping(value = "edit-class", method = RequestMethod.GET)
	public String editClass(ModelMap model, HttpSession session, 
			@ModelAttribute("tenGoiTapError") String tenGoiTapError, 
			@ModelAttribute("giaError") String giaError, 
			@ModelAttribute("thoiHanError") String thoiHanError, 
			@ModelAttribute("soHocVienError") String soHocVienError, 
			@ModelAttribute("soBuoiTapError") String soBuoiTapError,
			@ModelAttribute("tenGoiTap") String tenGoiTap, 
			@ModelAttribute("gia") String gia, 
			@ModelAttribute("thoiHan") String thoiHan,
			@ModelAttribute("soHocVien") String soHocVien,
			@ModelAttribute("soBuoiTap") String soBuoiTap,
			@RequestParam("id") String id) {
		LopDichVu lopDichVu = lopDichVuService.findByClassId(id);
		model.addAttribute("className", lopDichVu.getTenLop());
		model.addAttribute("lopDichVu", lopDichVu);
		model.addAttribute("id", id);
		model.addAttribute("listGoiTap", lopDichVu.getListGoiTap());
		model.addAttribute("tenGoiTapError", tenGoiTapError);
		model.addAttribute("giaError", giaError);
		model.addAttribute("thoiHanError", thoiHanError);
		model.addAttribute("soHocVienError", soHocVienError);
		model.addAttribute("soBuoiTapError", soBuoiTapError);
		model.addAttribute("tenGoiTap", tenGoiTap);
		model.addAttribute("gia", gia);
		model.addAttribute("thoiHan", thoiHan);
		model.addAttribute("soHocVien", soHocVien);
		model.addAttribute("soBuoiTap", soBuoiTap);
		return "classDetail";
	}
	
	@RequestMapping(value = "edit-class", method = RequestMethod.POST)
	public String editClass(ModelMap model, HttpSession session,
			@ModelAttribute("lopDichVu") @Validated LopDichVu lopDichVu, BindingResult errors) {
		if(!errors.hasErrors()) {
			LopDichVu lop = lopDichVuService.findByClassId(lopDichVu.getMaLop());
			lop.setTenLop(lopDichVu.getTenLop());
			lopDichVuService.save(lop);
			return "redirect:edit-class.htm?id=" + lopDichVu.getMaLop();
		}
		model.addAttribute("id", lopDichVu.getMaLop());
		return "classDetail";
	}
	
	@RequestMapping(value = "add-package", method = RequestMethod.POST)
	public String addPackage(ModelMap model, HttpSession session, RedirectAttributes redirectAttributes,
			@RequestParam("id") String id, 
			@RequestParam("TenGoiTap") String tenGoiTap,
			@RequestParam("Gia") String gia,
			@RequestParam("SoBuoiTap") String soBuoiTap,
			@RequestParam("ThoiHan") String thoiHan,
			@RequestParam("SoHocVien") String soHocVien,
			@ModelAttribute("lopDichVu") @Validated LopDichVu lopDichVu, BindingResult errors) {
		Map<String, String> myErrors = new HashMap<String, String>();
		if (tenGoiTap.isBlank()) {
			myErrors.put("tenGoiTapError", "Vui lòng nhập tên gói tập");
		}
		
		if(gia.isBlank()) {
			myErrors.put("giaError", "Vui lòng nhập giá");
		} else if (Float.valueOf(gia) < 0) {
			myErrors.put("giaError", "Giá gói tập phải lớn hơn 0");
		}
		
		if(soBuoiTap.isBlank()) {
			myErrors.put("soBuoiTapError", "Vui lòng nhập số buổi tập");
		} else if (Integer.valueOf(soBuoiTap) <= 0) {
			myErrors.put("soBuoiTapError", "Số buổi tập gói tập phải lớn hơn 0");
		}
		
		if(thoiHan.isBlank()) {
			myErrors.put("thoiHanError", "Vui lòng nhập thời hạn");
		} else if (Integer.valueOf(thoiHan) <= 0) {
			myErrors.put("thoiHanError", "Thời hạn của gói tập phải lớn hơn 0");
		}
		
		if(soHocVien.isBlank()) {
			myErrors.put("soHocVienError", "Vui lòng nhập số học viên");
		} else if (Integer.valueOf(soHocVien) <= 0) {
			myErrors.put("soHocVienError", "Số học viên phải lớn hơn 0");
		}
		
		if(myErrors.isEmpty()) {
			GoiTap goiTap = new GoiTap();
			goiTap.setTenGoiTap(tenGoiTap);
			goiTap.setSoBuoiTap(Integer.valueOf(soBuoiTap));
			goiTap.setThoiHan(Integer.valueOf(thoiHan));
			goiTap.setSoHocVien(Integer.valueOf(soHocVien));
			goiTap.setTrangThai(1);
			goiTap.setLopDichVu(lopDichVuService.findByClassId(id));
			goiTap.setNguoiQuanLyGT((NhanVien)session.getAttribute("currentEmployee"));
			
			GiaGoiTap giaGoiTap = new GiaGoiTap();
			giaGoiTap.setGia(Float.valueOf(gia));
			giaGoiTap.setGoiTap(goiTap);
			giaGoiTap.setNgayCapNhat(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			giaGoiTap.setNguoiQuanLyGGT((NhanVien)session.getAttribute("currentEmployee"));
			
			List<GiaGoiTap> listGGT = new ArrayList<GiaGoiTap>();
			goiTap.setListGiaGoiTap(listGGT);
			goiTap.setListChiTietThe(new ArrayList<ChiTietThe>());
			
			goiTapService.save(goiTap);		
			giaGoiTapService.save(giaGoiTap);
			
			return "redirect:edit-class.htm?id=" + id;
		}
		redirectAttributes.addAttribute("tenGoiTapError", myErrors.get("tenGoiTapError"));
		redirectAttributes.addAttribute("giaError", myErrors.get("giaError"));
		redirectAttributes.addAttribute("thoiHanError", myErrors.get("thoiHanError"));
		redirectAttributes.addAttribute("soHocVienError", myErrors.get("soHocVienError"));
		redirectAttributes.addAttribute("soBuoiTapError", myErrors.get("soBuoiTapError"));
		
		if (myErrors.get("tenGoiTapError") != null 
				|| myErrors.get("giaError") != null 
				|| myErrors.get("thoiHanError") != null
				|| myErrors.get("soHocVienError") != null
				|| myErrors.get("soBuoiTapError") != null) {
			redirectAttributes.addAttribute("tenGoiTap", tenGoiTap);
			redirectAttributes.addAttribute("gia", gia);
			redirectAttributes.addAttribute("thoiHan", thoiHan);
			redirectAttributes.addAttribute("soHocVien", soHocVien);
			redirectAttributes.addAttribute("soBuoiTap", soBuoiTap);
		}
		return "redirect:edit-class.htm?id=" + id;
	}
	
	@RequestMapping(value = "edit-package", method = RequestMethod.GET)
	public String editPackageInterface(ModelMap model, HttpSession session, 
			@RequestParam("id") String id, @RequestParam("package") String packageId) {
		LopDichVu lopDichVu = lopDichVuService.findByClassId(id);
		model.addAttribute("className", lopDichVu.getTenLop());
		model.addAttribute("lopDichVu", lopDichVu);
		model.addAttribute("id", id);
		model.addAttribute("listGoiTap", lopDichVu.getListGoiTap());
		model.addAttribute("goiTap", goiTapService.findByMaGoiTap(packageId));
		return "classDetail";
	}
	
	@RequestMapping(value = "edit-package", method = RequestMethod.POST)
	public String editPackage(ModelMap model, HttpSession session,
			@RequestParam("id") String id,
			@RequestParam("MaGoiTap") String maGoiTap,
			@RequestParam("SoBuoiTap") int soBuoiTap,
			@RequestParam("ThoiHan") int thoiHan,
			@RequestParam("Gia") float gia, 
			@RequestParam("SoHocVien") int soHocVien) {
		GoiTap goiTap = goiTapService.findByMaGoiTap(maGoiTap);
		GiaGoiTap giaGoiTap = new GiaGoiTap();
		giaGoiTap.setGoiTap(goiTap);
		giaGoiTap.setGia(gia);
		giaGoiTap.setNgayCapNhat(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		giaGoiTap.setNguoiQuanLyGGT((NhanVien) session.getAttribute("currentEmployee"));
		giaGoiTapService.save(giaGoiTap);
		
		goiTap.setSoHocVien(soHocVien);
		goiTap.setSoBuoiTap(soBuoiTap);
		goiTap.setThoiHan(thoiHan);
		goiTapService.save(goiTap);
		
		return "redirect:edit-class.htm?id=" + id;
	}
	
	@RequestMapping(value = "change-package-status")
	public String changePackageStatus(ModelMap model, HttpSession session,
			@RequestParam("id") String id,
			@RequestParam("package") String packageId) {
		GoiTap goiTap = goiTapService.findByMaGoiTap(packageId);
		goiTap.setTrangThai(goiTap.getTrangThai() == 1 ? 0 : 1);
		goiTapService.save(goiTap);
		return "redirect:edit-class.htm?id=" + id;
	}
}
