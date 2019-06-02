package com.example.nguyenhuongit.qlnhahangapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenhuongit.qlnhahangapp.Data.KhachHang;
import com.example.nguyenhuongit.qlnhahangapp.R;
import com.example.nguyenhuongit.qlnhahangapp.View.Fragment.FragmentKhachHangDetails;

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
        myViewHolder.tv_tenKH.setText(khachHang.getTenKH());
        myViewHolder.tv_SDT.setText(khachHang.getSDT());
        myViewHolder.tv_maKH.setText(khachHang.getMaKH());
        myViewHolder.tv_TichLuy.setText(khachHang.getTichLuy());
    }

    @Override
    public int getItemCount() {
        return khachHangList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tenKH,tv_SDT,tv_TichLuy,tv_maKH;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            tv_tenKH = itemView.findViewById(R.id.tv_tenKH);
            tv_SDT = itemView.findViewById(R.id.tv_SDT);
            tv_TichLuy = itemView.findViewById(R.id.tv_TichLuy);
            tv_maKH = itemView.findViewById(R.id.tv_maKH);

            //sự kiện click của item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //gửi dữ liệu qua fragment details
                    KhachHang khachHang = khachHangList.get(getAdapterPosition());
                    String tv_tenkhachhang = khachHang.getTenKH();
                    String tv_makhachhang = khachHang.getMaKH();
                    String tv_tichluy = khachHang.getTichLuy();
                    String tv_sodienthoai = khachHang.getSDT();
                    Bundle bundle = new Bundle();
                    bundle.putString("tv_tenkhachhang",tv_tenkhachhang);
                    bundle.putString("tv_sodienthoai",tv_sodienthoai);
                    bundle.putString("tv_tichluy",tv_tichluy);
                    bundle.putString("tv_makhachhang",tv_makhachhang);

                    FragmentKhachHangDetails fragmentDetails= new FragmentKhachHangDetails();
                    fragmentDetails.setArguments(bundle);
                    FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container_main, fragmentDetails);
                    transaction.addToBackStack(null);
                    transaction.commit();


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
