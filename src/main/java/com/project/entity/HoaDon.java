package com.project.entity;

import java.time.LocalDateTime;
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
import org.springframework.lang.Nullable;

@Entity
@Table(name="hoa_don")
public class HoaDon {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="ma_hd")
	private String MaHD;
	@Column(name="ngay_lap")
	private LocalDateTime NgayLap;
	
	@ManyToOne
	@JoinColumn(name="ma_nv")
	private NhanVien nhanVien;
	
	@OneToMany(mappedBy = "hoaDon", fetch = FetchType.EAGER)
	private List<ChiTietThe> listChiTietThe;

	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HoaDon(String maHD, LocalDateTime ngayLap, NhanVien nhanVien,
			List<ChiTietThe> listChiTietThe) {
		super();
		MaHD = maHD;
		NgayLap = ngayLap;
		this.nhanVien = nhanVien;
		this.listChiTietThe = listChiTietThe;
	}

	public String getMaHD() {
		return MaHD;
	}

	public void setMaHD(String maHD) {
		MaHD = maHD;
	}

	public LocalDateTime getNgayLap() {
		return NgayLap;
	}

	public void setNgayLap(LocalDateTime ngayLap) {
		NgayLap = ngayLap;
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
	
	public String getMaThe() {
		return listChiTietThe.get(listChiTietThe.size() - 1).getThe().getMaThe();
	}
	
	public String getFullName() {
		KhachHang khachHang = listChiTietThe.get(listChiTietThe.size() - 1).getThe().getKhachHang();
		return khachHang.getHo() + ' ' + khachHang.getTen();
	}
	
	public float getTotal() {
		float cost = 0;
		for (ChiTietThe item: listChiTietThe) {
			cost += item.getGoiTap().getPriceByDate(this.NgayLap);
		}
		return cost;
	}
}
