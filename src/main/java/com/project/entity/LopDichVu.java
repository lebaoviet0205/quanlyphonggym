package com.project.entity;

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
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="lop_dv")
public class LopDichVu {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="ma_lop")
	private String MaLop;
	@Column(name="ten_lop")
	@NotBlank(message="Vui lòng nhập tên lớp")
	private String TenLop;
	
	@ManyToOne
	@JoinColumn(name="ma_nguoi_quan_ly")
	private NhanVien nguoiQuanLyLDV;
	
	@OneToMany(mappedBy = "lopDichVu", fetch=FetchType.EAGER)
	private List<GoiTap> listGoiTap;
	
	public LopDichVu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LopDichVu(String maLop, String tenLop, NhanVien nguoiQuanLyLDV, List<GoiTap> listGoiTap) {
		super();
		MaLop = maLop;
		TenLop = tenLop;
		this.nguoiQuanLyLDV = nguoiQuanLyLDV;
		this.listGoiTap = listGoiTap;
	}

	public String getMaLop() {
		return MaLop;
	}

	public void setMaLop(String maLop) {
		MaLop = maLop;
	}

	public String getTenLop() {
		return TenLop;
	}

	public void setTenLop(String tenLop) {
		TenLop = tenLop;
	}

	public NhanVien getNguoiQuanLyLDV() {
		return nguoiQuanLyLDV;
	}

	public void setNguoiQuanLyLDV(NhanVien nguoiQuanLyLDV) {
		this.nguoiQuanLyLDV = nguoiQuanLyLDV;
	}

	public List<GoiTap> getListGoiTap() {
		return listGoiTap;
	}

	public void setListGoiTap(List<GoiTap> listGoiTap) {
		this.listGoiTap = listGoiTap;
	}
}
