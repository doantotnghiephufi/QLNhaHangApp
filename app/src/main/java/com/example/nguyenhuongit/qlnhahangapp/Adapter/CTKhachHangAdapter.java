package com.example.nguyenhuongit.qlnhahangapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenhuongit.qlnhahangapp.Data.CTKhachHang;
import com.example.nguyenhuongit.qlnhahangapp.R;

import java.util.ArrayList;
import java.util.List;

public class CTKhachHangAdapter extends RecyclerView.Adapter<CTKhachHangAdapter.MyViewHolder> {
    private int layout;
    private Context context;
    private LayoutInflater inflater;
    private List<CTKhachHang> ctKhachHangList = new ArrayList<>();

    public CTKhachHangAdapter(Context context , int layout, List<CTKhachHang> ctKhachHangList){
        this.context = context;
        this.layout = layout;
        this.ctKhachHangList = ctKhachHangList;
        inflater = LayoutInflater.from(context);
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_custom_khachhang_details, viewGroup, false);

        return new CTKhachHangAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
