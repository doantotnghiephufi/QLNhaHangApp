package com.example.nguyenhuongit.qlnhahangapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenhuongit.qlnhahangapp.Data.KhachHang;
import com.example.nguyenhuongit.qlnhahangapp.R;

import java.util.ArrayList;
import java.util.List;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.MyViewHolder> {
    private Context context;
    int layout;
    private LayoutInflater mLayoutInflater;
    private List<KhachHang> khachHangList = new ArrayList<>();

    public KhachHangAdapter(Context context, int layout, List<KhachHang> khachHangList)
    {
        this.context = context;
        this.layout = layout;
        this.khachHangList = khachHangList;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_custom_khachhang, viewGroup,false);
        return new KhachHangAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        KhachHang khachHang = khachHangList.get(i);
        myViewHolder.tv_tenKH.setText(khachHang.getTenKhachHang());
        myViewHolder.tv_SDT.setText(khachHang.getSDTKhachHang());
    }

    @Override
    public int getItemCount() {
        return khachHangList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tenKH,tv_SDT;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            tv_tenKH = itemView.findViewById(R.id.tv_tenKH);
            tv_SDT = itemView.findViewById(R.id.tv_SDT);

            //sự kiện click của item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Short item click" + tv_tenKH.getText() , Toast.LENGTH_SHORT).show();
                }
            });
            //sự kiện long click của item
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(itemView.getContext(), "Long item click" + tv_tenKH.getText() , Toast.LENGTH_SHORT).show();
                    return false;
                }
            });


        }
    }
}
