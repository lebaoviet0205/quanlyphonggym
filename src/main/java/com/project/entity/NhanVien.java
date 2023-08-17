package com.project.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="nhan_vien")
public class NhanVien {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="ma_nv")
	private String MaNV;
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
	@Column(name="dia_chi")
	@NotBlank(message="Thông tin này là bắt buộc")
	private String DiaChi;
	@Column(name="gioi_tinh")
	private int GioiTinh;
	@Column(name="ngay_nhan_chuc")
	@NotNull(message="Vui lòng nhập một ngày")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent(message="Ngày nhận chức không hợp lệ")
	private LocalDate NgayNhanChuc;
	@Column(name="ngay_sinh")
	@NotNull(message="Vui lòng nhập một ngày")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message="Ngày sinh không hợp lệ")
	private LocalDate NgaySinh;
	@Column(name="anh", nullable = true)
	private String Anh;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="username")
	private TaiKhoan taiKhoan;
	
	@OneToMany(mappedBy = "nhanVien")
	private List<The> listThe;
	
	@OneToMany(mappedBy = "nguoiQuanLyLDV")
	private List<LopDichVu> listLopDichVu;
	
	@OneToMany(mappedBy = "nguoiQuanLyGT")
	private List<GoiTap> listGoiTap;
	
	@OneToMany(mappedBy = "nguoiQuanLyGGT")
	private List<GiaGoiTap> listGiaGoiTap;
	
	@OneToMany(mappedBy = "nhanVien")
	private List<HoaDon> listHoaDon;

	@OneToMany(mappedBy = "nhanVien")
	private List<BangDiemDanh> listBangDiemDanh;
	
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NhanVien(String maNV, String ho, String ten, String email, String sDT, String diaChi, int gioiTinh,
			LocalDate ngayNhanChuc, LocalDate ngaySinh, String anh, TaiKhoan taiKhoan, List<The> listThe,
			List<LopDichVu> listLopDichVu, List<GoiTap> listGoiTap, List<GiaGoiTap> listGiaGoiTap,
			List<HoaDon> listHoaDon, List<BangDiemDanh> listBangDiemDanh) {
		super();
		MaNV = maNV;
		Ho = ho;
		Ten = ten;
		Email = email;
		SDT = sDT;
		DiaChi = diaChi;
		GioiTinh = gioiTinh;
		NgayNhanChuc = ngayNhanChuc;
		NgaySinh = ngaySinh;
		Anh = anh;
		this.taiKhoan = taiKhoan;
		this.listThe = listThe;
		this.listLopDichVu = listLopDichVu;
		this.listGoiTap = listGoiTap;
		this.listGiaGoiTap = listGiaGoiTap;
		this.listHoaDon = listHoaDon;
		this.listBangDiemDanh = listBangDiemDanh;
	}
	public String getMaNV() {
		return MaNV;
	}

	public void setMaNV(String maNV) {
		MaNV = maNV;
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

	public LocalDate getNgayNhanChuc() {
		return NgayNhanChuc;
	}

	public void setNgayNhanChuc(LocalDate ngayNhanChuc) {
		NgayNhanChuc = ngayNhanChuc;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getAnh() {
		return Anh;
	}
	public void setAnh(String anh) {
		Anh = anh;
	}
	public List<The> getListThe() {
		return listThe;
	}

	public void setListThe(List<The> listThe) {
		this.listThe = listThe;
	}

	public List<LopDichVu> getListLopDichVu() {
		return listLopDichVu;
	}

	public void setListLopDichVu(List<LopDichVu> listLopDichVu) {
		this.listLopDichVu = listLopDichVu;
	}

	public List<GoiTap> getListGoiTap() {
		return listGoiTap;
	}

	public void setListGoiTap(List<GoiTap> listGoiTap) {
		this.listGoiTap = listGoiTap;
	}

	public List<GiaGoiTap> getListGiaGoiTap() {
		return listGiaGoiTap;
	}

	public void setListGiaGoiTap(List<GiaGoiTap> listGiaGoiTap) {
		this.listGiaGoiTap = listGiaGoiTap;
	}

	public List<HoaDon> getListHoaDon() {
		return listHoaDon;
	}

	public void setListHoaDon(List<HoaDon> listHoaDon) {
		this.listHoaDon = listHoaDon;
	}

	public List<BangDiemDanh> getListBangDiemDanh() {
		return listBangDiemDanh;
	}

	public void setListBangDiemDanh(List<BangDiemDanh> listBangDiemDanh) {
		this.listBangDiemDanh = listBangDiemDanh;
	}
	
	public String getFullname() {
		return this.Ho + " " + this.Ten;
	}
	public LocalDate getNgaySinh() {
		return NgaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		NgaySinh = ngaySinh;
	}
}
