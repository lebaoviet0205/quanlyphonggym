package com.project.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.entity.ChiTietThe;
import com.project.entity.GoiTap;
import com.project.entity.HoaDon;
import com.project.entity.KhachHang;
import com.project.entity.LopDichVu;
import com.project.entity.NhanVien;
import com.project.entity.The;
import com.project.services.BangDiemDanhService;
import com.project.services.ChiTietTheService;
import com.project.services.GoiTapService;
import com.project.services.HoaDonService;
import com.project.services.KhachHangService;
import com.project.services.LopDichVuService;
import com.project.services.TheService;

import net.bytebuddy.asm.Advice.Local;

@Controller
public class CustomerController {
	@Autowired
	private KhachHangService khachHangService;
	@Autowired
	private TheService theService;
	@Autowired
	private LopDichVuService lopDichVuService;
	@Autowired
	private GoiTapService goiTapService;
	@Autowired
	private HoaDonService hoaDonService;
	@Autowired
	private ChiTietTheService chiTietTheService;
	@Autowired
	private BangDiemDanhService bangDiemDanhService;  

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String registerInterface(ModelMap model, HttpSession session) {
		KhachHang khachHang = new KhachHang();
		khachHang.setAnh(null);
		model.addAttribute("khachHang", khachHang);
		model.addAttribute("successMessage", "");
		return "register";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(ModelMap model, HttpSession session,
			@RequestParam("profile") CommonsMultipartFile file,
			@ModelAttribute("khachHang") @Validated KhachHang khachHang, BindingResult errors) {
		if (!errors.hasErrors()) {
			boolean isValid = true;
			for (KhachHang item : khachHangService.listAll()) {
				if (item.getEmail().equals(khachHang.getEmail())) {
					model.addAttribute("emailError", "Email này đã có người sử dụng.");
					isValid = false;
				}
				if (item.getSDT().equals(khachHang.getSDT())) {
					model.addAttribute("sdtError", "Số điện thoại này đã có người sử dụng.");
					isValid = false;
				}
			}
			if (!isValid) {
				return "register";
			}
			
			if (!file.isEmpty()) {
				byte[] data = file.getBytes();
				String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
				String path = session.getServletContext().getRealPath("/") + "files" + File.separator + khachHang.getSDT() + "_0" + fileExtension;
				
				try {
					FileOutputStream fos = new FileOutputStream(path);
					fos.write(data);
					fos.close();
					khachHang.setAnh("/files/" + khachHang.getSDT() + "_0" + fileExtension);
					System.out.println(path);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			
			The the = new The();
			NhanVien nhanVien = (NhanVien) session.getAttribute("currentEmployee");
			the.setKhachHang(khachHang);
			the.setNhanVien(nhanVien);
			the.setNgayTao(Date.valueOf(LocalDate.now()));
			the.setTrangThai(1);
			the.setListChiTietThe(new ArrayList<ChiTietThe>());
			List<The> listThe = new ArrayList<The>();
			listThe.add(the);
			khachHang.setListThe(listThe);
			khachHang.setNgayDangKy(LocalDate.now());
			khachHangService.save(khachHang);
			theService.save(the);
			model.addAttribute("successMessage", "Thêm khách hàng thành công");
		} else  {
			System.out.println("Co loi");
		}
		return "register";
	}

	@RequestMapping("customer")
	public String customer(ModelMap model, HttpSession session) {
		model.addAttribute("listCustomers", khachHangService.listAll());
		return "customer";
	}

	@RequestMapping(value = "search-customer", method = RequestMethod.POST)
	public String searchCustomer(ModelMap model, HttpSession session, @RequestParam("search") String search) {
		List<KhachHang> listKH = khachHangService.listAll();
		List<KhachHang> listCustomers = new ArrayList<KhachHang>();
		for (KhachHang item : listKH) {
			if (item.getFullname().contains(search)) {
				listCustomers.add(item);
			}
		}
		model.addAttribute("search", search);
		model.addAttribute("listCustomers", listCustomers);
		return "customer";
	}

	@RequestMapping(value = "search-card", method = RequestMethod.POST)
	public String searchCard(ModelMap model, HttpSession session, @RequestParam("search") String search) {
		List<The> listThe = theService.listAll();
		List<The> listTheFiltered = new ArrayList<The>();
		for (The item : listThe) {
			if (item.getKhachHang().getFullname().contains(search)) {
				listTheFiltered.add(item);
			}
		}
		model.addAttribute("search", search);
		model.addAttribute("listThe", listTheFiltered);
		return "cards";
	}

	@RequestMapping("cards")
	public String cards(ModelMap model, HttpSession session) {
		model.addAttribute("listThe", theService.listAll());
		return "cards";
	}

	@RequestMapping("list-card")
	public String listCard(ModelMap model, HttpSession session, @RequestParam("id") String maKH) {
		model.addAttribute("listThe", khachHangService.findByMaKH(maKH).getListThe());
		model.addAttribute("id", maKH);
		return "listCard";
	}

	@RequestMapping("add-card")
	public String addCard(ModelMap model, HttpSession session, @RequestParam("id") String maKH) {
		KhachHang khachHang = khachHangService.findByMaKH(maKH);
		List<The> listThe = khachHang.getListThe();

		The the = new The();
		NhanVien nhanVien = (NhanVien) session.getAttribute("currentEmployee");
		the.setKhachHang(khachHang);
		the.setNhanVien(nhanVien);
		the.setNgayTao(Date.valueOf(LocalDate.now()));
		the.setTrangThai(1);
		the.setListChiTietThe(new ArrayList<ChiTietThe>());
		listThe.add(the);
		theService.save(the);
		khachHangService.save(khachHang);
		return "redirect:list-card.htm?id=" + maKH;
	}

	@RequestMapping(value = "card-detail", method = RequestMethod.GET)
	public String cardDetail(ModelMap model, HttpSession session, @RequestParam("id") String maThe,
			@ModelAttribute("errorMessage") String errorMessage) {
		List<ChiTietThe> listChiTietThe = new ArrayList<ChiTietThe>();

		for (ChiTietThe item : chiTietTheService.listAll()) {
			if (item.getThe().getMaThe().equals(maThe)) {
				listChiTietThe.add(item);
			}
		}

		model.addAttribute("the", theService.findByMaThe(maThe));
		model.addAttribute("listChiTietThe", listChiTietThe);
		model.addAttribute("today", LocalDateTime.now());
		model.addAttribute("errorAttribute", errorMessage);	
		model.addAttribute("bangDiemDanhService", bangDiemDanhService);
		return "cardDetail";
	}

	@RequestMapping(value = "load-package.htm", method = RequestMethod.POST)
	public String loadPackage(HttpSession session, @RequestParam("id") String id, @RequestParam("MaLop") String maLop,
			RedirectAttributes attributes) {
		attributes.addAttribute("maLop", maLop);
		return "redirect:card.htm?id=" + id;
	}

	@RequestMapping(value = "add-package-to-cart.htm", method = RequestMethod.POST)
	public String addPackageToCart(HttpSession session, RedirectAttributes redirectAttributes,
			@RequestParam("id") String id, @RequestParam("GoiTap") String maGoiTap) {
		List<ChiTietThe> listCTT = (List<ChiTietThe>) session.getAttribute("listCTT" + id);
		
		for (ChiTietThe ctt : listCTT) {
			if (ctt.getGoiTap().getMaGoiTap().equals(maGoiTap)) {
				redirectAttributes.addAttribute("addPackageError", "Gói tập đã tồn tại");
				return "redirect:card.htm?id=" + id;
			}
		}
		
		List<ChiTietThe> listChiTiet = chiTietTheService.findListChiTietThe(maGoiTap, id);
		if (listChiTiet.size() > 0) {
			redirectAttributes.addAttribute("addPackageError", "Thẻ này đã đăng ký gói tập này");
			return "redirect:card.htm?id=" + id;
		}

		GoiTap goiTap = goiTapService.findByMaGoiTap(maGoiTap);
		ChiTietThe chiTietThe = new ChiTietThe();
		chiTietThe.setGoiTap(goiTap);
		chiTietThe.setHoaDon(null);
		chiTietThe.setNgayDangKy(
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).substring(0, 19));
		chiTietThe.setThe(theService.findByMaThe(id));

		listCTT.add(chiTietThe);
		return "redirect:card.htm?id=" + id;
	}

	@RequestMapping(value = "remove-package-from-cart.htm", method = RequestMethod.GET)
	public String removePackageFromCart(HttpSession session, @RequestParam("id") String id,
			@RequestParam("packageId") String packageId) {
		List<ChiTietThe> listCTT = (List<ChiTietThe>) session.getAttribute("listCTT" + id);
		for (int i = 0; i < listCTT.size(); i++) {
			if (listCTT.get(i).getGoiTap().getMaGoiTap().equals(packageId)) {
				listCTT.remove(i);
			}
		}

		return "redirect:card.htm?id=" + id;
	}

	@RequestMapping(value = "card.htm", method = RequestMethod.GET)
	public String card(ModelMap model, HttpSession session, @RequestParam("id") String maThe,
			@ModelAttribute("maLop") String maLop, @ModelAttribute("addPackageError") String addPackageError) {
		if (session.getAttribute("listCTT" + maThe) == null) {
			session.setAttribute("listCTT" + maThe, new ArrayList<ChiTietThe>());
		}
		KhachHang khachHang = theService.findByMaThe(maThe).getKhachHang();
		model.addAttribute("id", maThe);
		model.addAttribute(khachHang);
		model.addAttribute("listLop", lopDichVuService.listAll());
		model.addAttribute("lopDichVu", new LopDichVu());
		model.addAttribute("chiTietThe", new ChiTietThe());
		model.addAttribute("listCTT", session.getAttribute("listCTT" + maThe));
		model.addAttribute("addPackageError", addPackageError);

		if (maLop != null) {
			for (LopDichVu lop : lopDichVuService.listAll()) {
				if (lop.getMaLop().equals(maLop)) {
					model.addAttribute("lop", lop);
					break;
				}
			}
		}
		return "card";
	}

	@RequestMapping(value = "card.htm", method = RequestMethod.POST)
	public String addChiTietThe(HttpSession session, @RequestParam("id") String id) {
		List<ChiTietThe> listCTT = (List<ChiTietThe>) session.getAttribute("listCTT" + id);
		The the = theService.findByMaThe(id);
		the.setListChiTietThe(listCTT);

		session.removeAttribute("listCTT" + id);
		session.setAttribute("unsavedThe", the);

		return "redirect:bill-detail.htm?id=new-bill";
	}

	@RequestMapping(value = "edit-customer", method = RequestMethod.GET)
	public String editCustomerInterface(ModelMap model, HttpSession session, @RequestParam("id") String maKH) {
		model.addAttribute("khachHang", khachHangService.findByMaKH(maKH));
		return "editCustomer";
	}

	@RequestMapping(value = "edit-customer", method = RequestMethod.POST)
	public String editCustomer(ModelMap model, HttpSession session, @RequestParam("id") String maKH,
			@RequestParam("profile") CommonsMultipartFile file,
			@ModelAttribute("khachHang") @Validated KhachHang khachHang, BindingResult errors) {
		if (!errors.hasErrors()) {
			boolean isValid = true;
			for (KhachHang item : khachHangService.listAll()) {
				if (item.getMaKH().equals(maKH)) {
					continue;
				}

				if (item.getEmail().equals(khachHang.getEmail())) {
					model.addAttribute("emailError", "Email này đã có người sử dụng.");
					isValid = false;
				}
				if (item.getSDT().equals(khachHang.getSDT())) {
					model.addAttribute("sdtError", "Số điện thoại này đã có người sử dụng.");
					isValid = false;
				}
			}
			if (!isValid) {
				khachHang.setMaKH(maKH);
				model.addAttribute("khachHang", khachHang);
				return "editCustomer";
			}
			
			KhachHang kh = khachHangService.findByMaKH(maKH);	
			
			if (!file.isEmpty()) {	
				byte[] data = file.getBytes();
				String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
				String path = "";
				int count = 0;
				
				if (kh.getAnh() != null) {
					count = Integer.valueOf(kh.getAnh().substring(kh.getAnh().lastIndexOf('_') + 1, kh.getAnh().lastIndexOf('.')));
					count = count + 1;
				}
				
				path = session.getServletContext().getRealPath("/") + "files" + File.separator + khachHang.getSDT() + "_" + count + fileExtension;
				try {
					FileOutputStream fos = new FileOutputStream(path);
					fos.write(data);
					fos.close();
					kh.setAnh("/files/" + khachHang.getSDT() + "_" + count + fileExtension);
					System.out.println(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			kh.setHo(khachHang.getHo());
			kh.setTen(khachHang.getTen());
			kh.setSDT(khachHang.getSDT());
			kh.setNgaySinh(khachHang.getNgaySinh());
			kh.setGioiTinh(khachHang.getGioiTinh());
			kh.setEmail(khachHang.getEmail());
			kh.setDiaChi(khachHang.getDiaChi());
			kh.setGhiChu(khachHang.getGhiChu());
			khachHangService.save(kh);
		} else {
			khachHang.setMaKH(maKH);
			model.addAttribute("khachHang", khachHang);
			return "editCustomer";
		}
		return "redirect:customer.htm";
	}

	@RequestMapping("change-card-status")
	public String changeCardStatus(HttpSession session, @RequestParam("id") String maKH,
			@RequestParam("cardId") String maThe) {
		The the = theService.findByMaThe(maThe);
		the.setTrangThai(the.getTrangThai() == 1 ? 0 : 1);
		theService.save(the);

		if (maKH.equals("")) {
			return "redirect:cards.htm";
		}

		return "redirect:list-card.htm?id=" + maKH;
	}

	@RequestMapping("extend-package")
	public String extendPackage(ModelMap model, RedirectAttributes redirectAttributes, HttpSession session,
			@RequestParam("cardId") String maThe, @RequestParam("packageId") String maGoiTap) {
		List<ChiTietThe> listChiTietThe = chiTietTheService.findListChiTietThe(maGoiTap, maThe);

		for (ChiTietThe item : listChiTietThe) {
			if (item.getNgayDangKy().plusDays(item.getGoiTap().getThoiHan()).isAfter(LocalDateTime.now()) 
					&&  bangDiemDanhService.countDiemDanh(item.getGoiTap().getMaGoiTap(), item.getThe().getMaThe(), item.getNgayDangKy().toString()) < item.getGoiTap().getSoBuoiTap()) {
				redirectAttributes.addAttribute("errorMessage", "Gói tập này đã được gia hạn.");
				return "redirect:card-detail.htm?id=" + maThe;
			}
		}

		The unsavedThe = theService.findByMaThe(maThe);
		ChiTietThe chiTietThe = new ChiTietThe();
		chiTietThe.setGoiTap(goiTapService.findByMaGoiTap(maGoiTap));
		chiTietThe.setNgayDangKy(
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).substring(0, 19));
		chiTietThe.setHoaDon(null);
		chiTietThe.setThe(unsavedThe);
		List<ChiTietThe> listCTT = new ArrayList<ChiTietThe>();
		listCTT.add(chiTietThe);
		unsavedThe.setListChiTietThe(listCTT);
		session.setAttribute("unsavedThe", unsavedThe);

		return "redirect:bill-detail.htm?id=new-bill";
	}
}
