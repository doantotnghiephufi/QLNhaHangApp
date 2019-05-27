package com.example.nguyenhuongit.qlnhahangapp.Data;

public class NguyenLieu {
    private String MaNguyenLieu;
    private String TenNguyenLieu;
    private String LoaiNguyenLieu;
    private String SoLuong;

    public NguyenLieu(){}
    public NguyenLieu(String maNguyenLieu, String tenNguyenLieu, String loaiNguyenLieu, String soLuong) {
        MaNguyenLieu = maNguyenLieu;
        TenNguyenLieu = tenNguyenLieu;
        LoaiNguyenLieu = loaiNguyenLieu;
        SoLuong = soLuong;
    }

    public String getMaNguyenLieu() {
        return MaNguyenLieu;
    }

    public void setMaNguyenLieu(String maNguyenLieu) {
        MaNguyenLieu = maNguyenLieu;
    }

    public String getTenNguyenLieu() {
        return TenNguyenLieu;
    }

    public void setTenNguyenLieu(String tenNguyenLieu) {
        TenNguyenLieu = tenNguyenLieu;
    }

    public String getLoaiNguyenLieu() {
        return LoaiNguyenLieu;
    }

    public void setLoaiNguyenLieu(String loaiNguyenLieu) {
        LoaiNguyenLieu = loaiNguyenLieu;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }
}
