package com.example.nguyenhuongit.qlnhahangapp.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ThucUong {

    String MaMonAn,TenMonAn, TongDat;

    public ThucUong(String maMonAn, String tenMonAn, String tongDat) {
        MaMonAn = maMonAn;
        TenMonAn = tenMonAn;
        TongDat = tongDat;
    }

    public ThucUong() {
    }

    public String getMaMonAn() {
        return MaMonAn;
    }

    public void setMaMonAn(String maMonAn) {
        MaMonAn = maMonAn;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        TenMonAn = tenMonAn;
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
