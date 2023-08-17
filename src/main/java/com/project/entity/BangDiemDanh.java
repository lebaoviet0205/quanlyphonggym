package com.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bang_diem_danh")
@IdClass(BangDiemDanhId.class)
public class BangDiemDanh {
	@Id
	@Column(name="ngay_diem_danh")
	private String NgayDiemDanh;
	@Column(name="ngay_dang_ky")
	private String NgayDangKy;
	
	@ManyToOne
	@JoinColumn(name="ma_the")
	private The the;
	
	@ManyToOne
	@JoinColumn(name="ma_nv")
	private NhanVien nhanVien;
	
	@ManyToOne
	@JoinColumn(name="ma_goi_tap")
	private GoiTap goiTap;

	public BangDiemDanh() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BangDiemDanh(String ngayDiemDanh, String ngayDangKy, The the, NhanVien nhanVien,
			GoiTap goiTap) {
		super();
		NgayDiemDanh = ngayDiemDanh;
		NgayDangKy = ngayDangKy;
		this.the = the;
		this.nhanVien = nhanVien;
		this.goiTap = goiTap;
	}

	public String getNgayDiemDanh() {
		return NgayDiemDanh;
	}

	public void setNgayDiemDanh(String ngayDiemDanh) {
		NgayDiemDanh = ngayDiemDanh;
	}

	public The getThe() {
		return the;
	}

	public void setThe(The the) {
		this.the = the;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public GoiTap getGoiTap() {
		return goiTap;
	}

	public void setGoiTap(GoiTap goiTap) {
		this.goiTap = goiTap;
	}

	public String getNgayDangKy() {
		return NgayDangKy;
	}

	public void setNgayDangKy(String ngayDangKy) {
		NgayDangKy = ngayDangKy;
	}
}
