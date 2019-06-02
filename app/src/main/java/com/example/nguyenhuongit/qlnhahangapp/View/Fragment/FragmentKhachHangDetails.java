package com.example.nguyenhuongit.qlnhahangapp.View.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nguyenhuongit.qlnhahangapp.R;

public class FragmentKhachHangDetails extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    String tv_tenkhachhang,tv_sodienthoai,tv_tichluy,tv_makhachhang;
    TextView tv_tenKH_Details,tv_TichLuy_Details;

    public FragmentKhachHangDetails() {
    }

    public static FragmentKhachHangDetails newInstance(String param1, String param2) {
        FragmentKhachHangDetails fragment = new FragmentKhachHangDetails();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_custom_khachhang_details, container,false);

        //lấy dữ liệu từ khách hàng
        Bundle bundle = getArguments();
        tv_makhachhang = bundle.getString("tv_makhachhang");
        tv_sodienthoai = bundle.getString("tv_sodienthoai");
        tv_tenkhachhang = bundle.getString("tv_tenkhachhang");
        tv_tichluy = bundle.getString("tv_tichluy");

        //set dữ liệu
        tv_tenKH_Details = view.findViewById(R.id.tv_tenKH_Details);
        tv_TichLuy_Details = view.findViewById(R.id.tv_TichLuy_Details);

        tv_tenKH_Details.setText(tv_tenkhachhang);
        tv_TichLuy_Details.setText(tv_tichluy);

        return view;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
