package com.example.nguyenhuongit.qlnhahangapp.View.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nguyenhuongit.qlnhahangapp.Adapter.HoaDonAdapter;
import com.example.nguyenhuongit.qlnhahangapp.Adapter.KhachHangAdapter;
import com.example.nguyenhuongit.qlnhahangapp.Data.HoaDon;
import com.example.nguyenhuongit.qlnhahangapp.Data.KhachHang;
import com.example.nguyenhuongit.qlnhahangapp.R;

import org.json.JSONArray;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class FragmentKhachHangDetails extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    String tv_tenkhachhang,tv_sodienthoai,tv_tichluy,tv_makhachhang;
    TextView tv_tenKH_Details,tv_TichLuy_Details;

    RecyclerView recyclerView_hoadon;

    RecyclerView recyclerView;
    HoaDonAdapter hoaDonAdapter;
    ArrayList<HoaDon> hoaDonArrayList = new ArrayList<>();

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
        Log.d("tv_makhachhang",tv_makhachhang);
        Log.d("tv_sodienthoai",tv_sodienthoai);
        Log.d("tv_tenkhachhang",tv_tenkhachhang);
        Log.d("tv_tichluy",tv_tichluy);

        //set dữ liệu
        tv_tenKH_Details = view.findViewById(R.id.tv_tenKH_Details);
        tv_TichLuy_Details = view.findViewById(R.id.tv_TichLuy_Details);

        tv_tenKH_Details.setText(tv_tenkhachhang);

        float vnd = Float.parseFloat(tv_tichluy);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeVN);
        numberFormat.setCurrency(Currency.getInstance(localeVN));
        String str1 = numberFormat.format(vnd);
        tv_TichLuy_Details.setText(str1+"");
        recyclerView_hoadon = view.findViewById(R.id.recyclerView_hoadon);
        GetDataHDSD();
        return view;
    }

    private void GetDataHDSD() {
        String url ="http://192.168.1.11/api/ThongKe/ThongKeHoaDonByMaKH?maKH="+tv_makhachhang;
        Log.d("tv_makhachhang", url);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                HoaDon hoaDon  = new HoaDon();
                hoaDonArrayList = hoaDon.getListHoaDon(response.toString());
                hoaDonAdapter = new HoaDonAdapter(getContext(),R.layout.item_custom_hoadon,hoaDonArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
                recyclerView_hoadon.setLayoutManager(linearLayoutManager);
                recyclerView_hoadon.setItemAnimator(new DefaultItemAnimator());
                recyclerView_hoadon.setAdapter(hoaDonAdapter);
                hoaDonAdapter.notifyDataSetChanged();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),"Error!!!",Toast.LENGTH_SHORT);
                    }
                }
        );
        Log.d("khachHangArrayList", hoaDonArrayList.size() +"");
        requestQueue.add(jsonArrayRequest);
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
