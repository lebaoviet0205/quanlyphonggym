package com.project.controller;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.entity.HoaDon;
import com.project.entity.PhanQuyen;
import com.project.services.HoaDonService;

@Controller
public class StatisticController {
	@Autowired
	private HoaDonService hoaDonService;

	@RequestMapping(value = "statistic", method = RequestMethod.GET)
	public String statisticInterface(ModelMap model, HttpSession session) {
		LocalDate today = LocalDate.now();
		List<HoaDon> listHoaDon = hoaDonService.findFromDateToDate(
				LocalDateTime.of(today.getYear(), today.getMonth(), 1, 0, 0, 0), LocalDateTime.now());
		Float total = 0F;
		for (HoaDon item : listHoaDon) {
			total += item.getTotal();
		}

		model.addAttribute("from", LocalDate.of(today.getYear(), today.getMonth(), 1));
		model.addAttribute("to", today);
		model.addAttribute("listHoaDon", listHoaDon);
		model.addAttribute("total", total);
		return "statistic";
	}

	@RequestMapping(value = "statistic", method = RequestMethod.POST)
	public String statistic(ModelMap model, HttpSession session, @RequestParam("from") String from,
			@RequestParam("to") String to) {
		LocalDateTime fromDateTime = LocalDate.parse(from).atTime(LocalTime.MIN);
		LocalDateTime toDateTime = LocalDate.parse(to).atTime(LocalTime.MAX);

		List<HoaDon> listHoaDon = hoaDonService.findFromDateToDate(fromDateTime, toDateTime);
		Float total = 0F;
		for (HoaDon item : listHoaDon) {
			total += item.getTotal();
		}

		model.addAttribute("from", from);
		model.addAttribute("to", to);
		model.addAttribute("listHoaDon", listHoaDon);
		model.addAttribute("total", total);
		return "statistic";
	}
}
