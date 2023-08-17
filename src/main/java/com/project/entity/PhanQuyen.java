package com.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="phan_quyen")
public class PhanQuyen {
	@Id
	@Column(name="ma_quyen")
	private String MaQuyen;
	@Column(name="chuc_vu")
	private String ChucVu;
	public PhanQuyen() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PhanQuyen(String maQuyen, String chucVu) {
		super();
		MaQuyen = maQuyen;
		ChucVu = chucVu;
	}
	public String getMaQuyen() {
		return MaQuyen;
	}
	public void setMaQuyen(String maQuyen) {
		MaQuyen = maQuyen;
	}
	public String getChucVu() {
		return ChucVu;
	}
	public void setChucVu(String chucVu) {
		ChucVu = chucVu;
	}
}
