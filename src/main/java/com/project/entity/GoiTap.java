package com.project.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="goi_tap")
public class GoiTap {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="ma_goi_tap")
	private String MaGoiTap;
	@Column(name="ten_goi_tap")
	private String TenGoiTap;
	@Column(name="thoi_han")
	private int ThoiHan;
	@Column(name="trang_thai")
	private int TrangThai;
	@Column(name="so_hoc_vien")
	private int SoHocVien;
	@Column(name="so_buoi_tap")
	private int SoBuoiTap;
	
	@ManyToOne
	@JoinColumn(name="ma_lop")
	private LopDichVu lopDichVu;
	
	@ManyToOne
	@JoinColumn(name="ma_nguoi_quan_ly")
	private NhanVien nguoiQuanLyGT;
	
	@OneToMany(mappedBy = "goiTap", fetch = FetchType.EAGER)
	private List<ChiTietThe> listChiTietThe;
	
	@OneToMany(mappedBy = "goiTap", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<GiaGoiTap> listGiaGoiTap;

	public GoiTap() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GoiTap(String maGoiTap, String tenGoiTap, int ThoiHan, int trangThai, int soHocVien, int soBuoiTap, LopDichVu lopDichVu,
			NhanVien nguoiQuanLyGT, List<ChiTietThe> listChiTietThe, List<GiaGoiTap> listGiaGoiTap) {
		super();
		MaGoiTap = maGoiTap;
		TenGoiTap = tenGoiTap;
		this.ThoiHan = ThoiHan;
		TrangThai = trangThai;
		SoHocVien = soHocVien;
		SoBuoiTap = soBuoiTap;
		this.lopDichVu = lopDichVu;
		this.nguoiQuanLyGT = nguoiQuanLyGT;
		this.listChiTietThe = listChiTietThe;
		this.listGiaGoiTap = listGiaGoiTap;
	}

	public String getMaGoiTap() {
		return MaGoiTap;
	}

	public void setMaGoiTap(String maGoiTap) {
		MaGoiTap = maGoiTap;
	}

	public String getTenGoiTap() {
		return TenGoiTap;
	}

	public void setTenGoiTap(String tenGoiTap) {
		TenGoiTap = tenGoiTap;
	}

	public int getThoiHan() {
		return ThoiHan;
	}

	public void setThoiHan(int ThoiHan) {
		this.ThoiHan = ThoiHan;
	}

	public int getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(int trangThai) {
		TrangThai = trangThai;
	}

	public LopDichVu getLopDichVu() {
		return lopDichVu;
	}

	public void setLopDichVu(LopDichVu lopDichVu) {
		this.lopDichVu = lopDichVu;
	}

	public NhanVien getNguoiQuanLyGT() {
		return nguoiQuanLyGT;
	}

	public void setNguoiQuanLyGT(NhanVien nguoiQuanLyGT) {
		this.nguoiQuanLyGT = nguoiQuanLyGT;
	}

	public List<ChiTietThe> getListChiTietThe() {
		return listChiTietThe;
	}

	public void setListChiTietThe(List<ChiTietThe> listChiTietThe) {
		this.listChiTietThe = listChiTietThe;
	}

	public List<GiaGoiTap> getListGiaGoiTap() {
		return listGiaGoiTap;
	}

	public void setListGiaGoiTap(List<GiaGoiTap> listGiaGoiTap) {
		this.listGiaGoiTap = listGiaGoiTap;
	}
	
	public int getSoHocVien() {
		return SoHocVien;
	}

	public void setSoHocVien(int soHocVien) {
		SoHocVien = soHocVien;
	}

	public int getSoBuoiTap() {
		return SoBuoiTap;
	}

	public void setSoBuoiTap(int soBuoiTap) {
		SoBuoiTap = soBuoiTap;
	}

	public float getPrice() {
		Collections.sort(listGiaGoiTap, new Comparator<GiaGoiTap>() {
		  public int compare(GiaGoiTap g1, GiaGoiTap g2) {
		      return g1.getNgayCapNhat().compareTo(g2.getNgayCapNhat());
		  }
		});
		return listGiaGoiTap.get(listGiaGoiTap.size() - 1).getGia();
	}
	
	public float getPriceByDate(LocalDateTime date) {
		Collections.sort(listGiaGoiTap, new Comparator<GiaGoiTap>() {
		  public int compare(GiaGoiTap g1, GiaGoiTap g2) {
		      return g1.getNgayCapNhat().compareTo(g2.getNgayCapNhat());
		  }
		});
		float price = 0;
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		for(GiaGoiTap item: listGiaGoiTap) {
			if (date.isAfter(LocalDateTime.from(dateTimeFormatter.parse(item.getNgayCapNhat().substring(0, 19))))) {
				price = item.getGia();
			} else {
				break;
			}
		}
		return price;
	}
	
	public int getSoLuotDangKy() {
		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
		
		for (ChiTietThe chiTietThe: this.listChiTietThe) {
			KhachHang khachHang = chiTietThe.getThe().getKhachHang();
			Boolean isContain = false;
			for (KhachHang item: listKhachHang) {
				if (item.getMaKH().equals(khachHang.getMaKH())) {
					isContain = true;
					break;
				}
			}
			if (!isContain) {
				listKhachHang.add(khachHang);
			}
		}
		return listKhachHang.size();
	}
}
