package com.example.nguyenhuongit.qlnhahangapp.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class NguyenLieu {
    String MaNguyenLieu;
    String TenNguyenLieu;
    String TonKho;

    public NguyenLieu(String maNguyenLieu, String tenNguyenLieu, String tonKho, String maLoaiNguyenLieu) {
        this.MaNguyenLieu = maNguyenLieu;
        TenNguyenLieu = tenNguyenLieu;
        TonKho = tonKho;
        MaLoaiNguyenLieu = maLoaiNguyenLieu;
    }

    public NguyenLieu() {
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

    public String getTonKho() {
        return TonKho;
    }

    public void setTonKho(String tonKho) {
        TonKho = tonKho;
    }

    public String getMaLoaiNguyenLieu() {
        return MaLoaiNguyenLieu;
    }

    public void setMaLoaiNguyenLieu(String maLoaiNguyenLieu) {
        MaLoaiNguyenLieu = maLoaiNguyenLieu;
    }

    String MaLoaiNguyenLieu;

    @Override
    public String toString() {
        return "NguyenLieu{" +
                "MaNguyenLieu='" + MaNguyenLieu + '\'' +
                ", TenNguyenLieu='" + TenNguyenLieu + '\'' +
                ", TonKho='" + TonKho + '\'' +
                ", MaLoaiNguyenLieu='" + MaLoaiNguyenLieu + '\'' +
                '}';
    }

    public NguyenLieu getNguyenLieu(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<NguyenLieu>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<NguyenLieu> getListNguyenLieu(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<NguyenLieu>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
