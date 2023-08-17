package com.project.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="gia_goi_tap")
public class GiaGoiTap {
	@Id
	@Column(name="ngay_cap_nhat", length = 26)
	private String NgayCapNhat;
	@Column(name="gia")
	private Float Gia;
	
	@ManyToOne
	@JoinColumn(name="ma_nguoi_quan_ly")
	private NhanVien nguoiQuanLyGGT;
	
	@ManyToOne
	@JoinColumn(name="ma_goi_tap")
	private GoiTap goiTap;

	public GiaGoiTap() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GiaGoiTap(Float gia, String ngayCapNhat, NhanVien nguoiQuanLyGGT, GoiTap goiTap) {
		super();
		Gia = gia;
		NgayCapNhat = ngayCapNhat;
		this.nguoiQuanLyGGT = nguoiQuanLyGGT;
		this.goiTap = goiTap;
	}

	public Float getGia() {
		return Gia;
	}

	public void setGia(Float gia) {
		Gia = gia;
	}

	public String getNgayCapNhat() {
		return NgayCapNhat;
	}

	public void setNgayCapNhat(String ngayCapNhat) {
		NgayCapNhat = ngayCapNhat;
	}

	public NhanVien getNguoiQuanLyGGT() {
		return nguoiQuanLyGGT;
	}

	public void setNguoiQuanLyGGT(NhanVien nguoiQuanLyGGT) {
		this.nguoiQuanLyGGT = nguoiQuanLyGGT;
	}

	public GoiTap getGoiTap() {
		return goiTap;
	}

	public void setGoiTap(GoiTap goiTap) {
		this.goiTap = goiTap;
	}
}
