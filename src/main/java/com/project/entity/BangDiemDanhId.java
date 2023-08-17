package com.project.entity;

import java.io.Serializable;

public class BangDiemDanhId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String NgayDiemDanh;
	private String NgayDangKy;
	public BangDiemDanhId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BangDiemDanhId(String ngayDiemDanh, String ngayDangKy) {
		super();
		NgayDiemDanh = ngayDiemDanh;
		NgayDangKy = ngayDangKy;
	}
	public String getNgayDiemDanh() {
		return NgayDiemDanh;
	}
	public void setNgayDiemDanh(String ngayDiemDanh) {
		NgayDiemDanh = ngayDiemDanh;
	}
	public String getNgayDangKy() {
		return NgayDangKy;
	}
	public void setNgayDangKy(String ngayDangKy) {
		NgayDangKy = ngayDangKy;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NgayDangKy == null) ? 0 : NgayDangKy.hashCode());
		result = prime * result + ((NgayDiemDanh == null) ? 0 : NgayDiemDanh.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BangDiemDanhId other = (BangDiemDanhId) obj;
		if (NgayDangKy == null) {
			if (other.NgayDangKy != null)
				return false;
		} else if (!NgayDangKy.equals(other.NgayDangKy))
			return false;
		if (NgayDiemDanh == null) {
			if (other.NgayDiemDanh != null)
				return false;
		} else if (!NgayDiemDanh.equals(other.NgayDiemDanh))
			return false;
		return true;
	}
}
