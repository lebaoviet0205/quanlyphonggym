package com.project.entity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Email;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="khach_hang")
public class KhachHang {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="ma_kh")
	private String MaKH;
	@Column(name="ho")
	@NotBlank(message="Thông tin này là bắt buộc")
	private String Ho;
	@Column(name="ten")
	@NotBlank(message="Thông tin này là bắt buộc")
	private String Ten;
	@Column(name="email")
	@NotBlank(message="Thông tin này là bắt buộc")
	@Pattern(regexp = "^(?i)[-a-z0-9+_][-a-z0-9+_.]*@[-a-z0-9][-a-z0-9.]*\\.[a-z]*$", message = "Email không hợp lệ")
	private String Email;
	@Column(name="sdt")
	@NotBlank(message="Thông tin này là bắt buộc")
	@Length(min = 10, max = 10, message="Số điện thoại không hợp lệ")
	private String SDT;
	@Column(name="dia_chi", nullable = true)
	private String DiaChi;
	@Column(name="gioi_tinh")
	private int GioiTinh;
	@Column(name="ngay_sinh")
	@NotNull(message="Vui lòng nhập một ngày")
	@PastOrPresent(message="Ngày sinh không hợp lệ")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate NgaySinh;
	@Column(name="ghi_chu", nullable = true)
	private String GhiChu;
	@Column(name="ngay_dang_ky")
	private LocalDate NgayDangKy;
	@Column(name="anh", nullable = true)
	private String anh;
	
	@OneToMany(mappedBy = "khachHang", fetch = FetchType.EAGER)
	private List<The> listThe;
	
	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KhachHang(String maKH, String ho, String ten, String email, String sDT, String diaChi, int gioiTinh,
			LocalDate ngaySinh, String ghiChu, LocalDate ngayDangKy, String anh, List<The> listThe) {
		super();
		MaKH = maKH;
		Ho = ho;
		Ten = ten;
		Email = email;
		SDT = sDT;
		DiaChi = diaChi;
		GioiTinh = gioiTinh;
		NgaySinh = ngaySinh;
		GhiChu = ghiChu;
		NgayDangKy = ngayDangKy;
		this.anh = anh;
		this.listThe = listThe;
	}

	public String getMaKH() {
		return MaKH;
	}
	public void setMaKH(String maKH) {
		MaKH = maKH;
	}
	public String getHo() {
		return Ho;
	}
	public void setHo(String ho) {
		Ho = ho;
	}
	public String getTen() {
		return Ten;
	}
	public void setTen(String ten) {
		Ten = ten;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public int getGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(int gioiTinh) {
		GioiTinh = gioiTinh;
	}
	public LocalDate getNgaySinh() {
		return NgaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		NgaySinh = ngaySinh;
	}
	public String getGhiChu() {
		return GhiChu;
	}
	public void setGhiChu(String ghiChu) {
		GhiChu = ghiChu;
	}
	public List<The> getListThe() {
		return listThe;
	}
	public void setListThe(List<The> listThe) {
		this.listThe = listThe;
	}
	public LocalDate getNgayDangKy() {
		return NgayDangKy;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public void setNgayDangKy(LocalDate ngayDangKy) {
		NgayDangKy = ngayDangKy;
	}

	public The getCurrentThe() {
		List<The> listThe = this.getListThe();
		
		Collections.sort(listThe, new Comparator<The>() {
		  public int compare(The t1, The t2) {
		      if (t1.getNgayTao() == null || t2.getNgayTao() == null)
		        return 0;
		      return t1.getNgayTao().compareTo(t2.getNgayTao());
		  }
		});
		
		return listThe.get(listThe.size() - 1);
	}
	
	public String getFullname() {
		return this.Ho + " " + this.Ten;
	}
}
