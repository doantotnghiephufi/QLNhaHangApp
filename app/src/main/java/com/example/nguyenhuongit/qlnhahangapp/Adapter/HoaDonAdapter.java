package com.example.nguyenhuongit.qlnhahangapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nguyenhuongit.qlnhahangapp.Data.HoaDon;
import com.example.nguyenhuongit.qlnhahangapp.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;


public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.MyViewHolder> {

    int layout;
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<HoaDon> hoaDonList = new ArrayList<>();
    public HoaDonAdapter(Context context, int layout, ArrayList<HoaDon> hoaDonList)
    {
        this.context = context;
        this.layout = layout;
        this.hoaDonList = hoaDonList;
        layoutInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_custom_hoadon, viewGroup,false);
        return new HoaDonAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        HoaDon hoaDon = hoaDonList.get(i);
        myViewHolder.tv_gioThanhToan.setText(hoaDon.getGioThanhToan());
        myViewHolder.tv_maHD.setText(hoaDon.getMaHD());
        float vnd = Float.parseFloat(hoaDon.getThanhTien());
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeVN);
        numberFormat.setCurrency(Currency.getInstance(localeVN));
        String str1 = numberFormat.format(vnd);
        myViewHolder.tv_thanhTien.setText(str1+"");
    }

    @Override
    public int getItemCount() {
        return hoaDonList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_gioThanhToan,tv_thanhTien,tv_maHD;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_gioThanhToan = itemView.findViewById(R.id.tv_gioThanhToan);
            tv_thanhTien = itemView.findViewById(R.id.tv_thanhTien);
            tv_maHD = itemView.findViewById(R.id.tv_maHD);
        }
    }
}
