package com.example.nguyenhuongit.qlnhahangapp.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class KhachHang {

    String MaKH,TenKH,SDT,TichLuy;

    public KhachHang(String maKH, String tenKH, String SDT, String tichLuy) {
        MaKH = maKH;
        TenKH = tenKH;
        this.SDT = SDT;
        TichLuy = tichLuy;
    }

    public KhachHang() {
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getTichLuy() {
        return TichLuy;
    }

    public void setTichLuy(String tichLuy) {
        TichLuy = tichLuy;
    }

    public KhachHang getKhachHang(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<KhachHang>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<KhachHang> getListKhachHang(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<KhachHang>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "MaKH='" + MaKH + '\'' +
                ", TenKH='" + TenKH + '\'' +
                ", SDT='" + SDT + '\'' +
                ", TichLuy='" + TichLuy + '\'' +
                '}';
    }
}
