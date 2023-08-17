package com.project.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.entity.HoaDon;
import com.project.entity.KhachHang;
import com.project.entity.PhanQuyen;
import com.project.services.HoaDonService;
import com.project.services.KhachHangService;

@Controller
public class DashboardController {
	@Autowired
	private HoaDonService hoaDonService;
	@Autowired
	private KhachHangService khachHangService;
	
	@RequestMapping("dashboard")
	public String dashboard(ModelMap model, HttpSession session) {
		float todayRevenue = 0f;
		float thisMonthRevenue = 0f;
		int todayNewCustomer = 0;
		int thisMonthNewCustomer = 0;
		
		LocalDateTime today = LocalDateTime.now();
		List<HoaDon> listHoaDonToday = hoaDonService.findFromDateToDate(LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 0, 0, 0), today);
		for (HoaDon item: listHoaDonToday) {
			todayRevenue += item.getTotal();
		}
		
		List<HoaDon> listHoaDonThisMonth = hoaDonService.findFromDateToDate(LocalDateTime.of(today.getYear(), today.getMonth(), 1, 0, 0, 0), today);
		for (HoaDon item: listHoaDonThisMonth) {
			thisMonthRevenue += item.getTotal();
		}
		
		List<KhachHang> listKhachHangToday = khachHangService.findFromDateToDate(LocalDate.now(), LocalDate.now());
		for (KhachHang item: listKhachHangToday) {
			todayNewCustomer += 1;
		}
		
		List<KhachHang> listKhachHangThisMonth = khachHangService.findFromDateToDate(LocalDate.of(today.getYear(), today.getMonth(), 1), LocalDate.now());
		for (KhachHang item: listKhachHangThisMonth) {
			thisMonthNewCustomer += 1;
		}
		
		JSONArray listOfMonth = new JSONArray();
		for(int i = 1; i <= today.getMonthValue(); i++) {
			listOfMonth.put("Tháng " + i);
		}
		
		JSONArray listOfNewCustomerPerMonth = new JSONArray();
		for(int i = 1; i <= today.getMonthValue(); i++) {
			int newCustomer = 0;
			YearMonth yearMonth = YearMonth.of(today.getYear(), i);
			List<KhachHang> listKH = khachHangService.findFromDateToDate(LocalDate.of(today.getYear(), i, 1), LocalDate.of(today.getYear(), i, yearMonth.lengthOfMonth()));
			for(KhachHang item: listKH) {
				newCustomer += 1;
			}
			listOfNewCustomerPerMonth.put(newCustomer);
		}
		
		JSONArray listOfRevenuePerMonth = new JSONArray();
		for(int i = 1; i <= today.getMonthValue(); i++) {
			int revenue = 0;
			YearMonth yearMonth = YearMonth.of(today.getYear(), i);
			List<HoaDon> listHD = hoaDonService.findFromDateToDate(LocalDateTime.of(today.getYear(), i, 1, 0, 0, 0), LocalDateTime.of(today.getYear(), i, yearMonth.lengthOfMonth(), 11, 59, 59));
			for(HoaDon item: listHD) {
				revenue += item.getTotal();
			}
			listOfRevenuePerMonth.put(revenue);
		}
		
		model.addAttribute("todayRevenue", todayRevenue);
		model.addAttribute("thisMonthRevenue", thisMonthRevenue);
		model.addAttribute("todayNewCustomer", todayNewCustomer);
		model.addAttribute("thisMonthNewCustomer", thisMonthNewCustomer);
		model.addAttribute("listOfMonth", listOfMonth);
		model.addAttribute("listOfNewCustomerPerMonth", listOfNewCustomerPerMonth);
		model.addAttribute("listOfRevenuePerMonth", listOfRevenuePerMonth);
		model.addAttribute("listHoaDonThisMonth", listHoaDonThisMonth);
		
		return "dashboard";
	}
}
