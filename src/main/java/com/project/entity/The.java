package com.project.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="the")
public class The {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="ma_the")
	private String MaThe;
	@Column(name="ngay_tao")
	private Date NgayTao;
	@Column(name="trang_thai")
	private int TrangThai;
	
	@ManyToOne
	@JoinColumn(name="ma_kh")
	private KhachHang khachHang;
	
	@ManyToOne
	@JoinColumn(name="ma_nv")
	private NhanVien nhanVien;
	
	@OneToMany(mappedBy = "the", fetch=FetchType.EAGER)
	private List<ChiTietThe> listChiTietThe;

	public The() {
		super();
		// TODO Auto-generated constructor stub
	}

	public The(String maThe, Date ngayTao, int trangThai, KhachHang khachHang, NhanVien nhanVien,
			List<ChiTietThe> listChiTietThe) {
		super();
		MaThe = maThe;
		NgayTao = ngayTao;
		TrangThai = trangThai;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.listChiTietThe = listChiTietThe;
	}

	public String getMaThe() {
		return MaThe;
	}

	public void setMaThe(String maThe) {
		MaThe = maThe;
	}

	public Date getNgayTao() {
		return NgayTao;
	}

	public void setNgayTao(Date ngayTao) {
		NgayTao = ngayTao;
	}

	public int getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(int trangThai) {
		TrangThai = trangThai;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public List<ChiTietThe> getListChiTietThe() {
		return listChiTietThe;
	}

	public void setListChiTietThe(List<ChiTietThe> listChiTietThe) {
		this.listChiTietThe = listChiTietThe;
	}
}
