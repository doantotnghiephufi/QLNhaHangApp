package com.example.nguyenhuongit.qlnhahangapp.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MonAn {

    String MaMonAn,TenMonAn, TongDat;

    public MonAn(String maMonAn, String tenMonAn, String tongDat) {
        MaMonAn = maMonAn;
        TenMonAn = tenMonAn;
        TongDat = tongDat;
    }

    public MonAn() {
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

    public MonAn getMonAn(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<MonAn>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<MonAn> getListMonAn(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<MonAn>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    @Override
    public String toString() {
        return "MonAn{" +
                "MaMonAn='" + MaMonAn + '\'' +
                ", TenMonAn='" + TenMonAn + '\'' +
                ", TongDat='" + TongDat + '\'' +
                '}';
    }
}
