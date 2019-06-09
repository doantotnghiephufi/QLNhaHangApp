package com.example.nguyenhuongit.qlnhahangapp.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HoaDon {
    String MaHD;
    String GioThanhToan;
    String TongTien;
    String GiamGia;

    public HoaDon(String maHD, String gioThanhToan, String tongTien, String giamGia, String thanhTien) {
        MaHD = maHD;
        GioThanhToan = gioThanhToan;
        TongTien = tongTien;
        GiamGia = giamGia;
        ThanhTien = thanhTien;
    }

    public HoaDon() {
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
    }

    public String getGioThanhToan() {
        return GioThanhToan;
    }

    public void setGioThanhToan(String gioThanhToan) {
        GioThanhToan = gioThanhToan;
    }

    public String getTongTien() {
        return TongTien;
    }

    public void setTongTien(String tongTien) {
        TongTien = tongTien;
    }

    public String getGiamGia() {
        return GiamGia;
    }

    public void setGiamGia(String giamGia) {
        GiamGia = giamGia;
    }

    public String getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(String thanhTien) {
        ThanhTien = thanhTien;
    }

    String ThanhTien;

    @Override
    public String toString() {
        return "HoaDon{" +
                "MaHD='" + MaHD + '\'' +
                ", GioThanhToan='" + GioThanhToan + '\'' +
                ", TongTien='" + TongTien + '\'' +
                ", GiamGia='" + GiamGia + '\'' +
                ", ThanhTien='" + ThanhTien + '\'' +
                '}';
    }

    public HoaDon getHoaDon(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<HoaDon>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<HoaDon> getListHoaDon(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<HoaDon>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

}

