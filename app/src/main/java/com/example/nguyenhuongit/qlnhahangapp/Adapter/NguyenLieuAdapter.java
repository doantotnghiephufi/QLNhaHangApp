package com.example.nguyenhuongit.qlnhahangapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenhuongit.qlnhahangapp.Data.NguyenLieu;
import com.example.nguyenhuongit.qlnhahangapp.R;

import java.util.ArrayList;
import java.util.List;

public class NguyenLieuAdapter extends RecyclerView.Adapter<NguyenLieuAdapter.MyViewHolder> {

    public Context context;
    int layout;
    public LayoutInflater mLayoutInflater;
    public ArrayList<NguyenLieu> nguyenLieuList;

    public NguyenLieuAdapter(Context context, int layout, ArrayList<NguyenLieu> nguyenLieuList) {
        this.context = context;
        this.layout = layout;
        this.nguyenLieuList = nguyenLieuList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_custom_tonkho, viewGroup,false);
        return new NguyenLieuAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        NguyenLieu nguyenLieu = nguyenLieuList.get(i);
        myViewHolder.tv_mamon.setText(nguyenLieu.getMaNguyenLieu());
        myViewHolder.tv_tenmon.setText(nguyenLieu.getTenNguyenLieu());


//        Log.d("texttext", text);
        myViewHolder.tv_soluongton.setText( Math.round(Float.parseFloat(nguyenLieu.getTonKho()))+"");
        myViewHolder.tv_loaimon.setText(nguyenLieu.getMaLoaiNguyenLieu());
    }

    @Override
    public int getItemCount() {
        return nguyenLieuList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_tenmon,tv_loaimon,tv_mamon,tv_soluongton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tenmon = itemView.findViewById(R.id.tv_tenmon);
            tv_mamon = itemView.findViewById(R.id.tv_mamon);
            tv_loaimon = itemView.findViewById(R.id.tv_loaimon);
            tv_soluongton = itemView.findViewById(R.id.tv_soluongton);
            //sự kiện click của item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Short item clicked " + tv_tenmon.getText(), Toast.LENGTH_SHORT).show();
                }
            });

            // sự kiện click nhấn giữ
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(context, "Long item clicked " + tv_tenmon.getText(), Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }
    }
}
