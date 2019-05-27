package com.example.nguyenhuongit.qlnhahangapp.Data;

public class KhachHang {
    String TenKhachHang;

    public KhachHang(String tenKhachHang, String SDTKhachHang) {
        TenKhachHang = tenKhachHang;
        this.SDTKhachHang = SDTKhachHang;
    }

    public KhachHang() {
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        TenKhachHang = tenKhachHang;
    }

    public String getSDTKhachHang() {
        return SDTKhachHang;
    }

    public void setSDTKhachHang(String SDTKhachHang) {
        this.SDTKhachHang = SDTKhachHang;
    }

    String SDTKhachHang;
}
