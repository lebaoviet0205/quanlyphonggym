package com.project.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="chi_tiet_the")
public class ChiTietThe {
	@Id
	@Column(name="ngay_dang_ky", length = 26)
	private String NgayDangKy;
	
	@ManyToOne
	@JoinColumn(name="ma_goi_tap")
	private GoiTap goiTap;
	
	@ManyToOne
	@JoinColumn(name="ma_the")
	private The the;
	
	@ManyToOne
	@JoinColumn(name="ma_hd")
	private HoaDon hoaDon;
	
	@OneToMany(mappedBy = "the")
	private List<BangDiemDanh> listBangDiemDanh;

	public ChiTietThe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChiTietThe(String ngayDangKy, GoiTap goiTap, The the, HoaDon hoaDon) {
		super();
		NgayDangKy = ngayDangKy;
		this.goiTap = goiTap;
		this.the = the;
		this.hoaDon = hoaDon;
	}

	public LocalDateTime getNgayDangKy() {
		return LocalDateTime.parse(NgayDangKy.substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	public void setNgayDangKy(String ngayDangKy) {
		NgayDangKy = ngayDangKy;
	}

	public GoiTap getGoiTap() {
		return goiTap;
	}

	public void setGoiTap(GoiTap goiTap) {
		this.goiTap = goiTap;
	}

	public The getThe() {
		return the;
	}

	public void setThe(The the) {
		this.the = the;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
}
