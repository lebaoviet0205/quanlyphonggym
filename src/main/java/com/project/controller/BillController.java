package com.project.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.entity.ChiTietThe;
import com.project.entity.HoaDon;
import com.project.entity.NhanVien;
import com.project.entity.The;
import com.project.services.ChiTietTheService;
import com.project.services.HoaDonService;
import com.project.services.TheService;

@Controller
public class BillController {
	@Autowired
	private HoaDonService hoaDonService;
	@Autowired 
	private ChiTietTheService chiTietTheService;
	@Autowired 
	private TheService theService;
	
	@RequestMapping("bill")
	public String bill(ModelMap model, HttpSession session) {
		List<HoaDon> listHoaDon = hoaDonService.listAll();
		model.addAttribute("listHoaDon", listHoaDon);
		return "bill";
	}
	
	@RequestMapping(value = "search-bill", method=RequestMethod.POST)
	public String searchBill(ModelMap model, HttpSession session,
			@RequestParam("search") String search) {
		List<HoaDon> listHoaDon = hoaDonService.listAll();
		List<HoaDon> listHoaDonFiltered = new ArrayList<HoaDon>();
		
		for(HoaDon item: listHoaDon) {
			if(item.getFullName().contains(search)) {
				listHoaDonFiltered.add(item);
			}
		}
		
		model.addAttribute("search", search);
		model.addAttribute("listHoaDon", listHoaDonFiltered);
		return "bill";
	}
	
	@RequestMapping(value="bill-detail", method = RequestMethod.GET)
	public String billDetailInterface(ModelMap model, HttpSession session,
			@RequestParam("id") String id) {
		if (id.equals("new-bill")) {
			The unsavedThe = (The) session.getAttribute("unsavedThe");
			HoaDon hoaDon = new HoaDon();
			hoaDon.setMaHD(id);
			hoaDon.setNgayLap(LocalDateTime.now());
			hoaDon.setNhanVien((NhanVien) session.getAttribute("currentEmployee"));
			hoaDon.setListChiTietThe(unsavedThe.getListChiTietThe());
			model.addAttribute("hoaDon", hoaDon);
			model.addAttribute("id", id);
		} else {
			HoaDon hoaDon = hoaDonService.findByMaHD(id);
			model.addAttribute("hoaDon", hoaDon);
			model.addAttribute("id", id);
		}
		return "billDetail";
	}
	
	@RequestMapping(value="bill-detail", method = RequestMethod.POST)
	public String billDetail(ModelMap model, HttpSession session) {
		The unsavedThe = (The) session.getAttribute("unsavedThe");
		
		HoaDon hoaDon = new HoaDon();
		hoaDon.setNgayLap(LocalDateTime.now());
		hoaDon.setNhanVien((NhanVien) session.getAttribute("currentEmployee"));
		hoaDon.setListChiTietThe(unsavedThe.getListChiTietThe());
		hoaDonService.save(hoaDon);
		
		for(ChiTietThe item: hoaDon.getListChiTietThe()) {
			if(item.getHoaDon() == null) {
				item.setHoaDon(hoaDon);
				chiTietTheService.save(item);
			}
		}
		
		theService.save(unsavedThe);
		
		return "redirect:bill.htm";
	}
}
