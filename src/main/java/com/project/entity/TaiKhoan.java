package com.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="tai_khoan")
public class TaiKhoan {
	@Id
	@Column(name="username",unique = true)
	@NotBlank(message="Thông tin này là bắt buộc")
	@Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{4,29}$", message = "Tên tài khoản không hợp lệ")
	private String Username;
	@Column(name="password")
	@NotBlank(message="Thông tin này là bắt buộc")
	private String Password;
	@Column(name="trang_thai")
	private int TrangThai;
	
	@OneToOne(mappedBy = "taiKhoan" ,fetch = FetchType.EAGER)
	private NhanVien nhanVien;
	
	@ManyToOne
	@JoinColumn(name="ma_quyen")
	private PhanQuyen phanQuyen;

	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaiKhoan(String username, String password, int trangThai, NhanVien nhanVien,
			PhanQuyen phanQuyen) {
		super();
		Username = username;
		Password = password;
		TrangThai = trangThai;
		this.nhanVien = nhanVien;
		this.phanQuyen = phanQuyen;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public int getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(int trangThai) {
		TrangThai = trangThai;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public PhanQuyen getPhanQuyen() {
		return phanQuyen;
	}

	public void setPhanQuyen(PhanQuyen phanQuyen) {
		this.phanQuyen = phanQuyen;
	}
}
