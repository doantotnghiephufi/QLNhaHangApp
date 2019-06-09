package com.example.nguyenhuongit.qlnhahangapp.Data;

public class DoanhThuKhuVuc {
    String MaViTri;
    String TenViTri;
    String SoLuongPhieu;
    String DoanhThu;

    public DoanhThuKhuVuc(String maViTri, String tenViTri, String soLuongPhieu, String doanhThu, String tenLoaiKhuVuc) {
        MaViTri = maViTri;
        TenViTri = tenViTri;
        SoLuongPhieu = soLuongPhieu;
        DoanhThu = doanhThu;
        TenLoaiKhuVuc = tenLoaiKhuVuc;
    }

    public String getMaViTri() {
        return MaViTri;
    }

    public void setMaViTri(String maViTri) {
        MaViTri = maViTri;
    }

    public String getTenViTri() {
        return TenViTri;
    }

    public void setTenViTri(String tenViTri) {
        TenViTri = tenViTri;
    }

    public String getSoLuongPhieu() {
        return SoLuongPhieu;
    }

    public void setSoLuongPhieu(String soLuongPhieu) {
        SoLuongPhieu = soLuongPhieu;
    }

    public String getDoanhThu() {
        return DoanhThu;
    }

    public void setDoanhThu(String doanhThu) {
        DoanhThu = doanhThu;
    }

    public String getTenLoaiKhuVuc() {
        return TenLoaiKhuVuc;
    }

    public void setTenLoaiKhuVuc(String tenLoaiKhuVuc) {
        TenLoaiKhuVuc = tenLoaiKhuVuc;
    }

    String TenLoaiKhuVuc;

    @Override
    public String toString() {
        return "DoanhThuKhuVuc{" +
                "MaViTri='" + MaViTri + '\'' +
                ", TenViTri='" + TenViTri + '\'' +
                ", SoLuongPhieu='" + SoLuongPhieu + '\'' +
                ", DoanhThu='" + DoanhThu + '\'' +
                ", TenLoaiKhuVuc='" + TenLoaiKhuVuc + '\'' +
                '}';
    }
}
