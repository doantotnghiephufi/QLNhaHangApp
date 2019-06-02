package com.example.nguyenhuongit.qlnhahangapp.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DoanhThu {
    String MaHD;
    String TongTien;
    String GiamGia;
    String ThanhTien;

    public DoanhThu(String maHD, String tongTien, String giamGia, String thanhTien, String gioLapHD) {
        MaHD = maHD;
        TongTien = tongTien;
        GiamGia = giamGia;
        ThanhTien = thanhTien;
        GioLapHD = gioLapHD;
    }

    public DoanhThu() {
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
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

    public String getGioLapHD() {
        return GioLapHD;
    }

    public void setGioLapHD(String gioLapHD) {
        GioLapHD = gioLapHD;
    }

    String GioLapHD;

    @Override
    public String toString() {
        return "DoanhThu{" +
                "MaHD='" + MaHD + '\'' +
                ", TongTien='" + TongTien + '\'' +
                ", GiamGia='" + GiamGia + '\'' +
                ", ThanhTien='" + ThanhTien + '\'' +
                ", GioLapHD='" + GioLapHD + '\'' +
                '}';
    }

    public DoanhThu getDoanhThu (String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<DoanhThu>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<DoanhThu> getListDoanhThu(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<DoanhThu>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
