package com.example.nguyenhuongit.qlnhahangapp.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ThucUong {

    @Override
    public String toString() {
        return "ThucUong{" +
                "MaThucUong='" + MaThucUong + '\'' +
                ", TenThucUong='" + TenThucUong + '\'' +
                ", TongDat='" + TongDat + '\'' +
                '}';
    }

    public ThucUong(String maThucUong, String tenThucUong, String tongDat) {
        MaThucUong = maThucUong;
        TenThucUong = tenThucUong;
        TongDat = tongDat;
    }

    public String getMaThucUong() {
        return MaThucUong;
    }

    public void setMaThucUong(String maThucUong) {
        MaThucUong = maThucUong;
    }

    public String getTenThucUong() {
        return TenThucUong;
    }

    public void setTenThucUong(String tenThucUong) {
        TenThucUong = tenThucUong;
    }

    String MaThucUong,TenThucUong, TongDat;


    public ThucUong() {
    }


    public String getTongDat() {
        return TongDat;
    }

    public void setTongDat(String tongDat) {
        TongDat = tongDat;
    }

    public ThucUong getThucUong(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ThucUong>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<ThucUong> getListThucUong(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<ThucUong>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }
}
